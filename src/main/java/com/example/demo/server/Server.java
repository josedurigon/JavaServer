package com.example.demo.server;

import com.example.demo.Models.Pacientes;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

   private final ServerSocket serverSocket;
   public Server(ServerSocket serverSocket){
       this.serverSocket = serverSocket;
   }

   public void StartServer(){
       try {
           while (!serverSocket.isClosed()) {
               Socket socket = serverSocket.accept();
               System.out.println("A new client has connected");

               ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
               ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

               Pacientes paciente = (Pacientes)is.readObject();
               System.out.println(paciente.getNome());
              // ClientHandler clientHandler = new ClientHandler(socket);
               //Thread thread = new Thread(clientHandler);
               //thread.start();  // Start the thread first

           }
       }catch (IOException e){


        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e.getMessage());
       }

   }

   public void handleClientConnection(Socket clientSocket){
       try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
           // Read the Pacientes object from the client
           Pacientes receivedPacientes = (Pacientes) objectInputStream.readObject();
           // Process the received object
           processReceivedObject(receivedPacientes);

       } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
       }
   }

    private void processReceivedObject(Pacientes pacientes) {
        // Implement your logic to process the received Pacientes object
        System.out.println("Received Pacientes object: " + pacientes.getNome());
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

