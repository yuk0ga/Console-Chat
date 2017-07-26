
import java.io.*;
import java.net.Socket;

/**
 * Created by koga on 2017/07/19.
 */
public class ChatClient {

    static Socket s;

    static int port = 1234;

    static String line;

    public static void main(String[] args) {

        try{
            s = new Socket(args[0], port);
            System.out.println("Connected to: " + s.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input;

            while(!(input = br.readLine()).equals("bye")) {
                out.println(input);
                if((line = in.readLine()) != null) {
                    System.out.println(">>> Server: " + line);
                }else{
                    break;
                }
            }
        }catch(IOException e){
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


//Receives
//      String line;
//      BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//      while((line = in.readLine()) != null) {
//        System.out.println(">>> Server: " + line);
//      }