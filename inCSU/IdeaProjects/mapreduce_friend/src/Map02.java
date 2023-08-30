

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

/**
 * map函数，每个用户的推介好友列表，并按推介指数从大到小排序
 */
public class Map02 extends Mapper<LongWritable, Text, FriendSort, Text > {

    @Override
    protected void map(LongWritable key, Text value,Context context)
            throws IOException, InterruptedException {

        String lines = value.toString();
        String friend01 = StringUtils.split(lines,' ')[0];
        String friend02 = StringUtils.split(lines,' ')[1];	//推介的好友
        int hot = Integer.parseInt(StringUtils.split(lines,' ')[2]);	// 该推介好友的推介系数

        System.out.println("**************Map02******************");
        System.out.println(friend01+"  "+friend02+"  "+hot);
        System.out.println(friend02+"  "+friend01+"  "+hot);
        context.write(new FriendSort(friend01,hot),new Text(friend02+":"+hot));	 //mapkey输出用户和好友推介系数
        context.write(new FriendSort(friend02,hot),new Text(friend01+":"+hot));	//好友关系是相互的

    }
}
