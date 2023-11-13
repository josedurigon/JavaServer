package org.server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
   private ServerSocket serverSocket;

   public Server(ServerSocket serverSocket){
       this.serverSocket = serverSocket;
   }

   public void StartServer(){
       try {
           while (!serverSocket.isClosed()) {
               Socket socket = serverSocket.accept();
               System.out.println("A new client has connected");
               ClientHandler clientHandler = new ClientHandler(socket);

               Thread thread = new Thread(clientHandler);
               thread.start();

           }
       }catch (IOException e){


        }

   }

   public void CloseServerSocket(){
       try {
           if (!serverSocket.isClosed()) {
               serverSocket.close();
           }
           else{
               throw new RuntimeException("Socket ja está fechado!!");
           }
       }
       catch (IOException e){
           e.printStackTrace();
       }
   }
}

//Na main
/*
* public static void Main(String [] args){
*   ServerSocket serverSocket = new ServerSocket(123456);
* }
* */

