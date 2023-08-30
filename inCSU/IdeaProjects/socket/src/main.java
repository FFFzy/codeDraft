//main.java
public class main {
    public static void main(String[] args){
        try{
            new multiServer().start(23);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
