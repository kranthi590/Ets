package com.ets.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ets.models.Coordinates;
import com.ets.web.service.GpsCoordinatesService;
import com.google.gson.Gson;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private GpsCoordinatesService service;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) {

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}

	@ResponseBody
	@RequestMapping(value = "/receiveGPS", method = RequestMethod.GET)
	public String getCoordinates(HttpServletRequest httpServletRequest) {

		try {
			String location = httpServletRequest.getParameter("location");

			service.formatAndInsertCoordinates(location);
			logger.info("Coordinated Received: " + location);
		} catch (Exception e) {

			logger.error("Exception: " + e.getMessage());
		}

		return "ok";
	}

	@ResponseBody
	@RequestMapping(value = "/getMapData", method = RequestMethod.GET)
	public String getMapData(HttpServletRequest httpServletRequest) {

		logger.info("Fetching coordinates from server");
		Coordinates coordinates = service.getLatestCoordinate();
		logger.info("Coordinates From DB is: " + coordinates);
		if (coordinates == null) {
			coordinates = new Coordinates();
			coordinates.setLatitude(17.45434811);
			coordinates.setLongitude(78.3986823);
			coordinates.setTimestamp("This is a new object , due to objet from db is null");

		}
		Gson gson = new Gson();
		return gson.toJson(coordinates);
	}

}
