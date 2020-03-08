package lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

public class Main {

    public static void main(String[] args) throws IOException  {
        Init();

        JavaSparkContext sparkContext = GetContext();
        JavaRDD<String> requestsInfo = sparkContext.textFile("D:\\input2.txt");

        ShowBrowserStatistic(requestsInfo, (key ,value) -> System.out.println(String.format("%-25s : %d", key, value)));

        IpProcessor proc = new IpProcessor();
        JavaPairRDD<String, LogInfo> data = proc.Process(requestsInfo);

        data.saveAsTextFile("D:\\University\\big_data\\lab3\\results");
    }

    public static void Init() {
        System.setProperty("hadoop.home.dir", "C:\\winutils");
        System.setProperty("spark.local.dir", "C:\\winutils\\tmp");
    }

    public static JavaSparkContext GetContext() {
        SparkConf conf = new SparkConf()
                .setMaster("local[1]")
                .setAppName("lab3");
        return new JavaSparkContext(conf);
    }

    public static void ShowBrowserStatistic(JavaRDD<String> logs, BiConsumer<? super String, ? super Long> action) {
        UserAgentAnalyzer analyzer = new UserAgentAnalyzer();
        Map<String, Long> statistic = analyzer.GetBrowsersStatistic(logs);
        statistic.forEach(action);
    }
}
