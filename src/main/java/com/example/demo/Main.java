package com.example.demo;

import com.example.demo.server.Server_;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 8088;
        System.out.println("Server is active on port: "+port+". http://localhost:8088");
        System.setProperty("https.protocols", "TLSv1.2");
        Server_ server = new Server_();
        server.run(8088);

    }
}