package lab1;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentAnalyzer {

    private static String pattern = "\"([^\"]*)\"$";

    public void AnalyzeBrowserName(String log, Mapper.Context context) {

        Matcher matcher = Pattern.compile(pattern).matcher(log);
        matcher.find();

        String userAgentInfo = matcher.group(matcher.groupCount());
        String browserName = UserAgent.parseUserAgentString(userAgentInfo).getBrowser().getName();

        context.getCounter("UserAgentsInfo", browserName).increment(1);
    }
}
