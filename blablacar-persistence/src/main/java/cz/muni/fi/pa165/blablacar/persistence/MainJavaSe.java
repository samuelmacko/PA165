package cz.muni.fi.pa165.blablacar.persistence;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainJavaSe {
	private static EntityManagerFactory emf;

	public static void main(String[] args) throws SQLException {
		// The following line is here just to start up a in-memory database
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

		emf = Persistence.createEntityManagerFactory("default");
		try {
			// BEGIN YOUR CODE
                        EntityManager em = emf.createEntityManager();
                        em.getTransaction().begin();

                        User u = new User();
                        u.setFirstName("Test");
                        u.setLastName("1");
                        u.setLogin("U1");

                        Drive d = new Drive();
                        d.setDriver(u);
                        d.setCapacity(2);
                        d.setPrice(34.45);
                        d.setDate(new Date());

                        em.persist(u);
                        em.persist(d);

                        em.getTransaction().commit();
                        em.close();
                        EntityManager myEm = emf.createEntityManager();
                        myEm.getTransaction().begin();

//                        User foundU = myEm.find(User.class, u.getId());

                        Drive foundD = myEm.find(Drive.class, d.getId());

                        myEm.getTransaction().commit();
                        myEm.close();
//                        System.out.println("Creater user id = "+u.getId().toString());
//                        System.out.println("Found user id = "+foundU.getId().toString());
			// END YOUR CODE
		} finally {
			emf.close();
			appContext.close();
		}
	}

}
