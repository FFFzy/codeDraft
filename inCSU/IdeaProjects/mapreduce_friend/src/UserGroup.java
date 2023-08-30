

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 *  将同一个用户作为一个Group，同时被reduce处理
 */
public class UserGroup extends WritableComparator {
    public UserGroup(){
        super(FriendSort.class,true);
    }

    public int compare(WritableComparable a, WritableComparable b) {
        FriendSort o1 =(FriendSort) a;
        FriendSort o2 =(FriendSort) b;
        return o1.getFriend().compareTo(o2.getFriend());
    }
}
