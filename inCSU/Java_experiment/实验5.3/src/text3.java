import java.util.ArrayList;
import java.util.Scanner;

public class text3 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入五个字符串:");
        MyStack myStack = new MyStack();
        for (int i=0;i<5;i++){
            String o = input.next();
            myStack.push(o);
        }

        System.out.println("逆序输出的结果为：");
        for (int i=0;i<5;i++)
            System.out.print(myStack.pop()+" ");
    }
}

class MyStack extends ArrayList{
    MyStack(){

    }

    public Object pop(){
        Object o = this.get(this.size()-1);
        this.remove(this.size()-1);
        return o;
    }

    public void push(Object o){
        this.add(o);
    }
}
