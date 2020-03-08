package lab1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Main {

    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(LogsMapper.class);
        job.setMapperClass(LogsMapper.class);
        job.setCombinerClass(LogsCombiner.class);
        job.setReducerClass(LogsReducer.class);
        job.setSortComparatorClass(IpComparator.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LogInfo.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
