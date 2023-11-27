package com.example.demo.server;

import com.example.demo.Models.Pacientes;
import com.example.demo.repository.PacienteRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){

        try{
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            while (true){
                Pacientes receivedObject = (Pacientes) objectInputStream.readObject();
                System.out.println("Received object: " + receivedObject.getNome());
                //processa o objeto

                objectOutputStream.writeObject("Server received" + receivedObject.getNome());
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(Pacientes paciente){


        PacienteRepository pacienteRepository = new PacienteRepository("Pacientes");
        pacienteRepository.insertOne(paciente);
    }
}
