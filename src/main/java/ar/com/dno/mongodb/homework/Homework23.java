package ar.com.dno.mongodb.homework;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Homework23 {
	public static void main(String[] args) {
		MongoClient client  = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase database = client.getDatabase("students");
		MongoCollection<Document> collection = database.getCollection("grades");

		MongoCursor<Document> it = collection.find(eq("type", "homework"))
																		.sort(ascending("student_id", "score"))
																		.iterator();

		int studentIdPivot = -1;

		System.out.println(collection.count());
		while(it.hasNext()) {
			Document document = it.next();
			int studentId = document.getInteger("student_id");
			if(studentId != studentIdPivot) {
				System.out.println(studentId);
//				ObjectId objectId = document.getObjectId("_id");
//				collection.deleteOne(new BsonDocument("_id", new BsonObjectId(objectId)));
				studentIdPivot = studentId;
			}
			System.out.println(document.getDouble("score"));
		}
		
		System.out.println(collection.count());

	}
}
