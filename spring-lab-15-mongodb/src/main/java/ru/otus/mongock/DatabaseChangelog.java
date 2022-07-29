package ru.otus.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class DatabaseChangelog {

//    @ChangeSet(order = "001", id = "library", author = "root", runAlways = true)
//    public void dropDb(MongoDatabase db) {
//        db.drop();
//    }

    @ChangeSet(order = "002", id = "lev", author = "Lev Tolstoy")
    public void insertTolstoy(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        var doc = new Document().append("name", "Lev Tolstoy");
        myCollection.insertOne(doc);
    }
}
