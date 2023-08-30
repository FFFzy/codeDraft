import java.util.Scanner;

public class text1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入两个整数：");
        boolean goon = true;
        do {
            try {
                String a = input.next();
                String b = input.next();
                System.out.println(a + "+" + b + "=" + (Integer.valueOf(a) + Integer.valueOf(b)));
                goon = false;
            }
            catch (NumberFormatException ex){
                System.out.println("输入错误，请重新输入：");
            }
        }while (goon);
    }
}
