public class Client {
    public static void main(String args[]){
        Multiton m1,m2,m3,m4;
        m1 = Multiton.getInstance();
        m2 = Multiton.getInstance();
        m3 = Multiton.getInstance();
        m4 = Multiton.getInstance();

        System.out.println(m1 == m2);
        System.out.println(m1 == m3);
        System.out.println(m1 == m4);
    }
}
