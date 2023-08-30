import java.util.StringTokenizer;
public class MapReduce1 {
    public static class Map extends Mapper<Object,Text,Text,IntWritable>{
        public void map(Object key,Text value,Context context){
            //把读入的每一行转换为String
            String line=value.toString();
            //以“\n”切分数据
            StringTokenizer tokenizer=new StringTokenizer(line,"\n");
            while(tokenizer.hasMoreElements()){
                //以空格切分数据
                StringTokenizer tokenizerLine=new StringTokenizer(tokenizer.nextToken());
                //获取姓名
                String nameString=tokenizerLine.nextToken();
                //获取成绩
                String numString=tokenizerLine.nextToken();
                int numInt=Integer.parseInt(numString);
            }
        }
    }
    public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>{
        public void reduce(Text key,Iterable<IntWritable> values,Context context){
            //计算给同学的总成绩
            int sum=0;
            //获取科目数目
            int count=0;
            Iterator<IntWritable> iterable=values.iterator();
            while(iterable.hasNext()){
                sum+=iterable.next().get();
                count++;
            }
            int avg=(int)sum/count;
        }
    }
    public static void main(String[] args){
        Configuration conf = new Configuration();
        Job job = new Job(conf);
        job.setJarByClass(Average.class);
        job.setJobName("Average");
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("/averageInput.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/averageOutput.txt"));
        job.waitForCompletion(true);
    }
}

