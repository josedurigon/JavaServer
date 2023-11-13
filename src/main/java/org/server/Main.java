package org.server;

import io.github.cdimascio.dotenv.Dotenv;
import org.server.server.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Server server = new Server(serverSocket);
        server.StartServer();
        Dotenv dotenv = Dotenv.configure().load();
        String mongodbUri = dotenv.get("MONGODB_URI");

        // Use mongodbUri in your MongoDB connection logic
        System.out.println("MongoDB URI: " + mongodbUri);
    }
}