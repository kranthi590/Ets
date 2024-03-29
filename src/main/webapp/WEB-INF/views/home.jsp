<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>Easy Transportation System</title>

<meta name="apple-mobile-web-app-title" content="Karma Webapp">

<script async defer
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCVOEFz1FsL4mYFydE3rZbZ9HOp7TO0xYI&callback=initMap"></script>

<script type="text/javascript"
	src="<c:url value="/resources/jquery.js" />"></script>
<script type="text/javascript">
	var map;
	var marker, i;
	var markers = [];
	var geocoder;
	$(document).ready(function() {

		setInterval(function() {
			//marker.setVisible(false);      
			console.log("removed marker....");

			$.ajax({

				url : "/ets/getMapData",
				type : "GET",
				success : function(data) {
					
					var obj = JSON.parse(data)
					console.log(/* "Coordinates recieved: " + */ obj);
					var latLng = new google.maps.LatLng(obj.latitude, obj.longitude);
					
					marker.setPosition(latLng);
					$("#last_known").empty().html("Last Known Coordinates: <b>"+obj.latitude+","
							+ obj.longitude+" At: "+obj.timestamp+" </b>");
					geocoder.geocode({
						'latLng' : latLng
					}, function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							if (results[1]) {
								$("#address").empty().val("Location: "
										+ results[1].formatted_address);

								
							}
						}
					});
					console.log("added marker....");
				}
			});
		}, 5000);

	});
	function initMap() {
		var myLatLng = {
			lat : 17.45434811,
			lng : 78.3986823
		};

		map = new google.maps.Map(document.getElementById('map-canvas'), {
			zoom : 12,
			panControl : true,
			zoomControl : true,
			mapTypeControl : true,
			scaleControl : true,
			streetViewControl : true,
			overviewMapControl : true,
			rotateControl : true,
			center : myLatLng
		});

		marker = new google.maps.Marker({
			
			map : map,
			title : 'Hello World!'
		});
		geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'latLng' : myLatLng
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[1]) {
					document.getElementById('address').value = "Location: "
							+ results[1].formatted_address;
				}
			}
		});
	}
</script>
</head>
<body>



	<table style="left: 50px; position: relative;">
		<tr>
			<td><textarea id="address" style="width: 400px;"></textarea></td>
			<td id="last_known" >
			</td>
		</tr>
	</table>


	<div id="map-canvas"
		style="width: 1000px; height: 500px; left: 50px; top: 20px;"
		class="gmnoprint img"></div>



</body>
</html>