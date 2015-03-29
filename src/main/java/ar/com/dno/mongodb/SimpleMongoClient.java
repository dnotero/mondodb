package ar.com.dno.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SimpleMongoClient {

	public static void main(String[] args) {
		MongoClient client  = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		
	}

}
