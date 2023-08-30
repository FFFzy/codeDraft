import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

/**
 * map函数，统计好友之间的FOF关系列表（FOF关系：潜在好友关系）
 */
public class Map01 extends Mapper<LongWritable, Text, FoF, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value,Context context)
            throws IOException, InterruptedException {
        String lines = value.toString(); // 用户所有的好友列表
        String userAndFriends[] = StringUtils.split(lines, '\t');
        String user = userAndFriends[0];
        String[] friends;
        if(userAndFriends.length == 1){
            return;
        }else if (userAndFriends[1].length() == 1){
            friends = new String[]{userAndFriends[1]};
        }else {
            friends= userAndFriends[1].split(",");
        }

        //好友之间的FOF关系矩阵
        for (int i = 0; i < friends.length; i++) {
            String friend = friends[i];
            context.write(new FoF(user,friend),new IntWritable(0));//输出好友列表，值为0。方便在reduce阶段去除已经是好友的FOF关系。

            for (int j = i+1; j < friends.length; j++) {
                String friend2 = friends[j];
                context.write(new FoF(friend, friend2), new IntWritable(1));//输出好友之间的FOF关系列表，值为1，方便reduce阶段累加
            }
        }
    }
}
