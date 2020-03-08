package lab1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LogsCombiner extends Reducer<Text, LogInfo, Text, LogInfo> {

    public void reduce(Text key, Iterable<LogInfo> values, Context context) throws IOException, InterruptedException {

        LogInfo logInfo = ReduceHelper.reduce(values);
        context.write(key, logInfo);
    }
}