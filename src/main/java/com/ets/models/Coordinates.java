package com.ets.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coordinates")
public class Coordinates {

	private Double latitude, longitude;
	private String timestamp;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + timestamp + "]";
	}

}
