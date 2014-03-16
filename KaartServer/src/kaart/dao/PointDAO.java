package kaart.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import kaart.connection.Server;
import kaart.entities.Category;
import kaart.entities.Point;

/**
 * Access to Point table
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
		Query allPointsQuery = em
				.createQuery("Select c from Category c WHERE CATEGORY = :category");
		allPointsQuery.setParameter("category", category.trim());
		List<Category> allPoints = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return allPoints;
	}

	@SuppressWarnings("unchecked")
	public List<Point> getDetailedPointDescription(String location) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		Query allPointsQuery = em
				.createQuery("Select p from Point p WHERE LOCATION = :location");
		allPointsQuery.setParameter("location", location.trim());
		List<Point> allPoints = allPointsQuery.getResultList();
		em.getTransaction().commit();
		em.close();
		closeEntityManager();
		return allPoints;
	}

	public String convertCategoryListToString(List<Category> category) {
		String result = "";
		for (Category c : category) {
			String location = c.getPoint().getLocation();
			result += location + "!";
		}
		return result;
	}

	public String convertListToString(List<Point> points) {
		String result = "";
		for (Point p : points) {
			String name = p.getName();
			String location = p.getLocation();
			String desc = p.getDescription();
			String link = p.getLink();
			result += name + ";" + location + ";" + desc + ";" + link + "!";
		}
		return result;
	}
}
