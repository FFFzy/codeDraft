import java.io.IOException;

public class MapReduce2 {
        private static class Mapper1 extends Mapper<LongWritable, Text, Text, Text> {
            Text k = new Text();
            Text v = new Text();

            @Override
            protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
                String[] split = value.toString().split("\t");
                /* s#开头标识孙子 */
                k.set(split[1].trim());
                v.set("s#\t" + split[0]);
                context.write(k, v);

                /* g#开头标识爷爷 */
                k.set(split[0].trim());
                v.set("g#\t" + split[1]);
                context.write(k, v);
            }
        }

        private static class Reducer1 extends Reducer<Text, Text, Text, NullWritable> {
            Text k = new Text();

            @Override
            protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
                /* 定义两个缓冲变量，做结果的临时存放 */
                LinkedList<String> son = new LinkedList<>();
                LinkedList<String> grand = new LinkedList<>();

                for (Text value : values) {
                    String[] split = value.toString().split("\t");
                    /* 处理孙子 */
                    if ("s#".equals(split[0]))
                        son.add(split[1].trim());
                    /* 处理爷爷 */
                    if ("g#".equals(split[0]))
                        grand.add(split[1].trim());
                }

                /* 判断是否存在三代关系，也就是两个缓冲变量是否至少有一个值 */
                if (son.size() > 0 && grand.size() > 0) {
                    for (String s : son) {
                        for (String g : grand) {
                            /* 双层遍历展开关系 */
                            k.set(s + "\t" + key.toString() + "\t" + g);
                            context.write(k, NullWritable.get());
                        }
                    }
                }
            }
        }

package mr.day04;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.LinkedList;

    /**
     * @ClassName: SonDriver
     * @Description:
     * @Author: xuezhouyi
     * @Version: V1.0
     **/
    public class SonDriver {
        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf);
            job.setJarByClass(SonDriver.class);
            job.setMapperClass(Mapper1.class);
            job.setReducerClass(Reducer1.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            FileSystem fileSystem = FileSystem.get(conf);
            if (fileSystem.exists(new Path(args[1]))) {
                fileSystem.delete(new Path(args[1]), true);
            }
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }

        private static class Mapper1 extends Mapper<LongWritable, Text, Text, Text> {
            Text k = new Text();
            Text v = new Text();

            @Override
            protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
                String[] split = value.toString().split("\t");
                /* s#开头标识孙子 */
                k.set(split[1].trim());
                v.set("s#\t" + split[0]);
                context.write(k, v);

                /* g#开头标识爷爷 */
                k.set(split[0].trim());
                v.set("g#\t" + split[1]);
                context.write(k, v);
            }
        }

        private static class Reducer1 extends Reducer<Text, Text, Text, NullWritable> {
            Text k = new Text();

            @Override
            protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
                /* 定义两个缓冲变量，做结果的临时存放 */
                LinkedList<String> son = new LinkedList<>();
                LinkedList<String> grand = new LinkedList<>();

                for (Text value : values) {
                    String[] split = value.toString().split("\t");
                    /* 处理孙子 */
                    if ("s#".equals(split[0]))
                        son.add(split[1].trim());
                    /* 处理爷爷 */
                    if ("g#".equals(split[0]))
                        grand.add(split[1].trim());
                }

                /* 判断是否存在三代关系，也就是两个缓冲变量是否至少有一个值 */
                if (son.size() > 0 && grand.size() > 0) {
                    for (String s : son) {
                        for (String g : grand) {
                            /* 双层遍历展开关系 */
                            k.set(s + "\t" + key.toString() + "\t" + g);
                            context.write(k, NullWritable.get());
                        }
                    }
                }
            }
        }
    }
