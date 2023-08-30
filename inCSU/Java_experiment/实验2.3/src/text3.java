import java.util.Scanner;

public class text3 {
    public static void main(String[] args){
        System.out.println("请输入十个数字：");
        Scanner input = new Scanner(System.in);
        int[] array = new int[10];
        int number;
        int index = 1;

        array[0] = input.nextInt();
        for (int i =1;i<10;i++){
            number = input.nextInt();
            for (int j = 0;j<i;j++) {
                if (number == array[j])
                    break;
                else {
                    if (j == i - 1) {
                        array[index] = number;
                        index++;
                    }
                }
            }
        }

        System.out.println("互不相同的数为：");
        for(int i=0; i<index; i++)
            System.out.print(array[i] + " ");
    }
}
