package br.com.brunots.testes.everis.helper;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBHelper {
	
	private static MongoDatabase database;
	private static String locker = ".lock";
	
	public static MongoDatabase getDatabase() {
		if (null == database) {
			synchronized (locker) {
				if (null == database) {
					@SuppressWarnings("resource")
					MongoClient client = new MongoClient();
					database = client.getDatabase("gestaoDeGastos");
				}
			}
		}
		return database;
	}
	
}
