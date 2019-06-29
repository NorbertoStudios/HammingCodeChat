

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MyServer
{
    private final int MAX_CLIENTS = 10;

    ClientHandler[] ch = new ClientHandler[MAX_CLIENTS];

    public static void main(String[] args) {
        MyServer server = new MyServer();
    }

    public  MyServer()
    {
        System.out.println("Starting the server...");

        try{
            ServerSocket serverSocket = new ServerSocket(8080);

            System.out.println("Server is accepting Communication on port: 8080");

            for (int i = 0; i < MAX_CLIENTS; i++) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Connection Client Accepted.");
                    ch[i] = new ClientHandler(socket);
                    System.out.println("Client Handler " + i + " Ready");

            }

        }catch(IOException e){}

    }

    public class ClientHandler implements Runnable
    {
        DataInputStream     input;
        DataOutputStream    output;
        Thread              t;
        Socket              socket;




        public ClientHandler(Socket socket)
        {
            this.socket = socket;

            try {
                input   = new DataInputStream(socket.getInputStream());
                output  = new DataOutputStream(socket.getOutputStream());
            }catch (IOException e){ }

            t = new Thread(this);
            t.start();

        }

        @Override
        public void run()
        {
            while (true)
            {
                try {
                    String message = input.readUTF();

                    System.out.println(message.length());

//                    int intB = 49;

                    if(message.length() == 13)
                    {
                        Random rand = new Random();

                        int randomBit = rand.nextInt(7);
                        String b = "";
                        int myM = message.charAt(randomBit+6);

                        System.out.println("Before "+myM);

                        if(myM == 49)
                        {
                            // 1 to 0
                            myM = 48;

                        }else
                        {
                            // 0 to 1
                            myM = 49;
                        }
                        //myM = myM - intB;

                        b = Character.toString(myM);

                        System.out.println(message.substring(randomBit+6));

                        System.out.println(randomBit+6);

                        System.out.println(myM);

                        System.out.println("1 " + message);
                        //if(message.length() <= 12)
                        message = message.substring(0, (randomBit+6)) + b + message.substring((randomBit+6)+1, message.length());
                        System.out.println("2 "+message);

                    }

                    // random mess a bit

                    System.out.println(message);

                    for (int i = 0; i < MAX_CLIENTS; i++)
                    {
                        if(ch[i] != null)
                        {
                            System.out.println(i);
                            ch[i].output.writeUTF(message);
                        }

                    }
                }catch (IOException e) {}

            }


        }
    }
}
