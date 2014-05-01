package com.client;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Point;
import com.google.gson.Gson;

public class Client {
	// private static org.apache.log4j.Logger info = Logger
	// .getLogger(Client.class);
	private final static int PORT = 27910;
	private static String ip = "90.191.164.96";
//	private static String ip = "localhost";

	private String loc_name = "NA";
	private String loc_location = "NA";
	private String loc_description = "NA";
	private String loc_link = "NA";
	private long id = -1;
	private List<String> categoryTags;
	private boolean insert = false;
	private String result;

	private Socket socket = null;
	private PrintWriter output = null;
	private BufferedReader input = null;

	public Client() {
		setUpConnection();
	}

	private void setUpConnection() {
		try {
			connectToServer();
			setUpStreams();
		} catch (EOFException eofException) {
			// info.info("Client terminated connection");
		} catch (IOException e) {
			// info.info(e);
		}
		// tuleb siis kutsuda kui klient suletakse täielikult
		// finally {
		// try{
		// close();
		// }catch(Exception e){}
		// }
	}

	public void startRunning() {
		try {
			whileWaiting();
		} catch (IOException e) {
			// info.info(e);
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
//						System.out.println("Display dialog that insertion was successful.");
						result = "OK";
					}else{
						//result to json ja javascriptis peaks parsima
						System.out.println("Inpustring " + message);
							if(message.matches("^[0-9]+$")){
								result = message;
							}else{
								ArrayList<Point> allPoints = Translation.translate(message);
								Gson gson = new Gson();
								for (Point p : allPoints) {
									result+=gson.toJson(p)+"|";
							}
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
		// info.info("Attempting to connect to server");
		socket = new Socket(InetAddress.getByName(ip), PORT);
//		socket = new Socket(ip, PORT);
		// info.info("Connected");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	}

	private void setUpStreams() throws IOException {
		output = new PrintWriter(socket.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		// info.info("Streams are up.");
	}

	private void sendQuery(String outputLine) {
		try {
			output.println(outputLine);
		} catch (Exception e) {
			// info.error("ERROR while writing output", e);
		}
	}

	private String getOutputLine() {
		String result = "";
		if (insert) {
			result = loc_name.trim() + ";" + loc_location.trim() + ";"
					+ loc_description.trim() + ";"
					+ loc_link.toLowerCase().trim() + ";";
			for (int i = 0; i < categoryTags.size(); i++) {
				result += categoryTags.get(i).toLowerCase().trim() + ";";
			}
		} else {
			result = String.format("%s;%s", loc_name.toLowerCase().trim(),
					String.valueOf(id));
		}
		insert = false;
		return result;
	}

	private void close() {
		try {
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			// info.error("IOException at closing", e);
		}
	}

	public String getResult() {
		// System.out.println(result);
		return result;
	}

	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}

	public void setCategoryTags(List<String> categoryTags) {
		this.categoryTags = categoryTags;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public void setLoc_location(String loc_location) {
		this.loc_location = loc_location;
	}

	public void setLoc_description(String loc_description) {
		this.loc_description = loc_description;
	}

	public void setLoc_link(String loc_link) {
		this.loc_link = loc_link;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void clean() {
		this.loc_name = "NA";
		this.loc_description = "NA";
		this.loc_link = "NA";
		this.loc_location = "NA";
		this.insert = false;
		this.categoryTags = null;
		this.id = -1;
	}
}
