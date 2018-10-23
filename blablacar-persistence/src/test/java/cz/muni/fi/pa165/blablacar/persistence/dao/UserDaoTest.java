package cz.muni.fi.pa165.blablacar.persistence.dao;


import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;


    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;


    @Test
    public void categoryTest() {
        User u = new User();
        u.setLogin("KEKET");
        u.setFirstName("a");
        u.setLastName("b");
        userDao.addUser(u);
        System.out.println(userDao.findAll().toString());
    }

}
