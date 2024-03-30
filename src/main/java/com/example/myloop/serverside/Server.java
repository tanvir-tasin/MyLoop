package com.example.myloop.serverside;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {

     try {

            System.out.println("Waiting for client");
            ServerSocket ss = new ServerSocket(6666);

            while (true) {

                Socket socket = ss.accept();
                Client client = new Client(socket);
                Thread thread = new Thread(client);
                thread.start();
            }



        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Server(String path)
    {
        try{
            ServerSocket serverSocket=new ServerSocket(6666);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[10 * 1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data);

                }
                buffer.flush();
                byte[] filedata = buffer.toByteArray();
                FileOutputStream outputStream = new FileOutputStream(path);
                outputStream.write(filedata);
                outputStream.close();
                inputStream.close();
                socket.close();

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
