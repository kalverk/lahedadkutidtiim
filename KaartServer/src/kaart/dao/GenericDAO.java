package kaart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Gerenic data access object
 * 
 */

public abstract class GenericDAO {
 
    private static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("KaartServer");
 
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
 
    public static void closeEntityManager() {
        emf.close();
    }
 
}