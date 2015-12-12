package com.ets.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ets.models.Coordinates;

@Component
public class DBUtil {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void insertCoordinates(Coordinates coordinates) {

		mongoTemplate.insert(coordinates);
	}

	public Coordinates getLatestCoordinates() {

		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "timestamp"));

		return mongoTemplate.findOne(query, Coordinates.class);
	}
}
