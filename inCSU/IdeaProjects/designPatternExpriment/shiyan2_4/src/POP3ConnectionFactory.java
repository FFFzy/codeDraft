public class POP3ConnectionFactory implements ConnectionFactory{
    @Override
    public Connection createConnection() {
        Connection connection = new POP3Connection();
        return connection;
    }
}
