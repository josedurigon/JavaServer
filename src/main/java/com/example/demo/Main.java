package com.example.demo;

import com.example.demo.mongo.MongoDb;
import com.example.demo.server.Server_;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import com.example.demo.server.Server;

import java.io.IOException;
import java.net.ServerSocket;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 8088;
        System.out.println("Server is active on port: "+port+". http://localhost:8088");

//        ServerSocket serverSocket = new ServerSocket(port);
//        Server server = new Server(serverSocket);
//        server.StartServer();

        Server_ server = new Server_();
        server.run(8088);

//
//        Dotenv dotenv = Dotenv.configure().load();
//        String mongodbUri = dotenv.get("MONGODB_URI");
//        MongoClient mongoClient = MongoClients.create(mongodbUri);
//        MongoDatabase database = mongoClient.getDatabase("ProjetointegradorIV");
//        // Use mongodbUri in your MongoDB connection logic
        MongoDb mongoDb = new MongoDb("ProjetointegradorIV");



    }
}