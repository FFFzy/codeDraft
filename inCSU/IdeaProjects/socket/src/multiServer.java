//multiServer.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class multiServer {
    private ServerSocket serverSocket;

    public void start(int port)throws Exception{
        serverSocket = new ServerSocket(port);
        while (true){
            clientHandler client = new clientHandler(serverSocket.accept());
            clientList.clientList.add(client);
            client.start();
        }
    }

    public void stop() throws Exception{
        serverSocket.close();
    }

    public static class clientHandler extends Thread{
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public clientHandler(Socket socket){
            this.clientSocket = socket;
        }

        public void run(){
            try{
                out = new PrintWriter(clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null){
                    System.out.println("msg from client" + clientList.clientList.indexOf(this) + "--msg:" + inputLine);
                    if(".".equals(inputLine)){
                        out.println("good bye");
                        break;
                    }
                    out.println("server got msg: "+inputLine);
                }
                in.close();
                out.close();
                clientSocket.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
