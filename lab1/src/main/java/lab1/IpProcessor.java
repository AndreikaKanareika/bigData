package lab1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpProcessor  {

    private static String _ipPattern = "ip(\\d+)";
    private static String _contentLengthPattern = "\\d{3}\\s(\\d+)";

    public LogInfo Process(String log) {
        Matcher matcher = Pattern.compile(_ipPattern).matcher(log);
        matcher.find();

        String ip = matcher.group(0);
        LogInfo logInfo = new LogInfo();
        logInfo.Ip = ip;
        logInfo.Count = 1;

        matcher.usePattern(Pattern.compile(_contentLengthPattern));
        if (matcher.find()) {
            logInfo.Length = Integer.parseInt(matcher.group(1));
        }
        return logInfo;
    }
}
