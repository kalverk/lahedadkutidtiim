import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Server {
	private static org.apache.log4j.Logger info = Logger.getLogger(Server.class);
	public static void main(String[] args) throws IOException {
		info.info("Server starting");
		final int PORT = 9002;
		DatabaseConnection dbconnection = new DatabaseConnection();
		Connection connection = dbconnection.getConnection();
		try{
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			info.error("Ei saa pordiga ühendust " + PORT + ".", e);
			System.exit(-1);
		}

		info.info("Server töötab!");
		connection = dbconnection.getConnection();
		PreparedStatement ps = null;
		
		try{
			ps = connection.prepareStatement("SELECT * FROM PLACE");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Integer id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String location = rs.getString("LOCATION");
				String description = rs.getString("DESCRIPTION");
				String link = rs.getString("LINK");
				info.info("ID " + id + " NAME " + name+" LOCATION " + location + " DESCRIPTION " + description + " LINK " + link);
			}
			rs.close();
			ps.close();
		}catch(SQLException e){
			info.error("SQL error :(",e);
		}
		try {
			connection.close();
		} catch (SQLException e) {
			info.error("CONNECTION error :(", e);
		} 
		}catch(NullPointerException e){info.error("NullPointerException",e);}
	}
}
