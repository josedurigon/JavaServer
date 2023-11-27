package com.example.demo.server;

import com.example.demo.Models.Pacientes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_ {
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;



    public void run(int port) throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(port);

        while(true) {
                if(!serverSocket.isClosed()) {
                    socket = serverSocket.accept();
                    System.out.println("Client connected");
                    //
                    //            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    //            objectInputStream = new ObjectInputStream(socket.getInputStream());

                    Thread clientThread = new Thread(new ClientHandler(socket));
                    clientThread.start();

//                    Pacientes receivedObject = (Pacientes) objectInputStream.readObject();
//                    System.out.println(receivedObject.getNome());
//                    System.out.println(receivedObject.getEndereco());
                }
        }

    }

}
