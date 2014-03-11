package kaart.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kaart.dao.GenericDAO;
import kaart.dao.PointDAO;
import kaart.entities.Point;

import org.apache.log4j.Logger;

/**
 * Server to start ServerThread and initialize DatabaseConnection
 *
 */

public class Server {
	private static org.apache.log4j.Logger info = Logger.getLogger(Server.class);
	public static void main(String[] args) throws IOException {
		info.info("Server starting");
		final int PORT = 2200;
		DatabaseConnection dbconnection = new DatabaseConnection();
		Connection connection = dbconnection.getConnection();
		try{
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			info.error("Can't connect to port " + PORT + ".", e);
			System.exit(-1);
		}

		info.info("Server is up!");
		connection = dbconnection.getConnection();
		
		while (true){
			new ServerThread(serverSocket.accept(), dbconnection).start();
		}

		}catch(NullPointerException e){info.error("NullPointerException",e);}
	}
}
