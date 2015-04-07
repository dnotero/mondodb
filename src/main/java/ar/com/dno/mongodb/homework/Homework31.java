package ar.com.dno.mongodb.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bson.Document;

import ar.com.dno.mongodb.builder.MongoBuilder;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

@SuppressWarnings("unchecked")
public class Homework31 {

	private static final String HOMEWORK = "homework";

	public static void main(String[] args) {
		Homework31 hw = new Homework31();
		MongoCollection<Document> collection = MongoBuilder.getMongoCollection("school", "students");
		MongoCursor<Document> it = collection.find().iterator();
		
		while(it.hasNext()) {
			Map<String, Document> filteredScores = new TreeMap<String, Document>();
			Document student = it.next();
			
			for(Document score : hw.getScores(student)) {
				String type = score.getString("type");
				
				if(hw.isHomework(type)) {
					hw.setMaxScore(filteredScores, score);
				} else {
					filteredScores.put(type, score);
				}
			}
			
			student.put("scores", new ArrayList<Document>(filteredScores.values()));
			collection.replaceOne(Filters.eq("_id", student.getInteger("_id")), student);
		}
	}

	private List<Document> getScores(Document document) {
		return (ArrayList<Document>) document.get("scores", ArrayList.class);
	}

	private boolean isHomework(String type) {
		return HOMEWORK.equalsIgnoreCase(type);
	}

	private void setMaxScore(Map<String, Document> filteredScores, Document score) {
		Document maxScore = filteredScores.get(HOMEWORK);
		
		if(maxScore == null) {
			filteredScores.put(HOMEWORK, score);
			return;
		}

		Double maxScoreValue = maxScore.getDouble("score");
		Double scoreValue = score.getDouble("score");
		
		if(maxScoreValue.compareTo(scoreValue) < 0) {
			filteredScores.put(HOMEWORK, score);
		}
	}
}
