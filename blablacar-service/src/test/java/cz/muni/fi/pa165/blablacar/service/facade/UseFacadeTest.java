package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.UserService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UseFacadeTest {
    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private UserService userService;

    private User validUser;

    private Drive validDrive;


    private UserDTO validUserDTO;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init() {
    }

    @Test
    void testCreateUser() {
    }

    @Test
    void testEditUser() {
    }

    @Test
    void testDeleteUser() {
    }

    @Test
    void testAddPassengerToRide() {
    }


    @Test
    void testRemovePasssengerFromRide() {
    }

    @Test
    void testFindAllUsers() {
    }

    @Test
    void testFindUserById() {
    }

    @Test
    void testFindUserByNickname() {
    }

    @Test
    void testFindUserRidesAsDriver() {
    }


    @Test
    void testFindUserRidesAsPassenger() {

    }

}
