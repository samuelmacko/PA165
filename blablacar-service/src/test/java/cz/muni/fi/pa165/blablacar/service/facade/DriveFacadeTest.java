package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.*;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.DriveService;
import cz.muni.fi.pa165.blablacar.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class DriveFacadeTest {

    @Autowired
    @InjectMocks
    private DriveFacadeImpl driveFacade;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private DriveService driveService;

    @Mock
    private UserService userService;

    private User user;

    private Drive drive;

    private UserDTO userDTO;

    private DriveCreateDTO driveCreateDTO;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init() {
        user = new User();
        user.setLogin("driver");
        user.setFirstName("John");
        user.setLastName("Driver");
        user.setPassword("asdoajld");
        user.setId(1L);

        drive = new Drive();
        drive.setId(2L);
        drive.setFromCity(new City());
        drive.setToCity(new City());
        drive.setCapacity(4);
        drive.setDriver(user);

        userDTO = new UserDTO();
        userDTO.setLogin("driver");
        userDTO.setFirstName("John");
        userDTO.setLastName("Driver");

        driveCreateDTO = new DriveCreateDTO();
        driveCreateDTO.setId(2L);
        driveCreateDTO.setCapacity(4);
        driveCreateDTO.setFromCity(new CityDTO());
        driveCreateDTO.setToCity(new CityDTO());
        driveCreateDTO.setDriver(userDTO);

    }

    @Test
    void createDriveTest() {
        when(driveService.addDrive(drive)).thenReturn(drive);

        Long id = driveFacade.createDrive(driveCreateDTO);
        verify(driveService).addDrive(drive);
        assertThat(id).isEqualTo(drive.getId());
    }

    @Test
    void removeDriveTest() {
        doNothing().when(driveService).removeDrive(any());
        when(driveService.findDriveById(drive.getId())).thenReturn(drive);

        driveFacade.removeDrive(drive.getId());

        verify(driveService).removeDrive(drive);
    }

    @Test
    void addCustomerTest() {
        AddCustomerDTO addCustomerDTO = new AddCustomerDTO();
//        addCustomerDTO.
//        doNothing().when(userService).addCustomerToDrive();
//        when(driveService.findUserById(user.getId())).thenReturn(user);
//
//        driveFacade.editUser(userDTO);
//
//        verify(driveService).editUser(user);

    }

    @Test
    void removeCustomerTest() {
        RemoveCustomerDTO removeCustomerDTO = new RemoveCustomerDTO();
//        addCustomerDTO.
//        doNothing().when(userService).addCustomerToDrive();
//        when(driveService.findUserById(user.getId())).thenReturn(user);
//
//        driveFacade.editUser(userDTO);
//
//        verify(driveService).editUser(user);

    }

    @Test
    void changeCapacityTest() {
        doNothing().when(driveService).updateDrive(drive);

        driveFacade.changeCapacity(drive.getId(), 5);
        verify(driveService).updateDrive(drive);
        assertThat(drive.getCapacity()).isEqualTo(5);
    }

    @Test
    void changeDriverTest() {

    }

    @Test
    void changeDate() {

    }
    @Test
    void findDriveById() {

    }
    @Test
    void findAllDrives() {

    }



}
