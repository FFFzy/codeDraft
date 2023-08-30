public class IMAPConnectionFactory implements ConnectionFactory{
    @Override
    public Connection createConnection() {
        Connection connection = new IMAPConnection();
        return connection;
    }
}
