package ConsoleChat;

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

        Receiver receiver = new Receiver();
        Sender sender = new Sender();

        Thread receive = new Thread(receiver);
        Thread send = new Thread(sender);

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

            receive.start();
            send.start();

//            while((line = in.readLine()) != null) {
//                System.out.println(">>> Client: " + line);
//            }
//            while ((input = br.readLine()).length()>0) {
//                out.println(input);
//            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

class Receiver implements Runnable {
    public void run(){
        while(ChatServer.line.length() > 0) {
            System.out.println(">>> Client: " + ChatServer.line);
        }
    }
}

class Sender implements Runnable {
    @Override
    public void run() {
        while(ChatServer.input.length() > 0){
            ChatServer.out.println(ChatServer.input);
        }
    }
}