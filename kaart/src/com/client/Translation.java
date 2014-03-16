package com.client;

import java.util.ArrayList;

import com.entity.Point;

public class Translation {

//	private static org.apache.log4j.Logger logger = Logger
//			.getLogger(Translation.class);

	public static ArrayList<Point> translate(String input) {
		
		ArrayList<Point> allPoints = new ArrayList<Point>();
		String name;
		String location;
		String description;
		String link;
		
		try {
			String[] allRows = input.split("!");
			for(String row : allRows){
				String[] objectParts = row.split(";");
				if(objectParts.length<=1){
					name = "";
					location = objectParts[0].trim();
					description = "";
					link = "";
				}else{
					name = objectParts[0].trim();
					location = objectParts[1].trim();
					description = objectParts[2].trim();
					link = objectParts[3].trim();
				}
				Point p = new Point(name, location, description, link);
				allPoints.add(p);
			}
		}catch(Exception e){
//			logger.error("Traslation exception.", e);
		}
		
		return allPoints;
	}
}

