package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.UserDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Autowired
    private UserService userService;

    private User validUser;
    private User invalidUser;

    private Drive validDrive;

    private Calendar calendar = Calendar.getInstance();


    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void initMethod() {

    }

    @Test
    void testCreateUserValid() {

    }

    @Test
    void testCreateNullUser() {

    }

    @Test
    void testCreateUserNullName() {

    }

    @Test
    void testCreateUserNullNickname() {

    }


    @Test
    void testCreateUserNullSurname() {

    }


    @Test
    void testCreateUserEmptyName() {

    }

    @Test
    void testCreateUserEmptyNickname() {
    }


    @Test
    void testCreateUserEmptySurname() {
    }

    @Test
    void testUpdateValidUserName() {

    }


    @Test
    void testUpdateValidUserSurname() {

    }


    @Test
    void testUpdateValidUserNickname() {

    }


    @Test
    void testUpdateNullUser() {
    }

    @Test
    void testUpdateNullUserName() {

    }

    @Test
    void testUpdateNullUserSurname() {

    }

    @Test
    void testUpdateNullUserNickname() {

    }

    @Test
    void testFindAllUsers() {

    }

    @Test
    void testFindUserById() {

    }

    @Test
    void testFindAllUserRidesAsDriver() {

    }

    @Test
    void testFindAllUserRidesAsPassenger() {

    }


    @Test
    void testDeleteUser() {

    }

    @Test
    void testDeleteNullUser() {

    }


}
