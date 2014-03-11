package kaart.translation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Translation {

	private static org.apache.log4j.Logger logger = Logger
			.getLogger(Translation.class);

	private static String a; // name or category
	private static String b; // location or position
	private static String c; // description
	private static String d; // link
	private static List<String> categories = new ArrayList<String>(); //category tags

	public static int translate(String input) {
		try {
			String[] inputParameters = input.split(";");
			if (inputParameters.length == 2) {
				a = inputParameters[0];
				b = inputParameters[1];
				return 120;
			} else if (inputParameters.length >= 4) {
				a = inputParameters[0];
				b = inputParameters[1];
				c = inputParameters[2];
				d = inputParameters[3];
				for(int i=4;i<inputParameters.length;i++){
					if(inputParameters[i].equalsIgnoreCase("")||inputParameters[i].equalsIgnoreCase(" ")){}
					else{
						categories.add(inputParameters[i]);
					}
				}
				return 140;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			logger.warn("Cannot translate input: (" + input + ")", e);
			return -1;
		}
	}

	public static String getA() {
		return a;
	}

	public static String getB() {
		return b;
	}

	public static String getC() {
		return c;
	}

	public static String getD() {
		return d;
	}
	
	public static List<String> getCategoryTagList(){
		return categories;
	}
}
