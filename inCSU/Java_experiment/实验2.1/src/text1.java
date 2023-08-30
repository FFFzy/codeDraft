import java.util.Scanner;

public class text1 {
    public static void main(String[] args){
        System.out.println("请输入1-100之间的整数：");
        Scanner input = new Scanner(System.in);
        int[] list = new int[100];
        int count = 0;
        int number;

        for(int i=0;i<100;i++){
            number = input.nextInt();
            if(number!=0){
                if(number>=1 && number<=100)
                    list[i]=number;
            }
            else break;
        }

        for (int i=0;i<=100;i++){
            number = i+1;
            for (int j=0;j<100;j++){
                if (list[j]!=0){
                    if (list[j]==number)
                        count++;
                }
            }
            if (count!=0)
                System.out.println(number + "  出现" + count + "次");
            count = 0;
        }
    }
}
