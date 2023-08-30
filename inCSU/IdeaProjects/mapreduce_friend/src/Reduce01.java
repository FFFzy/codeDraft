import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

/**
 * reduce函数，统计全部的FOF关系列表的系数
 */
public class Reduce01 extends Reducer<FoF, IntWritable, Text, NullWritable> {

    @Override
    protected void reduce(FoF key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        boolean f = true;
        for(IntWritable i : values){
            if (0==i.get()) { //已经是好友关系
                f=false;
                break;
            }
            sum+=i.get();	//累计，统计FOF的系数
        }
        System.out.println("******************Reduce01*******************");
        if (f) {
            String msg = StringUtils.split(key.toString(), '\t')[0]+" "+StringUtils.split(key.toString(), '\t')[1]+" "+sum;
            System.out.println(msg);
            context.write(new Text(msg), NullWritable.get()); //输出key为潜在好友对，值为出现的次数
        }
    }
}
