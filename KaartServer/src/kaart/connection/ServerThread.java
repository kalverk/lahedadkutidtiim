package kaart.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import kaart.dao.GenericDAO;
import kaart.dao.PointDAO;
import kaart.entities.Category;
import kaart.entities.Comments;
import kaart.entities.Point;
import kaart.entities.Ratings;
import kaart.entities.User;
import kaart.translation.Translation;

import org.apache.log4j.Logger;

/**
 * ServerThread that reads Client input and prepares the appropriate output
 * 
 */
public class ServerThread extends Thread {

	private Socket socket;
	private DatabaseConnection dbconnectionProvider;
	private Connection connection;
	private static org.apache.log4j.Logger logger = Logger
			.getLogger(ServerThread.class);
	private PointDAO pointDao;

	public ServerThread(Socket socket, DatabaseConnection dbconnectionProvider) {
		super("Server");
		this.socket = socket;
		this.dbconnectionProvider = dbconnectionProvider;
	}

	public void run() {
		PrintWriter output = null;
		BufferedReader input = null;
		String inputString;
		String outputString = "";

		pointDao = new PointDAO();
		connection = dbconnectionProvider.getConnection();

		try {
			output = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			logger.error("IOException", e);
		}

		try {
			while ((inputString = input.readLine()) != null) {
				System.out.println("Inputstring server side " + inputString);
				int t = Translation.translate(inputString);
				outputString = getResult(t);
				output.println(outputString);
				System.out.println("Outputstring " + outputString);
			}
			output.close();
			input.close();
			socket.close();
		} catch (SocketException e) {
			logger.info("Socket connection lost");
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error("Cannot close database.", e);
		}

	}

	private String getResult(int t) {
		String result = "";
		String a = Translation.getA();
		String b = Translation.getB();

		if (t == 120) {
			if (a.equalsIgnoreCase("na") && isNumeric(b)) {
				// küsitakse punkti kirjeldust

				try {
					List<Point> point = pointDao.getDetailedPointDescription(b);
					String rating = pointDao.getPointRaiting(point.get(0).getId());
					result = pointDao.convertListToString(point) + rating + "!";
				} catch (Exception e) {
					logger.error("Could not get detailed point description", e);
				}

			} else if (!a.equalsIgnoreCase("na") && isNumeric(b)) {
				// küsitakse uuendusi
				if (Long.parseLong(b.trim()) == -11) {
					try {
						int count = pointDao.getPointsCount(a);
						return String.valueOf(count);
					} catch (Exception e) {
						logger.error(
								"Could not get count of points!", e);
					}

				} else {
					try {
						List<Category> newPoints = pointDao.getNewPoints(a, b);
						result = pointDao
								.convertCategoryListToString(newPoints);
					} catch (Exception e) {
						logger.error("Could not get upgraded info", e);
					}
				}
			} else {
				// list points by category Kuidas seda teha, et võtab minu
				// asukoha ümbrusest?
				try {
					List<Category> allPoints = pointDao
							.getAllPointsByCategory(a);
					result = pointDao.convertCategoryListToString(allPoints);
				} catch (Exception e) {
					logger.error("ERROR at listing object by category.", e);
				}
			}
		} else if (t == 140) {
			try {
				String c = Translation.getC();
				String d = Translation.getD();
				Point p = new Point(a, b, c, d);
				pointDao.persistPoint(p);
				List<String> categoryTags = Translation.getCategoryTagList();
				for (int i = 0; i < categoryTags.size(); i++) {
					Category category = new Category(categoryTags.get(i), p);
					System.out.println(categoryTags.get(i));
					pointDao.persistCategory(category);
				}
				result = "OK";
			} catch (Exception e) {
				logger.error("Error writing into database.", e);
			}
		} else if(t==100){
			try{
				User user;
				String c = Translation.getC();
				String name = Translation.getD().trim();
				Point point = pointDao.getDetailedPointDescription(a).get(0);
				List<User> users = pointDao.getUserByFBId(b);
				if(users.size()>0){
					user = users.get(0);
					
				}else{
					user = new User(name, b.trim());
					pointDao.persistUser(user);
				}
				Ratings rating;
				//tuleks vaadata kui rating on olemas siis tuleks seda muuta mitte uus teha
				List<Ratings> ratings = pointDao.getUserRatingsOnPoint(user.getId(), point.getId());
				if(ratings.size()>0){
					//kasutaja on juba hinnanud, peab uuendama
					rating = ratings.get(0);
					rating.setRating(Integer.valueOf(c));
					pointDao.renewUserRating(rating);
				}else{
					//uus hinnang
					rating = new Ratings(point, user, Integer.valueOf(c));
					pointDao.persistUserRating(rating);
				}	
				result = "OK";
			}catch(Exception e){
				logger.error("Error writing into database.", e);
			}
		}else if(t==110){
			//post comment
			User user;
			String text = Translation.getC();
			String name = Translation.getD();
			Point point = pointDao.getDetailedPointDescription(a).get(0);
			List<User> users = pointDao.getUserByFBId(b);
			if(users.size()>0){
				user = users.get(0);
				
			}else{
				user = new User(name, b.trim());
				pointDao.persistUser(user);
			}
			Comments comment = new Comments(point, user, text);
			pointDao.persistComment(comment);
			result = "OK";
		}else if(t==130){
			List<Comments> comments = pointDao.getPointComments(Long.valueOf(a));
			result = pointDao.convertCommentsToString(comments);
		}else {
			logger.info("Invalid request");
		}
		return result;
	}

	private boolean isNumeric(String num) {
		try {
			Long.parseLong(num.trim());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
