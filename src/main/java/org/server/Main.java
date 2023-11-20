package org.server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.server.server.Server;
import org.bson.Document;
import java.io.IOException;
import java.net.ServerSocket;


public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8088;
        System.out.println("Server is active on port: "+port+". http://localhost:8088");

        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        Dotenv dotenv = Dotenv.configure().load();
        String mongodbUri = dotenv.get("MONGODB_URI");
        MongoClient mongoClient = MongoClients.create(mongodbUri);
        MongoDatabase database = mongoClient.getDatabase("ProjetointegradorIV");
        server.StartServer();

        // Use mongodbUri in your MongoDB connection logic
        System.out.println("MongoDB URI: " + mongodbUri);


    }
}