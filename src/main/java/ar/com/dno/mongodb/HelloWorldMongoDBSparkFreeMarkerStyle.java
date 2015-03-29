package ar.com.dno.mongodb;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreeMarkerStyle {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");
		
		MongoClient client  = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase database = client.getDatabase("course");
		final MongoCollection<Document> collection = database.getCollection("hello");
		
		collection.drop();
		
		collection.insertOne(new Document("name", "MongoDb"));
		
		Spark.get(new Route("/") {
			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template template = configuration.getTemplate("hello.ftl");
					Document document = collection.find().first();
					template.process(document, writer);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				return writer;
			}
		});
	}

}
