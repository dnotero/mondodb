package ar.com.dno.mongodb.builder;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoBuilder {

	public static MongoCollection<Document> getMongoCollection(String dbName, String collectionName) {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase database = mongoClient.getDatabase(dbName);
		return database.getCollection(collectionName);
	}
}
