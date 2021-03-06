package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//import cz.muni.fi.pa165.blablacar.service.config.ServiceConfiguration;

public class UserFacadeTest {

    @Autowired
    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private UserService userService;

    private User user;

    private UserDTO userDTO;

    private Drive drive;

    private DriveDTO driveDTO;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init() {
        user = new User();
        user.setLogin("f.baggins");
        user.setFirstName("Frodo");
        user.setLastName("Baggins");
        user.setPassword("123456789");
        user.setId(1L);

        userDTO = new UserDTO();
        userDTO.setLogin("f.baggins");
        userDTO.setFirstName("Frodo");
        userDTO.setLastName("Baggins");

        drive = new Drive();
        drive.setId(2L);
        drive.setFromCity(new City());
        drive.setToCity(new City());
        drive.setDriver(user);

        driveDTO = new DriveDTO();
        driveDTO.setId(2L);
        driveDTO.setFromCity(new CityDTO());
        driveDTO.setToCity(new CityDTO());
        driveDTO.setDriver(userDTO);

    }

    @Test
    void createUserTest() {
        when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(user);

        Long createdId = userFacade.createUser(userDTO);
        verify(userService).createUser(user);
        assertThat(createdId).isEqualTo(user.getId());
    }

    @Test
    void editUserTest() {
        when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(user);
        when(userService.findUserById(user.getId())).thenReturn(user);

        userFacade.editUser(userDTO);

        verify(userService).editUser(user);

    }

    @Test
    void deleteUserTest() {
        when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(user);

        userFacade.removeUser(userDTO);

        verify(userService).deleteUser(user);
    }

    @Test
    void findUserByIdTest() {
        when(beanMappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);
        when(userService.findUserById(1L)).thenReturn(user);

        UserDTO resUserDTO = userFacade.findUserById(user.getId());
        assertThat(resUserDTO)
                .isEqualToComparingFieldByField(userDTO);
    }


    @Test
    void findUserByLoginTest() {
        when(beanMappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);
        when(userService.findUserByLogin(user.getLogin())).thenReturn(user);

        UserDTO resUserDTO = userFacade.findUserById(user.getId());
        assertThat(resUserDTO)
                .isEqualToComparingFieldByField(userDTO);
    }


    @Test
    void findUserByFullNameTest() {
        when(beanMappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);
        when(userService.findUserByFullName(user.getFirstName(), user.getLastName())).thenReturn(user);

        UserDTO resUserDTO = userFacade.findUserById(user.getId());
        assertThat(resUserDTO)
                .isEqualToComparingFieldByField(userDTO);
    }


    @Test
    void getAllUsersTest() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);
        when(userService.findAllUsers()).thenReturn(expectedUsers);

        List<UserDTO> expectedUsersDTO = new ArrayList<>();
        expectedUsersDTO.add(userDTO);
        when(beanMappingService.mapTo(expectedUsers, UserDTO.class)).thenReturn(expectedUsersDTO);

        Collection<UserDTO> actualUsersDTO = new ArrayList<>(userFacade.getAllUsers());

        verify(userService).findAllUsers();
        assertThat(actualUsersDTO)
                .containsOnly(userDTO);
    }


    @Test
    void findDrivesAsDriverTest() {
        List<Drive> expectedDrives = new ArrayList<>();
        expectedDrives.add(drive);
        when(userService.findDrivesAsDriver(user.getId())).thenReturn(expectedDrives);

        List<DriveDTO> expectedDrivesDTO = new ArrayList<>();
        expectedDrivesDTO.add(driveDTO);
        when(beanMappingService.mapTo(expectedDrives, DriveDTO.class)).thenReturn(expectedDrivesDTO);


        Collection<DriveDTO> actualDrivesDTO = userFacade.getDriverDrives(user.getId());
        verify(userService).findDrivesAsDriver(user.getId());

        assertThat(actualDrivesDTO).
                containsOnly(driveDTO);
    }

    @Test
    void findDrivesAsPassengerTest() {
        List<Drive> expectedDrives = new ArrayList<>();
        expectedDrives.add(drive);
        when(userService.findDrivesAsPassenger(user.getId())).thenReturn(expectedDrives);

        List<DriveDTO> expectedDrivesDTO = new ArrayList<>();
        expectedDrivesDTO.add(driveDTO);
        when(beanMappingService.mapTo(expectedDrives, DriveDTO.class)).thenReturn(expectedDrivesDTO);


        Collection<DriveDTO> actualDrivesDTO = userFacade.getPassengerDrives(user.getId());
        verify(userService).findDrivesAsPassenger(user.getId());

        assertThat(actualDrivesDTO).
                containsOnly(driveDTO);
    }

    @Test
    void addDriveAsPassengerTest() {
        doNothing().when(userService).addCustomerToDrive(drive.getId(), user.getId());
        userFacade.addDriveAsPassenger(drive.getId(), user.getId());
        verify(userService).addCustomerToDrive(drive.getId(), user.getId());
    }

}
