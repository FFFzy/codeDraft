import java.io.File;
        import java.util.Scanner;

public class text2 {
    public static void main(String[] args) throws Exception{
        if (args.length!=1){
            System.out.println("文件"+args[0]+"不存在");
            System.exit(1);
        }

        File file = new File(args[0]);
        int numberOfChar = 0;
        int numberOfWord = 0;
        int numberOfLines = 0;

        try(Scanner input = new Scanner(file)){
            while (input.hasNext()){
                String ch = input.nextLine();
                numberOfChar = numberOfChar + ch.length();
                String[] words = ch.split(" ");
                numberOfWord = numberOfWord + words.length;
                numberOfLines++;
            }
            System.out.println("File " + args[0] +" has\n"
                    + numberOfChar + " characters\n"
                    + numberOfWord + " words\n"
                    + numberOfLines + " lines");
        }
    }
}
