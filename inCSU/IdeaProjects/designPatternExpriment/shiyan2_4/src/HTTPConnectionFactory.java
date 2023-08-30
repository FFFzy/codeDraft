public class HTTPConnectionFactory implements ConnectionFactory{
    @Override
    public Connection createConnection() {
        Connection connection = new HTTPConnection();
        return connection;
    }
}
