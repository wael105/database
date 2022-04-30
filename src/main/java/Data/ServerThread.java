package Data;

import java.io.*;
import java.net.*;
import java.util.Date;

public class ServerThread extends Thread{

    Socket socket;
    ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input = in.readLine();

            if (input.equalsIgnoreCase("Time")) {
                Date d = new Date();
                String time = d.toString();
                out.println(time);
            } else {
                out.println("Unknown Command");
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {
                if (socket != null) socket.close();
                if (in!= null) in.close();
                if (out != null) out.close();
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

}
