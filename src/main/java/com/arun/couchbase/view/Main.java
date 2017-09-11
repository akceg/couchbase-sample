package com.arun.couchbase.view;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arun.couchbase.dao.CouchbaseDao;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

public class Main {
	public static void main(String args[]) {
		try {
			/*CouchbaseDao dao = new CouchbaseDao("localhost", "sample", "admin");
			
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
//			dao.remove("ua:king_arthur");*/
			
			FileReader reader=new FileReader("db.properties");  
		      
		    Properties p=new Properties(); 
		    p.load(reader);
		    System.out.println(p);
		    
		    FileInputStream fis = new FileInputStream("db.properties");
		    final ResourceBundle bundle = new PropertyResourceBundle(fis);
		    final Map<String, String> bundleMap = resourceBundleToMap(bundle);

		    final Type mapType = new TypeToken<Map<String, String>>(){}.getType();

		    final String jsonBundle = new GsonBuilder()
		            .registerTypeAdapter(mapType, new BundleMapSerializer())
		            .create()
		            .toJson(bundleMap, mapType);
		    System.out.println(jsonBundle);
		}
		catch(Exception ex) {
			System.out.println("Exception : "+ex);
			ex.printStackTrace();
		}
	}
	
	private static Map<String, String> resourceBundleToMap(final ResourceBundle bundle) {
	    final Map<String, String> bundleMap = new HashMap<>();

	    for (String key: bundle.keySet()) {
	        final String value = bundle.getString(key);

	        bundleMap.put(key, value);
	    }

	    return bundleMap;
	}

	
}
