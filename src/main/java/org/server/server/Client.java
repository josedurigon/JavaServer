package org.server.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;

    private String userName;

    private ObjectInput objectInput;
    private ObjectOutputStream objectOutputStream;

    public Client(Socket socket, String userName){
        try {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInput = new ObjectInputStream(socket.getInputStream());

            this.userName = userName;
        }
        catch (IOException ex){
            closeEverything(socket, objectInput, objectOutputStream);
        }
    }

    public void sendObject() {
        try {

            Object obj = null;//Apenas para compilar
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageTosend = scanner.nextLine();

            }
        }

        catch(IOException e){
            closeEverything(socket, objectInput, objectOutputStream);
        }

    }


        public void closeEverything(Socket socket, ObjectInput objectInput, ObjectOutputStream objectOutputStream){
            try{
                if(objectInput != null){
                    objectInput.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
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
