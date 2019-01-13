package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.DriveDao;
import cz.muni.fi.pa165.blablacar.persistence.dao.UserDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Mock
    private TimeService timeService;

    @Mock
    private DriveDao driveDao;

    @Mock
    private MailService mailService;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private Drive drive;
    private Drive driveAsPassenger;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void initMethod() {
        user = new User();
        user.setFirstName("Frodo");
        user.setLastName("Baggins");
        user.setPassword("RuleThemAll");
        user.setLogin("f.baggins");
        user.setId(1L);


        Date date = new Date();
        date.setYear(28);
        date.setMonth(8);
        date.setDate(12);

        drive = new Drive();
        drive.setId(5L);
        drive.setCapacity(5);
        drive.setDriver(user);
        drive.setPrice(BigDecimal.TEN);
        drive.setDate(date);
        drive.setFromCity(new City());
        drive.setToCity(new City());
    }

    @AfterMethod
    void reset() {
        Mockito.reset(userDao);
        Mockito.reset(driveDao);
        Mockito.reset(timeService);
        Mockito.reset(mailService);
    }

    @Test
    void testcreateUserTest() {
        User createdUser = userService.createUser(user);
        verify(userDao).addUser(user);

        assertThat(createdUser)
                .isEqualToComparingFieldByField(user);
    }

    @Test
    void createNullUserTest() {
        assertThatThrownBy(() -> userService.createUser(null))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void createUserNullFirstNameTest() {
        user.setFirstName(null);

        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createUserNullLastNameTest() {
        user.setLastName(null);

        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createUserNullLoginTest() {
        user.setLogin(null);

        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void editFirstNameTest() {
        String newFirstName = "RingBearer";

        doAnswer(invocation -> {
            user.setFirstName(newFirstName);
            return null;
        }).when(userDao).updateUser(any());

        userService.editUser(user);
        assertThat(user.getFirstName())
                .isEqualTo(newFirstName);

    }

    @Test
    void editLastNameTest() {
        String newLastName = "Bublik";

        doAnswer(invocation -> {
            user.setLastName(newLastName);
            return null;
        }).when(userDao).updateUser(any());

        userService.editUser(user);
        assertThat(user.getLastName())
                .isEqualTo(newLastName);

    }

    @Test
    void editLoginTest() {
        String newLogin = "super.frodo";

        doAnswer(invocation -> {
            user.setLogin(newLogin);
            return null;
        }).when(userDao).updateUser(any());

        userService.editUser(user);
        assertThat(user.getLogin())
                .isEqualTo(newLogin);
    }


    @Test
    void editNullUser() {
        assertThatThrownBy(() -> userService.editUser(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void editNullFirstNameTest() {
        user.setFirstName(null);

        assertThatThrownBy(() -> userService.editUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void editNullLastNameTest() {
        user.setLastName(null);

        assertThatThrownBy(() -> userService.editUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void editNullLoginTest() {
        user.setLogin(null);

        assertThatThrownBy(() -> userService.editUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deleteNullUserTest() {
        when(userDao.findUserById(user.getId())).thenReturn(null);

        assertThatThrownBy(() -> userService.deleteUser(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deleteUserTest() {
        when(userDao.findUserById(user.getId())).thenReturn(user);
        userService.deleteUser(user);
        verify(userDao).removeUser(user);

    }

    @Test
    void deleteUserUserNotFoundTest() {
        when(userDao.findUserById(user.getId())).thenReturn(null);

        assertThatThrownBy(() -> userService.deleteUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void removeNullUserTest() {
        assertThatThrownBy(() -> userService.deleteUser(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findAllUsersTest() {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userDao.findAll()).thenReturn(userList);

        List<User> resultUserList = userService.findAllUsers();

        assertThat(resultUserList)
                .containsExactlyInAnyOrder(user);
    }

    @Test
    void findDrivesAsDriverTest() {
        user.addToBeingDriver(drive);
        drive.addCustomer(user);

        when(userDao.findUserById(user.getId())).thenReturn(user);
        List<Drive> expected = userService.findDrivesAsDriver(user.getId());
        verify(userDao).findUserById(user.getId());
        assertThat(expected.size()).isEqualTo(1);
    }

    @Test
    void findDrivesAsPassengerTest() {
        drive.addCustomer(user);
        user.addToBeingCustomer(drive);

        when(userDao.findUserById(user.getId())).thenReturn(user);
        List<Drive> expected = userService.findDrivesAsPassenger(user.getId());
        verify(userDao).findUserById(user.getId());
        assertThat(expected.size()).isEqualTo(1);
    }

    @Test
    void addCustomerToDriveTest() {
        drive.addCustomer(user);
        user.addToBeingCustomer(drive);

        when(userDao.findUserById(user.getId())).thenReturn(user);
        user.setBeingCustomer(new HashSet());
        when(driveDao.findDriveById(drive.getId())).thenReturn(drive);
        doNothing().when(mailService).sendEmail(any(), any(), any());
        userService.addCustomerToDrive(drive.getId(), user.getId());
        assertThat(user.getBeingCustomer().size()).isEqualTo(1);
    }

    @Test
    void removeCustomerFromDriveTest() {
        drive.addCustomer(user);
        user.addToBeingCustomer(drive);

        when(userDao.findUserById(user.getId())).thenReturn(user);
        when(driveDao.findDriveById(drive.getId())).thenReturn(drive);
        doNothing().when(mailService).sendEmail(any(), any(), any());
        userService.removeCustomerFromDrive(drive.getId(), user.getId());
        assertThat(user.getBeingCustomer().isEmpty()).isEqualTo(true);
    }

    @Test
    void findUserByIdTest() {
        when(userDao.findUserById(user.getId())).thenReturn(user);

        User foundUser = userService.findUserById(user.getId());
        assertThat(foundUser)
                .isEqualToComparingFieldByField(user);
    }

    @Test
    void getUserRewardsTest() {
        user.addToBeingDriver(drive);
        drive.setDriver(user);

        drive.addCustomer(user);
        user.addToBeingCustomer(drive);

        HashMap<User, BigDecimal> expected = new HashMap<>();
        expected.put(user, BigDecimal.TEN);
        List<User> users = new ArrayList();
        users.add(user);
        when(userDao.findAll()).thenReturn(users);
        when(timeService.getCurrentTime()).thenReturn(new Date());
        Map<User, BigDecimal> result = userService.getUsersReward();
        verify(userDao).findAll();
        verify(timeService).getCurrentTime();
        assertThat(expected).isEqualTo(result);

    }

    @Test
    void getUserSpendingTest() {
        drive.addCustomer(user);
        user.addToBeingCustomer(drive);

        HashMap<User, BigDecimal> exp = new HashMap<>();
        exp.put(user, BigDecimal.valueOf(10));
        List<User> users = new ArrayList();
        users.add(user);
        when(userDao.findAll()).thenReturn(users);
        when(timeService.getCurrentTime()).thenReturn(new Date());
        Map<User, BigDecimal> result = userService.getUsersSpending();
        verify(userDao).findAll();
        verify(timeService).getCurrentTime();
        assertThat(exp).isEqualTo(result);

    }

    @Test
    void getUserProfitsTest() {
        drive.addCustomer(user);
        user.addToBeingCustomer(drive);

        List<User> users = new ArrayList<>();
        users.add(user);
        Map<User, BigDecimal> expected = new HashMap();
        expected.put(user, BigDecimal.valueOf(-10));
        when(userDao.findAll()).thenReturn(users);
        when(timeService.getCurrentTime()).thenReturn(new Date());
        Map<User, BigDecimal> result = userService.getUsersTotalProfit();

        verify(userDao, times(2)).findAll();
        verify(timeService).getCurrentTime();
        assertThat(expected).isEqualTo(result);
    }

}
