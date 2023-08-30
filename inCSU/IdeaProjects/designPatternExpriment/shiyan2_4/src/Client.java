public class Client {
    public static void main(String args[]){
        ConnectionFactory factory;
        Connection connection;
        factory = (ConnectionFactory)XMLUtil.getBean();
        connection = factory.createConnection();
        connection.create();
    }
}
