package com.example.demo.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class MongoDb {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> pacientesCollection;

    public MongoDb(String databaseName) {
        Dotenv dotenv = Dotenv.configure().load();
        String mongodbUri = dotenv.get("MONGODB_URI");

        this.mongoClient = MongoClients.create(mongodbUri);
        this.database = mongoClient.getDatabase(databaseName);
        this.pacientesCollection = database.getCollection("Pacientes");
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getPacientesCollection() {
        return pacientesCollection;
    }
}
