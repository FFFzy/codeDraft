

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *  FriendSort作为key和value，FriendSort：用户名+FOF系数
 *  用户名一致，FOF系数从大到小排序。 很容易得到一个用户的好友推介的列表
 */
public class FriendSort implements WritableComparable<FriendSort> {

    private String friend;
    private int hot;

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }


    public FriendSort() {
        super();
    }

    public FriendSort(String friend, int hot) {
        this.friend = friend;
        this.hot = hot;
    }
    //反序列化
    @Override
    public void readFields(DataInput in) throws IOException {
        this.friend=in.readUTF();
        this.hot=in.readInt();
    }
    //序列化
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(friend);
        out.writeInt(hot);
    }
    //判断是否为同一用户,并通过hot值排序
    @Override
    public int compareTo(FriendSort newFriend) {
        System.out.println(friend+"-------"+newFriend.getFriend());

        int c = friend.compareTo(newFriend.getFriend());
        int e = -Integer.compare(hot, newFriend.getHot());
        if (c==0) {
            return e;
        }
        return c;
    }
}
