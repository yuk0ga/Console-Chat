
//package ConsoleChat;


import java.io.*;
import java.net.Socket;

/**
 * Created by koga on 2017/07/19.
 */
public class ChatClient {

    static Socket s;

    static int port;

    static String name;

    public static void main(String[] args) {

        Receivemsg1 receive = new Receivemsg1();
        Sendmsg1 send = new Sendmsg1();

        Thread receiver = new Thread(receive);
        Thread sender = new Thread(send);

        try{
            port = Integer.parseInt(args[2]);
            s = new Socket(args[1], port);
            name = args[0];
            System.out.println("Connected to: " + s.getRemoteSocketAddress());
            System.out.println("Welcome " + name + "!");

            receiver.start();
            sender.start();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

class Sendmsg1 implements Runnable {
    @Override
    public void run() {
        String input;
        PrintWriter out = null;
        try {
            out = new PrintWriter(ChatClient.s.getOutputStream(), true);
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


class Receivemsg1 implements Runnable {
    @Override
    public void run() {
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(ChatClient.s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while((line = in.readLine()) != null) {
                System.out.println("> Server: " + line);
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