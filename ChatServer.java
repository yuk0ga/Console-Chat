import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by koga on 2017/07/19.
 */
public class ChatServer {


    static int port = 1234;

    static ServerSocket ss;
    static Socket s;


    public static void main(String[] args) {

        try{
            ss = new ServerSocket(port);
            System.out.println("Server Started with port: " + ss.getLocalPort());

            s = ss.accept();
            System.out.println("Connection Established with client: " + s.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line;
            String input;

            while((line = in.readLine()) != null) {
                System.out.println(">>> Client: " + line);
                if ((input = br.readLine()).length()>0) {
                    out.println(input);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}