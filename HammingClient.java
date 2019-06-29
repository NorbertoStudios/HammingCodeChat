

import java.io.IOException;
import java.net.Socket;

public class HammingClient
{

    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 8080);

            // the frame here;
            ParityHammingCodeGUI show =  new ParityHammingCodeGUI(100,100,socket);


        }catch (IOException e) {
            System.out.println("Im Here");
        }
    }
}
