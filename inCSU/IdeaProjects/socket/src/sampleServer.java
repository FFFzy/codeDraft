//sampleServer.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class sampleServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    //建立服务端，等待连接
    public void start(int port) throws Exception{
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        System.out.println("get link");
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
            if(inputLine.equals("goodbye")){
                out.println("bye\n");
                stop();
                break;
            }else{
                out.println("server got msg: "+inputLine);
            }
        }
    }

    //关闭连接以及相关的io流
    public void stop() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args){
        try {
            sampleServer server = new sampleServer();
            server.start(23);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
