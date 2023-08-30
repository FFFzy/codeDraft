import java.util.Scanner;

public class text1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入密码，且符合以下规则："+"\n1.密码必须至少有8个字符。" + "\n2.密码只能包括数字和字母。" + "\n3.密码必须至少有2个数字。" + "\n请输入一个密码：");
        String password = input.nextLine();

        if (Check(password))
            System.out.println("Valid Password");
        else
            System.out.println("Invalid Password");
    }

    public static boolean Check(String password){
        int number = 0;
        for (int i=0;i<password.length();i++) {
            if (Character.isLetter(password.charAt(i)))
                continue;
            else if (Character.isDigit(password.charAt(i)))
                number++;
            else
                return false;
        }
        if (number>=2 && password.length()>=8)
            return true;
        else
            return false;
    }
}
