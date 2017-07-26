//package ConsoleChat;

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

    static String line;
    static String input;

    static PrintWriter out;


    public static void main(String[] args) {

        try{
            ss = new ServerSocket(port);
            System.out.println("Server Started with port: " + ss.getLocalPort());

            s = ss.accept();
            System.out.println("Connection Established with client: " + s.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            line = in.readLine();
            input = br.readLine();

            while(true){
                if (!line.equals("bye")) {
                    System.out.println(">>> Client: " + line);
                }
                if (input.length()>0) {
                    out.println(input);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}


