package com.example.demo.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.bson.Document;

@Getter
public class MongoDb {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> pacientesCollection;
    private final MongoCollection<Document> filaCollection;
    public MongoDb(String databaseName) {
        Dotenv dotenv = Dotenv.configure().load();
        String mongodbUri = dotenv.get("MONGODB_URI");

        this.mongoClient = MongoClients.create(mongodbUri);
        this.database = mongoClient.getDatabase(databaseName);
        this.pacientesCollection = database.getCollection("Pacientes");
        this.filaCollection = database.getCollection("Fila");
    }

}
