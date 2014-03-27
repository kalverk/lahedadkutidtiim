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
		Long id;
		
		try {
			String[] allRows = input.split("!");
			for(String row : allRows){
				String[] objectParts = row.split(";");
				if(objectParts.length<=2){
					id = Long.parseLong(objectParts[0].trim());
					name = "";
					location = objectParts[1].trim();
					description = "";
					link = "";
				}else{
					id = Long.parseLong(objectParts[0].trim());
					name = objectParts[1].trim();
					location = objectParts[2].trim();
					description = objectParts[3].trim();
					link = objectParts[4].trim();
				}
				Point p = new Point(id, name, location, description, link);
				allPoints.add(p);
			}
		}catch(Exception e){
//			logger.error("Traslation exception.", e);
		}
		
		return allPoints;
	}
}

