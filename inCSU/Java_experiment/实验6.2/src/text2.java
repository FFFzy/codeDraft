import java.util.Scanner;

public class text2 {
    public static void main(String[] args){
        int[] a = new int[100];
        for (int i=0;i<100;i++)
            a[i] = (int)(Math.random()*100);
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("请输入数组下标：");
            int arrayIndex = input.nextInt();
            System.out.println("数组元素a["+arrayIndex+"]为："+a[arrayIndex]);
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("out of bounds");
        }
    }
}
