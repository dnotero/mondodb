package ar.com.dno.mongodb;

import org.bson.Document;

import ar.com.dno.mongodb.builder.MongoBuilder;

import com.mongodb.client.MongoCollection;

public class SimpleMongoClient {

	public static void main(String[] args) {
		MongoCollection<Document> collection =  MongoBuilder.getMongoCollection("test", "test");
	}

}
