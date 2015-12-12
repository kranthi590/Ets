package com.ets.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ets.models.Coordinates;
import com.ets.utils.DBUtil;
import com.ets.utils.TimeStampUtil;

@Component
public class GpsCoordinatesService {

	@Autowired
	private DBUtil dbUtil;

	public void formatAndInsertCoordinates(String location) {

		String data[] = location.split(",");
		Coordinates coordinates = new Coordinates();
		coordinates.setLatitude(Double.parseDouble(data[0]));
		coordinates.setLongitude(Double.parseDouble(data[1]));
		coordinates.setTimestamp(TimeStampUtil.getCurrentTimeStamp());
		dbUtil.insertCoordinates(coordinates);
	}

	public Coordinates getLatestCoordinate() {

		return dbUtil.getLatestCoordinates();
	}
}
