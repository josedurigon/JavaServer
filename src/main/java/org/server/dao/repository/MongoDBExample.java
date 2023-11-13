package org.server.dao.repository;

import io.github.cdimascio.dotenv.Dotenv;

public class MongoDBExample {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        String mongodbUri = dotenv.get("MONGODB_URI");

        // Use mongodbUri in your MongoDB connection logic
        System.out.println("MongoDB URI: " + mongodbUri);
    }
}
