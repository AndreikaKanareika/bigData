package lab3;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpProcessor implements Serializable {

    private static String _ipPattern = "ip(\\d+)";
    private static String _contentLengthPattern = "\\d{3}\\s(\\d+)";

    public JavaPairRDD<String, LogInfo> Process(JavaRDD<String> logs){
        return logs.mapToPair(word -> {
            Matcher matcher = Pattern.compile(_ipPattern).matcher(word);
            matcher.find();

            String ip = matcher.group(0);
            LogInfo logInfo = new LogInfo();

            matcher.usePattern(Pattern.compile(_contentLengthPattern));

            if (matcher.find()) {
                logInfo.Length = Integer.parseInt(matcher.group(1));
            }
            return new Tuple2<>(ip, logInfo);
        }).reduceByKey((current, next) -> {
            current.Count++;
            current.Length += next.Length;
            Thread.sleep(500);

            return current;
        });
    }
}
