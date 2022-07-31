package ru.otus.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "library", author = "root", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "1", author = "admin")
    public void insertTolstoy(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("authors");
        var doc = new Document().append("name", "Lev Tolstoy");
        collection.insertOne(doc);
    }
    @ChangeSet(order = "003", id = "2", author = "admin")
    public void insertGenre(MongoDatabase db){
        MongoCollection<Document> collection = db.getCollection("genres");
        var doc = new Document().append("name", "Drama");
        collection.insertOne(doc);
    }
    @ChangeSet(order = "004", id = "3", author = "admin")
    public void insertBook(MongoDatabase db){
        var collection = db.getCollection("books");
        var doc = new Document().append("title", "Peace and War");
        collection.insertOne(doc);
    }
}
