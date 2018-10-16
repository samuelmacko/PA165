package cz.muni.fi.pa165.blablacar;

import java.sql.SQLException;
import java.util.Calendar;
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
			// END YOUR CODE
		} finally {
			emf.close();
			appContext.close();
		}
	}

}
