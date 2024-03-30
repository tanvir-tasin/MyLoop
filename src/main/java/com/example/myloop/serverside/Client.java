package com.example.myloop.serverside;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Client implements Runnable {

    String clientName;
    BufferedReader br;
    BufferedWriter bw;
    final static ArrayList<Client> clients=new ArrayList<>();

    public Client(Socket sc) {
        try {

            OutputStreamWriter o=new OutputStreamWriter(sc.getOutputStream());
            bw=new BufferedWriter(o);
            InputStreamReader i=new InputStreamReader(sc.getInputStream());
            br=new BufferedReader(i);
            clientName= br.readLine();
            clients.add(this);





        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true)
        {
            try {
                String data= br.readLine();
                data=clientName+": "+data+"\n";
                //  synchronized (clients){


                for(Client client:clients)
                {
                    client.bw.write(data);
                    client.bw.flush();
                }

            }
            catch (SocketException e)
            {
                break;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Client(String path)
    {

        try{
            byte[] data= Files.readAllBytes(Paths.get(path));
            Socket socket=new Socket("localhost",6666);
            OutputStream outputStream=socket.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
            socket.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
