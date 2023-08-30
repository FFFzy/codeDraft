package text1;

import java.util.Scanner;

public class text1 {
    public static void main(String[] args){
        int k,S=0;
        System.out.println("请输入一个在0至1000的数字：");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        if(i<0||i>1000)
            System.out.println("输入的数字超出范围");
        else{
            do {
                k = i%10;
                i = i/10;
                S = S+k;
            }while(i>0);
            System.out.println("该整数各位数字相加和为："+S);
        }
    }
}
