
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * reduce函数
 */
public class Reduce02 extends Reducer<FriendSort, Text, Text, Text> {

    @Override
    protected void reduce(FriendSort user, Iterable<Text> friends, Context context)
            throws IOException, InterruptedException {

        String msg = "";
        //拼接推荐好友
        for(Text friend :friends){
            System.out.println("***************Reduce02*****************");
            msg += friend.toString()+",";
            System.out.println(msg);
        }
        context.write(new Text(user.getFriend()), new Text(msg));
    }
}
