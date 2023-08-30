import java.util.Scanner;

public class text3 {
    static boolean goOn = true;
    public static void main(String[] args){
        do {
            try {
                method1();
            } catch (NumberFormatException ex) {
                System.out.println("NumberFormatException");
                System.out.println("");
            }
        }while (goOn);
    }

    static void method1(){
        try{
            method2();
        }catch (ArithmeticException ex){
            System.out.println("除数为0（ArithmeticException）");
            System.out.println("");
        }
    }

    static void method2(){
        try{
            method3();
        }catch (IndexOutOfBoundsException ex){
            System.out.println("数组越界错误(IndexOutOfBoundsException)");
            goOn = false;
        }
    }

    static void method3(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数字选择抛出的异常：");
        String[] strings = {"1","a","0"};
        switch (input.nextInt()) {
            case 1:
                int a = Integer.valueOf(strings[0]) + Integer.valueOf(strings[1]);
            case 2:
                int b = Integer.valueOf(strings[0]) / Integer.valueOf(strings[2]);
            case 3:
                String c = strings[3];
        }
    }
}
