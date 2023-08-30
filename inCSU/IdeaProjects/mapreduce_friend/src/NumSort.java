
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 将key根据用户名和次数排序
 */
public class NumSort extends WritableComparator {
    public NumSort(){
        super(FriendSort.class,true);
    }

    public int compare(WritableComparable a, WritableComparable b) {
        FriendSort o1 =(FriendSort) a;
        FriendSort o2 =(FriendSort) b;

        int r =o1.getFriend().compareTo(o2.getFriend());
        if(r==0){
            return -Integer.compare(o1.getHot(), o2.getHot());
        }
        return r;
    }
}
