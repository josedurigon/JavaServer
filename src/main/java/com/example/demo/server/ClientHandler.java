package com.example.demo.server;

import com.example.demo.Models.Pacientes;
import com.example.demo.repository.FilaRepository;
import com.example.demo.repository.PacienteRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private PacienteRepository pacientesRepo;
    private FilaRepository filaRepo;


    public ClientHandler(Socket socket, PacienteRepository pacientesRepo, FilaRepository filaRepo){
        this.socket = socket;
        this.filaRepo = filaRepo;
        this.pacientesRepo = pacientesRepo;
    }
    @Override
    public void run(){

        try{
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            while (true){
                Pacientes receivedObject = (Pacientes) objectInputStream.readObject();
                System.out.println("Received object: " + receivedObject.getNome());

                //salvar paciente apenas (na coleção Pacientes)
//                this.salvarPaciente(receivedObject);
//                this.inserirNaFila(receivedObject);


                objectOutputStream.writeObject("Server received. This is server" + receivedObject.getNome());
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvarPaciente(Pacientes pacientes){
        pacientesRepo.savePaciente(pacientes);
    }

    public void inserirNaFila(Pacientes pacientes){
        filaRepo.entrarNaFila(pacientes);
    }
}
