package com.arun.couchbase.dao;

import com.couchbase.client.core.endpoint.kv.AuthenticationException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class CouchbaseDao {	
	Cluster cluster ;
	Bucket bucket;

	public CouchbaseDao(String hostname,String bucketName, String password)throws Exception {
		cluster = CouchbaseCluster.create(hostname);
		if(cluster != null) {
			try {
				bucket=cluster.openBucket(bucketName, password);
			}
			catch(Exception authEx) {
				throw new Exception("Invalid bucket name or password!");
			}
		}
		else {
			throw new Exception("Cluster creation error");			
		}
	}
	
	public void upsert(String key,JsonObject value) {
		bucket.upsert(JsonDocument.create(key, value));
	}
	
	public void remove(String id) {
		bucket.remove(id);
	}
	
	public JsonDocument get(String id) {
		return bucket.get(id);
	}
}
