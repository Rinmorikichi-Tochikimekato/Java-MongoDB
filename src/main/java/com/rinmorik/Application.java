package com.rinmorik;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/*Every function except database creation and connection to the database is by default commented,
 *  the port at which the database is located is 27017, and is connected implicitly when you run the code
 *  To use any method, read the respective comments and then execute the function by uncomment the following code
 *  Create and insertion of elements, update and delete are unsafe methods, so bear caution while using it.
 * */

public class Application {

	
	public static void main(String[] args) {

/* Database Connection */
		MongoClient mongoClient = Service.getConnection();

/* Database Creation */		
		/* Enter the name of the database */
		String dbName = "cartoon";
		MongoDatabase db = Service.createDatabase(mongoClient, dbName);

/* collection creation */		
		/* Enter the name of the collection you want to enter */
		String collectName = "Characters";
		MongoCollection<Document> collection = Service.createCollection(db, collectName);

/* Insert A new Document */
		Document mickeyMouse = new Document();
		Document charlieBrown = new Document();

		mickeyMouse.append("_id", 1).append("characterName", "Mickey Mouse")
				.append("creator", new Document("firstName", "Walt").append("lastName", "Disney"))
				.append("pet", "Goofy");

		charlieBrown.append("_id", 2).append("characterName", "Charlie Brown")
				.append("creator", new Document("firstName", "Charles").append("lastName", "Shultz"))
				.append("pet", "Snoopy");
		
		

		/* Pushing the Document in the collection */

		 //Service.createDocument(collection, mickeyMouse);
		// Service.createDocument(collection, charlieBrown);

/* Finding a document inside the collection */
		/* Enter the id of the document you want to find */
		//int id = 2;
		//int checkFunc = Service.findDocument(collection, id);

/* To view all the Documents inside the Collection */
		//System.out.println("Printing the entire document");
		//Service.findAllDocument(collection);

		
		
		
/*Update Function*/
		  
		  /* Enter the Document you want to update, and enter the updated record
		  
		  Document existingDoc = new Document("_id", 1);
		  
		  Document newDoc = new Document("$set", new Document("characterName",
		  "Dilbert") . append("creator",new Document("firstName", "Scott").
		  append("lastName", "Adams")) . append("pet","Dogbert") );
		  
		  
		  this function calls the update document and updates the existing doc with the
		  new Doc Service.updateDocument(collection, existingDoc, newDoc);
		  
		  
		  To view all the Documents inside the Collection
		  System.out.println("Printing the entire document");
		  Service.findAllDocument(collection);
		  */
		  
		 
		
		

/* Delete Function */

		/* Enter the id of the element you want to delete */

		/*
		 * int idDelete = 3; 
		 * Service.deleteDocument(collection, idDelete);
		 * 
		 * Service.findAllDocument(collection);
		 */
		
	
/* Creating a second collection */		
		String serialColl = "Serials";
		MongoCollection<Document> collectionSerial = Service.createCollection(db, serialColl);
		
		
/* prepare nested data, with document of collection Characters, inside the Collection serialCollection*/				
		/*
		 * Document LooneyToons = new Document().append("name", "Looney Toons");
		 * ArrayList<Document> charId = new ArrayList<Document>();
		 * charId.add(Service.getDocument(collection, "Mickey Mouse"));
		 * charId.add(Service.getDocument(collection, "Charlie Brown"));
		 * LooneyToons.append("characters", charId);
		 */
		  // Service.createDocument(collectionSerial,LooneyToons);
		
		  //Service.findAllDocument(collectionSerial);
		  //Service.deleteByName(collectionSerial, "Looney Toons");
		
/* append to the collection in a document */
		  
		  int appendId = 1;
		  //Service.appendDataToDocument(collection, appendId);
		 // Service.findDocument(collection, appendId);
		
	}

}
