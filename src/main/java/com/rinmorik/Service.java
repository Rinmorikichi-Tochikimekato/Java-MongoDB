package com.rinmorik;

import java.util.Scanner;

import org.bson.Document;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Service {
	
	static Scanner sc = new Scanner(System.in);
	
	/* Function to connect with the database using driver of mongoDB */
	static public MongoClient getConnection() {

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Database successfully connected");
		return mongoClient;

	}

	/* Function to create a new Database with a given name */
	static public MongoDatabase createDatabase(MongoClient mongoClient, String dbName) {

		return mongoClient.getDatabase(dbName);

	}

	/* Function to create a Collection inside the database */
	static MongoCollection<Document> createCollection(MongoDatabase db, String name) {

		MongoCollection<Document> collection = db.getCollection(name);
		return collection;

	}

	/* Function to insert a new Doucment inside the Collection with the */
	static void createDocument(MongoCollection<Document> collection, Document newDoc) {
		try {
			collection.insertOne(newDoc);
			System.out.println("Document successfully inserted");
		} catch (MongoWriteException mwe) {
			if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				System.out.println("Document with that id already exists");
			}
		}

	}

	/* Function to result all the records */
	static public int findDocument(MongoCollection<Document> collection, int id) {

		try {
			Document Finder = collection.find(Filters.eq("_id", id)).first();
			System.out.println(Finder.toJson());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	/* Function to view all the entries in the database (viewALL) */
	static public void findAllDocument(MongoCollection<Document> collection) {
		System.out.println("Printing the collection"+collection.getNamespace());
		MongoCursor<Document> cursor = collection.find().iterator();

		while (cursor.hasNext()) {
			System.out.println(cursor.tryNext().toJson());
		}
	}

	/* Function to update one document out of all Document */
	static public void updateDocument(MongoCollection<Document> collection, Document existingDoc, Document newDoc) {

		collection.updateOne(existingDoc, newDoc);

	}

	static public void deleteDocument(MongoCollection<Document> collection, int id) {

		int checkFunc = findDocument(collection, id);
		if (checkFunc == 1) {
			collection.deleteOne(Filters.eq("_id", id));
			System.out.println("element with Id = " + id + " deleted successful ");
		} else {
			System.out.println("Element with id = " + id + " does not exist");
		}
	}
	
	public static void deleteByName(MongoCollection<Document> collection,String name){
		int checkFunc = findDocument(collection, name);
		if (checkFunc == 1) {
			collection.deleteOne(Filters.eq("name", name));
			System.out.println("element with Id = " + name + " deleted successful ");
		} else {
			System.out.println("Element with id = " +name + " does not exist");
		}
		
	}

	private static int findDocument(MongoCollection<Document> collection, String name) {
		// TODO Auto-generated method stub
		try {
			Document Finder = collection.find(Filters.eq("name", name)).first();
			System.out.println("document found");
			System.out.println(Finder.toJson());
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	public static Document getDocument(MongoCollection<Document> collection, String name) {
		
		try {
			Document Finder = collection.find(Filters.eq("characterName", name)).first();
			System.out.println("document found");
			return Finder;
			
		} catch (Exception e) {
			
			return new Document();
		}
	}
	
public static Document getDocument(MongoCollection<Document> collection, int id) {
		
		try {
			Document Finder = collection.find(Filters.eq("_id", id)).first();
			System.out.println("document found");
			return Finder;
			
		} catch (Exception e) {
			
			return new Document();
		}
	}

	
	public static void appendDataToDocument(MongoCollection<Document> collection,int id) {
		
		Document filter = new Document("_id", id);
		
		int foundDocument = findDocument(collection, id);
		if(foundDocument == 1) {
			String value = "pickey";
			String key = "alternative-name";
			Document DocumentToBeUpdated = new Document("$set",new Document().append(key, value));
			
			
			updateDocument(collection,filter , DocumentToBeUpdated);
		
		}
	}

}
