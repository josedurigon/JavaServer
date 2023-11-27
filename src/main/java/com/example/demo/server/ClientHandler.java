package com.example.demo.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private ObjectInput objectInput;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInput = new ObjectInputStream(socket.getInputStream());

            clientHandlers.add(this);
        } catch (IOException e) {
            closeEverything(socket, objectInput, objectOutputStream);
        }
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                // Read the object from the client
                Object receivedObject = objectInput.readObject();

                // Process the received object (you can customize this part)
                processReceivedObject(receivedObject);
            }
        } catch (IOException | ClassNotFoundException e) {
            closeEverything(socket, objectInput, objectOutputStream);
        }
    }

    private void processReceivedObject(Object receivedObject) {
        // Implement your logic to process the received object
        System.out.println("Received object: " + receivedObject.toString());
    }

    public void sendObject(Object object) {
        try {
            if (object != null) {
                objectOutputStream.writeObject(object);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, objectInput, objectOutputStream);
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
    }

    public void closeEverything(Socket socket, ObjectInput objectInput, ObjectOutputStream objectOutputStream) {
        removeClientHandler();
        try {
            if (objectInput != null) {
                objectInput.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
