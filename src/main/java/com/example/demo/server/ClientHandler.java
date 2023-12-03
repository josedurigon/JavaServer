package com.example.demo.server;

import com.example.demo.Models.Fila;
import com.example.demo.Models.Pacientes;
import com.example.demo.mongo.MongoDb;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import com.example.demo.negocio.GUID;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ClientHandler implements Runnable{
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private MongoDb mongoDb;


    public ClientHandler(Socket socket, MongoDb mongoDb){
        this.mongoDb = mongoDb;
        this.socket = socket;
    }
    @Override
    public void run(){
        ArrayList<Document> trackDocuments = new ArrayList<>();

        try{
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            while (true){
                Pacientes receivedObject = (Pacientes) objectInputStream.readObject();

                //Seta o GUID do ID do paciente para garantir a caracteristica unica
                receivedObject.setId(GUID.generateUID().toString());
                System.out.println("Received object: " + receivedObject.getNome());

                //Cria o BSON do paciente para persistir no mongo
                Document document = this.toPacienteDocument(receivedObject);
                //Crai o BSON da fila para persistir no mongo
                Document documentFila = this.toFilaDocument(receivedObject);

                //usa o metodo insertPaciente para persistir um paciente na coleção pacientes
                if(!mongoDb.getFilaCollection().find(documentFila).equals(documentFila)){
                    if(!receivedObject.getNome().isBlank()) {
                        InsertOneResult result = mongoDb.insertPaciente(document);
                        mongoDb.insertFila(documentFila);
                        if (result.getInsertedId().isNull()) {
                            throw new RuntimeException("Insert gone wrong...");
                        }
                    }
                }

                //Insere na lista de documentos
                trackDocuments.add(document);

                ArrayList<Document> filaList=  mongoDb.selectAll();


                objectOutputStream.writeObject(filaList);
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public Document toPacienteDocument(Pacientes receivedObject){
        Document document = new Document();

        document.put("id",receivedObject.getId());
        document.put("nome", receivedObject.getNome());
        document.put("endereco",receivedObject.getEndereco());
        document.put("contato",receivedObject.getContato());
        document.put("historico_medico",receivedObject.getHistorico_medico());

        return document;
    }

    public Document toFilaDocument(Pacientes receivedObject){
        System.out.println("documentos: "+ mongoDb.getFilaCollection().countDocuments());
        System.out.println("documentos: "+ mongoDb.getFilaCollection().find());

//        LocalDateTime localDateTime = new LocalDateTime();
        Fila fila = new Fila();
        fila.setNome(receivedObject.getNome());
        fila.setPatient_Id(receivedObject.getId());
        fila.setAdmissionDateTime(LocalDateTime.now());


        Document doc = new Document();

        doc.put("Patient_Id", receivedObject.getId());
        doc.put("Status", fila.getStatus());
        doc.put("AdmissionDateTime", fila.getAdmissionDateTime());
        doc.put("Nome", fila.getNome());

        return doc;
    }

    /*TODO delete all documents*/


}
