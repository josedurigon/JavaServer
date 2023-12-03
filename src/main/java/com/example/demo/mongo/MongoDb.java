package com.example.demo.mongo;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

@Getter
public class MongoDb {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> pacientesCollection;
    private final MongoCollection<Document> filaCollection;
    private ArrayList<Document> filaList;
    public MongoDb(String databaseName) {
        Dotenv dotenv = Dotenv.configure().load();
        String mongodbUri = dotenv.get("MONGODB_URI");

        this.mongoClient = MongoClients.create(mongodbUri);
        this.database = mongoClient.getDatabase(databaseName);
        this.pacientesCollection = database.getCollection("Pacientes");
        this.filaCollection = database.getCollection("Fila");

    }

    public String listCollections(){
        return this.database.listCollections().toString();
    }

    public InsertOneResult insertPaciente(Document document){
        InsertOneResult result = pacientesCollection.insertOne(document);
        return result;
    }

    public InsertOneResult insertFila(Document document){
        InsertOneResult result = filaCollection.insertOne(document);
        return result;
    }

    public Document callFromQueue(){
        FindIterable<Document> findIterable = filaCollection.find()
                .sort(Sorts.descending("AdmissionDateTime"))
                .limit(1);
        return findIterable.first();
    }

    public Document selectOneFila(){
        Bson projectionsField = Projections.fields(
                Projections.include("nome","AdmissionDateTime"),
                Projections.exclude("_id")
        );

        Document doc = filaCollection.find()
                .projection(projectionsField)
                .sort(Sorts.descending("AdmissionDateTime"))
                .first();
        return doc;
    }

    public ArrayList<Document> selectAll(){
        Bson projectionsField = Projections.fields(
                Projections.include("Nome", "AdmissionDateTime", "Patient_Id"),
                Projections.exclude("_id")
        );

        MongoCursor<Document> cursor = filaCollection.find()
                .projection(projectionsField)
                .sort(Sorts.descending("AdmissionDateTime")).iterator();
        System.out.println(cursor);
        System.out.println(cursor.next());

        filaList = new ArrayList<>();
        while(cursor.hasNext()){
                filaList.add(cursor.next());
        }
        return filaList;
    }

}
