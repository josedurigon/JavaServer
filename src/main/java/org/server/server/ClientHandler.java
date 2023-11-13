package org.server.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

//public class ClientHandler extends Thread {
//    private Socket clientSocket;
//
//    public ClientHandler(Socket clientSocket) {
//        this.clientSocket = clientSocket;
//    }
//
//    public void run() {
//        try {
//            // Create input and output streams for the client socket
//            InputStream input = clientSocket.getInputStream();
//            OutputStream output = clientSocket.getOutputStream();
//
//            // Read data from the client
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//            String clientData;
//            while ((clientData = reader.readLine()) != null) {
//                System.out.println("Received from client: " + clientData);
//
//                // You can process client data here and send a response back
//
//                // Example: Sending a response
//                String response = "Server response: Hello, client!";
//                output.write(response.getBytes());
//                output.flush();
//            }
//        } catch (SocketException e) {
//            // Handle the SocketException (connection terminated by client) here
//            System.out.println("Client connection terminated abruptly.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // Close the client socket when done
//                clientSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandler = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientusername;

    public  ClientHandler(Socket socket){
        try{
            this.socket= socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter((socket.getOutputStream())));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientusername = bufferedReader.readLine();
            clientHandler.add(this);
            broadcastMessage("Server: "+clientusername+ " has entered the server");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    @Override
    public void run() {
        String messageFromClient;

        while(socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);

            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String message){
        for(ClientHandler clientHandler1 : clientHandler){
            try{
                if(!clientHandler1.clientusername.equals(clientusername)){
                     clientHandler1.bufferedWriter.write(message);
                     clientHandler1.bufferedWriter.newLine();
                     clientHandler1.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket, bufferedReader,bufferedWriter);
            }
        }
    }

    public void removeClienthandler(){
        clientHandler.remove(this);
        broadcastMessage("Server: "+ clientusername+" has left the chat");
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClienthandler();
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}