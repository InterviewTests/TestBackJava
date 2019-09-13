package com.santander.interview.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
//    @Value("#{environment['MONGO_HOST']}")
    @Value("${mongo.host}")
    private String MONGO_HOST;

//    @Value("#{environment['MONGO_PORT']}")
    @Value("${mongo.port}")
    private int MONGO_PORT;

//    @Value("#{environment['MONGO_DATABASE']}")
    @Value("${mongo.database}")
    private String MONGO_DATABASE;

    @Bean
    @Override
    public MongoClient mongoClient() {
        return new MongoClient(MONGO_HOST, MONGO_PORT);
    }

    @Override
    protected String getDatabaseName() {
        return MONGO_DATABASE;
    }
}
