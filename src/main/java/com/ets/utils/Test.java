package com.ets.utils;

import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Test {

	public static void main(String[] args) throws IOException {

	
		String textUri = "mongodb://kranthi:konahamaru@ds063134.mongolab.com:63134/gps";
		MongoClientURI uri = new MongoClientURI(textUri);
		MongoClient m = new MongoClient(uri);
		MongoDatabase database = m.getDatabase("gps");
		for(String str : database.listCollectionNames() ){
			
			System.out.println(str);
		}
	}
}
