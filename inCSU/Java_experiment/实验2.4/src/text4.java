import java.util.Scanner;

public class text4 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("输入list1:");
        int length1 = input.nextInt();
        int[] list1 = new int[length1];
        for (int i=0;i<length1;i++)
            list1[i] = input.nextInt();

        System.out.println("请输入list2");
        int length2 = input.nextInt();
        int[] list2 = new int[length2];
        for (int i=0;i<length2;i++)
            list2[i] = input.nextInt();

        if (equal(list1,list2))
            System.out.println("这两个数列是相同的");
        else System.out.println("这两个数列是不同的");
    }

    public static boolean equal(int[ ] list1, int[ ] list2){
        if (list1.length!=list2.length)
            return false;
        else {
            boolean isEqual=false;
            for (int i=0;i<list1.length;i++) {
                if (list1[i] != list2[i]) {
                    isEqual = false;
                    break;
                }
                else isEqual = true;
            }
            return isEqual;
        }
    }
}
