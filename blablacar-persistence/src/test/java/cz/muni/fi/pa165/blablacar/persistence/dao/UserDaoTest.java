package cz.muni.fi.pa165.blablacar.persistence.dao;


import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserDao userDao;

    private User firstUser;
    private User secondUser;
    private User thirdUser;

    @BeforeMethod
    private void setUp() {
        firstUser = new User();
        firstUser.setFirstName("Samuel");
        firstUser.setLastName("Macko");
        firstUser.setLogin("s.macko");
        firstUser.setPassword("strongPassword");

        secondUser = new User();
        secondUser.setFirstName("Bruno");
        secondUser.setLastName("Mizik");
        secondUser.setLogin("b.mizik");
        secondUser.setPassword("thisIsStrongerPassword");

        thirdUser = new User();
        thirdUser.setFirstName("Matus");
        thirdUser.setLastName("Sakala");
        thirdUser.setLogin("m.sakala");
        thirdUser.setPassword("but%this*Is/Really-Strong?Password#498468945");
    }


    @Test
    public void addUserTest() {
        userDao.addUser(firstUser);
    }

    @Test
    public void findUserByIdTest() {
        userDao.addUser(secondUser);
        assertThat(userDao.findUserById(secondUser.getId())).isEqualTo(secondUser);
    }


    @Test
    public void updateUserTest() {
        userDao.addUser(secondUser);
        secondUser.setLastName("newLastName");
        userDao.updateUser(secondUser);
        assertThat(userDao.findUserById(secondUser.getId()).getLastName()).isEqualTo("newLastName");
    }

    @Test
    public void findAllUsersTest() {
        userDao.addUser(firstUser);
        userDao.addUser(secondUser);
        userDao.addUser(thirdUser);
        Long numberOfUsers = (Long) em.createQuery("select count(u) from User u").getSingleResult();

        assertThat(userDao.findAll().size()).isEqualTo(numberOfUsers.intValue());
    }

    @Test
    public void removeUserTest() {
        int numberOfUsers = userDao.findAll().size();
        userDao.addUser(thirdUser);
        userDao.removeUser(thirdUser);
        assertThat(userDao.findAll().size()).isEqualTo(numberOfUsers);
    }

    @Test
    public void findUserByLoginTest() {
        userDao.addUser(firstUser);
        assertThat(userDao.findUserByLogin("s.macko")).isEqualTo(firstUser);
    }

    @Test
    public void findUserByFullNameTest() {
        userDao.addUser(secondUser);
        assertThat(userDao.findUserByFullName("Bruno", "Mizik")).isEqualTo(secondUser);
    }

}
