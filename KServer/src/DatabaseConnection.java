import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class DatabaseConnection {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(DatabaseConnection.class);
	
	DatabaseConnection(){
	}
	public synchronized Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e1) {
			logger.error("DB driver error", e1);
		}
		
		String url = "jdbc:hsqldb:hsql://localhost/KaartDB";
		String user = "SA";
		String password = "";

		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(true);
		} catch (Exception e1) {
			logger.error("Database connection error", e1);;
		}
		return connection;
	}
}