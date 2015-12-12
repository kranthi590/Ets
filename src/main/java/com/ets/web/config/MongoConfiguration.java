package com.ets.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Autowired
	private Environment env;

	@Override
	protected String getDatabaseName() {
		return env.getProperty("mongodb.db");
	}

	@Override
	public Mongo mongo() throws Exception {
		MongoClientURI uri = new MongoClientURI(env.getProperty("mongodb.uri"));
		return new MongoClient(uri);
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.ets.models";
	}
}