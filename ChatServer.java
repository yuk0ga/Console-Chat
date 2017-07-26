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

    public static void main(String[] args) {

        Receivemsg receive = new Receivemsg();
        Sendmsg send = new Sendmsg();

        Thread receiver = new Thread(receive);
        Thread sender = new Thread(send);

        try{
            ss = new ServerSocket(port);
            System.out.println("Server Started with port: " + ss.getLocalPort());

            s = ss.accept();
            System.out.println("Connection Established with client: " + s.getRemoteSocketAddress());

            receiver.start();
            sender.start();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}


class Sendmsg implements Runnable {
    @Override
    public void run() {
        String input;
        PrintWriter out = null;
        try {
            out = new PrintWriter(ChatServer.s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!(input = br.readLine()).equals("bye")){
                out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//Sends
//      String input;
//      PrintWriter out = new PrintWriter(s.getOutputStream(), true);
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//      while ((input = br.readLine()).length() > 0){
//        out.println(input);
//      }


class Receivemsg implements Runnable {
    @Override
    public void run() {
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(ChatServer.s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while((line = in.readLine()) != null) {
                System.out.println("> Client: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//Receives
//      String line;
//      BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//      while((line = in.readLine()) != null) {
//        System.out.println(">>> Server: " + line);
//      }


