package ConsoleChat;

import java.io.*;
import java.net.Socket;

/**
 * Created by koga on 2017/07/19.
 */
public class ChatClient {

    static Socket s;

    static int port = 1234;

    public static void main(String[] args) {

        try{
            s = new Socket(args[0], port);
            System.out.println("Connected to: " + s.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input;

            while((input = br.readLine()).length()>0) {
                out.println(input);
                String line = in.readLine();
                if (line != null) {
                    System.out.println(">>> Server: " + line);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
