public class Client {
    public static void main(String args[]){
        VirtualUserGenerator_Eager s1,s2;
        s1 = VirtualUserGenerator_Eager.getInstance();
        s2 = VirtualUserGenerator_Eager.getInstance();
        System.out.println(s1 == s2);

        VirtualUserGenerator_Lazy s3,s4;
        s3 = VirtualUserGenerator_Lazy.getInstance();
        s4 = VirtualUserGenerator_Lazy.getInstance();
        System.out.println(s3 == s4);

        VirtualUserGenerator_IoDH s5,s6;
        s5 = VirtualUserGenerator_IoDH.getInstance();
        s6 = VirtualUserGenerator_IoDH.getInstance();
        System.out.println(s5 == s6);
    }
}
