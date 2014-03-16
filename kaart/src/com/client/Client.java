package com.client;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Point;
import com.google.gson.Gson;

public class Client {
//	private static org.apache.log4j.Logger info = Logger
//			.getLogger(Client.class);
	private final static int PORT = 2200;
	private static String ip = "localhost";

	private String loc_name = "NA";
	private String loc_location = "NA";
	private String loc_description = "NA";
	private String loc_link = "NA";
	private List<String> categoryTags;
	private boolean insert = false;
	private String result;

	private Socket socket = null;
	private PrintWriter output = null;
	private BufferedReader input = null;

	public Client(String name, String location, String description, String link, List<String> categoryTags) {
		this.loc_name = name;
		this.loc_description = description;
		this.loc_link = link;
		this.loc_location = location;
		this.insert = true;
		this.categoryTags = categoryTags;
	}

	public Client(String category, String position) {
		this.loc_name = category;
		this.loc_location = position;
	}

	public Client(String category) {
		this.loc_name = category;
	}

	public void startRunning() {
		try {
			connectToServer();
			setUpStreams();
			whileWaiting();
		} catch (EOFException eofException) {
//			info.info("Client terminated connection");
		} catch (IOException e) {
//			info.info(e);
		} finally {
			close();
		}
	}

	private void whileWaiting() throws IOException {
		boolean gotInfo = false;
		result = "";
		sendQuery(getOutputLine());
		while (!gotInfo) {
			try {
				String message = "";
				if ((message = input.readLine()) != null) {
					if(message.equalsIgnoreCase("ok")){
						//display dialog telling that SQL insertion has been completed.
						System.out.println("Display dialog that insertion was successful.");
						result = "OK";
					}else{
						//result to json ja javascriptis peaks parsima
						ArrayList<Point> allPoints = Translation.translate(message);
						Gson gson = new Gson();
						for (Point p : allPoints) {
							result+=gson.toJson(p)+"|";
						}
					}
					gotInfo = true;
				}
			} catch (Exception e) {
//				info.error("ERROR while reading in", e);
			}
		}
//		info.info("Result has been received, shutting client down.");
	}

	private void connectToServer() throws IOException {
//		info.info("Attempting to connect to server");
		socket = new Socket(ip, PORT);
//		info.info("Connected");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	}

	private void setUpStreams() throws IOException {
		output = new PrintWriter(socket.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
//		info.info("Streams are up.");
	}

	private void sendQuery(String outputLine) {
		try {
			output.println(outputLine);
		} catch (Exception e) {
//			info.error("ERROR while writing output", e);
		}
	}

	private String getOutputLine() {
		String result = "";
		if (insert) {
			result = loc_name.toLowerCase().trim() + ";" + loc_location.trim() + ";" + loc_description.trim() + ";" + loc_link.toLowerCase().trim() + ";";
			for(int i=0; i<categoryTags.size();i++){
				result +=categoryTags.get(i).toLowerCase().trim() + ";";
			}
		} else {
			result = String.format("%s;%s", loc_name.toLowerCase().trim(), loc_location.trim());
		}
		insert = false;
		return result;
	}
	
	private void getDetailedPointInformation(){
		
	}

	private void close() {
		try {
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
//			info.error("IOException at closing", e);
		}
	}
	
	public String getResult(){
		System.out.println(result);
		return result;
	}
}

