import java.util.Arrays;

public class Client {
    public static void main(String args[]){
        int [] a = {8, 33, 87, 97, 12, 108, 90, 201};
        DataOperation dataOperation;
        dataOperation = (DataOperation)XMLUtil.getBean();

        System.out.print("排序前的数列：");
        System.out.println(Arrays.toString(a));

        dataOperation.sort(a);

        System.out.print("排序后的数列：");
        System.out.println(Arrays.toString(a));

        int c = dataOperation.search(a,87);
        System.out.print("数字87在数组中的位置：");
        System.out.println(c);
    }
}
