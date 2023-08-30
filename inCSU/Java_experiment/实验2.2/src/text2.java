import java.util.Scanner;

public class text2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double[] list = new double[10];
        System.out.println("请输入十个数字：");

        for (int i = 0;i<10;i++)
            list[i] = input.nextDouble();

        int n = indexofSmallestElement(list);

        System.out.println("最小元素的最小下标值为"+n);
    }

    public static int indexofSmallestElement(double[] array){
        int min = 0;
        double number = array[0];
        for (int i=1;i<array.length;i++){
            if (number>array[i]) {
                number = array[i];
                min = i;
            }
        }
        return min;
    }
}
