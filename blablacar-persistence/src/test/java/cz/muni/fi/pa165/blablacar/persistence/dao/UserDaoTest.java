package cz.muni.fi.pa165.blablacar.persistence.dao;


import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

//    @PersistenceUnit
//    private EntityManagerFactory emf;


//    @PersistenceContext
//    private EntityManager em;

    @Autowired
    private UserDao userDao;


    @Test
    public void categoryTest() {

    }

}
