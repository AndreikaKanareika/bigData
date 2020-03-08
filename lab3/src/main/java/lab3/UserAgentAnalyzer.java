package lab3;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentAnalyzer {

    private static String pattern = "\"([^\"]*)\"$";

    public Map<String, Long> GetBrowsersStatistic(JavaRDD<String> logs) {
        return new TreeMap(
            logs.mapToPair(str -> {
                Matcher matcher = Pattern.compile(pattern).matcher(str);
                matcher.find();

                String userAgentInfo = matcher.group(matcher.groupCount());
                String browserName = UserAgent.parseUserAgentString(userAgentInfo).getBrowser().getName();

                return new Tuple2(browserName, 0);
            }).countByKey()
        );
    }
}
