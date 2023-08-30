import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class text3 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Exercise9_19.txt");
        if (file.exists()){
            System.out.println("该文件已存在");
        }

        PrintWriter output = new PrintWriter(file);
        for (int i=0;i<10;i++){
            if(i==9)
                output.print( (int)(Math.random()*100) );
            else
                output.print( (int)(Math.random()*100) + " ");
        }
        output.close();

        Scanner input = new Scanner(file);
        ArrayList<Integer> a = new ArrayList<>();
        while(input.hasNext()){
            int number = input.nextInt();
            a.add(number);
        }
        input.close();

        Collections.sort(a);
        for(int e: a)
            System.out.print(e + " ");
        System.out.println();
    }
}
