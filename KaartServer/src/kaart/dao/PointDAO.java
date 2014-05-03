package kaart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import kaart.connection.Server;
import kaart.entities.Category;
import kaart.entities.Comments;
import kaart.entities.Point;
import kaart.entities.Ratings;
import kaart.entities.User;

/**
 * Access to Tables
 * 
 */

public class PointDAO extends GenericDAO {
	

	public void persistPoint(Point point) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(point);
		em.getTransaction().commit();
		em.close();
	}

	public void persistCategory(Category category) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(category);
		em.getTransaction().commit();
		em.close();
	}
	
	public void persistUserRating(Ratings rating) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(rating);
		em.getTransaction().commit();
		em.close();
	}
	
	public void persistUser(User user) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public void persistComment(Comments comment) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public void renewUserRating(Ratings rating) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.merge(rating);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllPoints() {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query allPointsQuery = em.createQuery("Select c from Category c");
		List<Category> allPoints = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return allPoints;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllPointsByCategory(String category) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		List<Category> allPoints = new ArrayList<Category>();		
		Query allPointsQuery = em
				.createQuery("Select c from Category c WHERE CATEGORY = :category");
		allPointsQuery.setParameter("category", category.trim());
		allPoints.addAll(allPointsQuery.getResultList());			
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return allPoints;
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getNewPoints(String category, String id) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query allPointsQuery = em
				.createQuery("Select c from Category c WHERE CATEGORY = :category AND POINT_ID > :id");
		allPointsQuery.setParameter("category", category.trim());
		allPointsQuery.setParameter("id", Long.parseLong(id.trim()));
//		Query pointsCount = em
//				.createQuery("SELECT COUNT(*) FROM CATEGORY WHERE CATEGORY = :category GROUP BY CATEGORY;");
//		pointsCount.setParameter("category", category.trim());
		//List<Integer> s =  pointsCount.getResultList();
		//int count = s.get(0);
		List<Category> allPoints = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return allPoints;
	}
	
	public int getPointsCount(String category) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query pointsCount = em
				.createQuery("SELECT Count(c) FROM Category c WHERE CATEGORY = :category GROUP BY Category");
		pointsCount.setParameter("category", category.trim());		
		int s =  ((Long)pointsCount.getSingleResult()).intValue();
		int count = s;
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Point> getDetailedPointDescription(String id) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query allPointsQuery = em
				.createQuery("Select p from Point p WHERE id = :id");
		allPointsQuery.setParameter("id", Long.parseLong(id.trim()));
		List<Point> allPoints = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return allPoints;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByFBId(String fbID) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query allPointsQuery = em
				.createQuery("Select u from User u WHERE fbUserID = :id");
		allPointsQuery.setParameter("id", fbID.trim());
		List<User> user = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ratings> getUserRatingsOnPoint(Long userid, Long pointid) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query allPointsQuery = em
				.createQuery("Select r from Ratings r WHERE USER_ID = :user AND POINT_ID = :point");
		allPointsQuery.setParameter("user", userid);
		allPointsQuery.setParameter("point", pointid);
		List<Ratings> ratings = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return ratings;
	}
	
	@SuppressWarnings("unchecked")
	public Number getPointRaiting(Long id) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		//siit edasi ei jõua? aga peaks...
		//http://docs.oracle.com/cd/E13189_01/kodo/docs40/full/html/ejb3_overview_query.html
		Query allPointsQuery = em
				.createQuery("Select AVG(r.RATING) from Ratings r WHERE r.POINT_ID = :point");
		allPointsQuery.setParameter("point", id);
		Number rating = (Number) allPointsQuery.getSingleResult();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return rating;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comments> getPointComments(Long pointid) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query allPointsQuery = em
				.createQuery("Select c from Comments c WHERE POINT_ID = :point");
		allPointsQuery.setParameter("point", pointid);
		List<Comments> comments = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return comments;
	}

	public String convertCategoryListToString(List<Category> category) {
		String result = "";
		for (Category c : category) {
			String location = c.getPoint().getLocation();
			Long id = c.getPoint().getId();
			result += String.valueOf(id) + ";" + location + "!";
		}
		//String count = String.valueOf(getPointsCount(cat));
		//result += count + ";1.0,1.0!";
		return result;
	}

	public String convertListToString(List<Point> points) {
		String result = "";
		for (Point p : points) {
			String id = String.valueOf(p.getId());
			String name = p.getName();
			String location = p.getLocation();
			String desc = p.getDescription();
			String link = p.getLink();
			result += id + ";" + name + ";" + location + ";" + desc + ";"
					+ link + "!";
		}	
		return result;
	}
	
	public String convertCountToString(int c){
		return String.valueOf(c) + ";1.0,1.0!";
	}
	
	public String convertCommentsToString(List<Comments> comments){
		String result = "point_comments!";
		for(Comments c : comments){
			String name = c.getUser().getName();
			String comment = c.getComment();
			result += name + ";" + comment + "!";
		}
		return result;
	}
}
