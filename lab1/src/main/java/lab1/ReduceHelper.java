package lab1;

public class ReduceHelper {
    public static LogInfo reduce(Iterable<LogInfo> values) {
        LogInfo result = new LogInfo();
        for (LogInfo x : values) {
            result.Ip = x.Ip;
            result.Count += x.Count;
            result.Length += x.Length;
        }
        return result;
    }
}