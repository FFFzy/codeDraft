public class text4 {
    public static void main(String[] args){
        int x = 0;
        for(int i=100;i<=200;i++) {
            int p = i%5;
            int q = i%6;
            if ((p == 0 && q != 0) || (p != 0 && q == 0)) {
                System.out.print(i + " ");
                x++;
                if (x%10==0)
                    System.out.println();
            }
        }
    }
}
