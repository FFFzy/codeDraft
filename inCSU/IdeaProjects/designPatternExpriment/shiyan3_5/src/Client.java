public class Client {
    public static void main(String args[]){
        Business business;
        business = (Business)XMLUtil.getBean();
        business.method();
    }
}
