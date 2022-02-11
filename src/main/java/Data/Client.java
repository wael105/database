package Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            System.out.println("Sending connection request to host 127.0.0.1 at port 2000....");

            socket = new Socket("127.0.0.1", 1978);

            System.out.println("Connection is open with the server ....");

            //HTTP Protocol --> RFC (Google HTTP RFC, FTP,

            out = new PrintWriter(socket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("TImE");

            System.out.println(in.readLine());
        }catch (Exception e){
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
