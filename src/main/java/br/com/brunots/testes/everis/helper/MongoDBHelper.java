package br.com.brunots.testes.everis.helper;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoDatabase;

public class MongoDBHelper {

	private static MongoDatabase database;
	private static String locker = ".lock";

	public static MongoDatabase getDatabase() {
		if (null == database) {
			synchronized (locker) {
				if (null == database) {
					CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(
							MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries
									.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

					@SuppressWarnings("resource")
					MongoClient client = new MongoClient();
					database = client.getDatabase("gestaoDeGastos").withCodecRegistry(pojoCodecRegistry);
				}
			}
		}
		return database;
	}

}
