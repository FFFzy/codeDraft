

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobFriends {

    public static void main(String[] args) {
        Boolean flag = jobOne();
        if (flag) {
            jobTwo();
        }
    }

    // MapReduce01
    private static Boolean jobOne() {
        Configuration config = new Configuration();

        boolean flag = false;
        try{
            Job job = Job.getInstance(config);

            job.setJarByClass(JobFriends.class);
            job.setJobName("fof one job");

            job.setMapperClass(Map01.class);
            job.setReducerClass(Reduce01.class);

            job.setOutputKeyClass(FoF.class);
            job.setOutputValueClass(IntWritable.class);

            Path input = new Path("……\\src\\LiveJournal.txt");
            FileInputFormat.addInputPath(job, input);

            Path output = new Path("……\\output\\friends_7\\f1");
            //如果文件存在,,删除文件,方便后续调试代码
            if (output.getFileSystem(config).exists(output)) {
                output.getFileSystem(config).delete(output,true);
            }

            FileOutputFormat.setOutputPath(job, output);

            flag = job.waitForCompletion(true);
            if (flag) {
                System.out.println("job1 success...");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    private static Boolean jobTwo() {
        Configuration config =new Configuration();

        Boolean flag = false;
        try {
            Job job = Job.getInstance(config);

            job.setJarByClass(JobFriends.class);
            job.setJobName("fof two job");

            job.setMapperClass(Map02.class);
            job.setReducerClass(Reduce02.class);
            job.setSortComparatorClass(NumSort.class); //sort类
            job.setGroupingComparatorClass(UserGroup.class); //group类
            job.setMapOutputKeyClass(FriendSort.class);
            job.setMapOutputValueClass(Text.class);

            Path input = new Path("……\\output\\friends_7\\f1");
            FileInputFormat.addInputPath(job, input);

            Path output = new Path("……\\output\\friends_7\\f2");
            //如果文件存在,,删除文件,方便后续调试代码
            if (output.getFileSystem(config).exists(output)) {
                output.getFileSystem(config).delete(output,true);
            }

            FileOutputFormat.setOutputPath(job, output);

            flag = job.waitForCompletion(true);
            if (flag) {
                System.out.println("job2 success...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        };
        return flag;
    }
}

