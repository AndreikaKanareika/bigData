package lab1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogsMapper extends Mapper<Object, Text, Text, LogInfo> {

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String log = value.toString();
        UserAgentAnalyzer userAgentAnalyzer = new UserAgentAnalyzer();
        userAgentAnalyzer.AnalyzeBrowserName(value.toString(), context);

        IpProcessor ipProcessor = new IpProcessor();
        LogInfo result = ipProcessor.Process(log);

        context.write(new Text(result.Ip), result);
    }
}