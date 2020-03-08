package lab1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LogsReducer extends Reducer<Text, LogInfo, Text, Text> {

    public void reduce(Text key, Iterable<LogInfo> values, Context context) throws IOException, InterruptedException {

        LogInfo logInfo = ReduceHelper.reduce(values);
        float avg = (float) logInfo.Length / logInfo.Count;
        Text logInfoText = new Text(String.format(",%f,%d", avg, logInfo.Length));
        context.write(key, logInfoText);
    }
}