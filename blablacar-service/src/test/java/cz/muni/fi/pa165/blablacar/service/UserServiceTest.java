package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.UserDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

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
    void removeUserTest() {
        userService.deleteUser(user);
        verify(userDao).removeUser(user);
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
    void testFindUserById() {
        when(userDao.findUserById(user.getId())).thenReturn(user);

        User foundUser = userService.findUserById(user.getId());
        assertThat(foundUser)
                .isEqualToComparingFieldByField(user);

    }

}
