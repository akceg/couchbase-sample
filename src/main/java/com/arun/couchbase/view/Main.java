package com.arun.couchbase.view;

import com.arun.couchbase.dao.CouchbaseDao;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

public class Main {
	public static void main(String args[]) {
		try {
			CouchbaseDao dao = new CouchbaseDao("localhost", "sample", "admin");
			
			//insertion example
			JsonObject arun = JsonObject.create()
		            .put("name", "Arun")
		            .put("dept","CSE")
		            .put("skills", JsonArray.from("C","CPP" ,"Java","Python","Machine Learning"));
			dao.upsert("2013103056", arun);
			
			//retrival example
			JsonDocument doc = dao.get("2013103056");
			
			if(doc != null) {
				System.out.println(doc.content());
			}
			else {
				System.out.println("Document not found");
			}
			
			//deletion example
//			dao.remove("ua:king_arthur");
		}
		catch(Exception ex) {
			System.out.println("Exception : "+ex);			
		}
	}
	
	
}
