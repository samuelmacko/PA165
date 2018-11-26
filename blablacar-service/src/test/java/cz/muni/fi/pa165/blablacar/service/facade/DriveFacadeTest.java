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

import java.util.*;

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

    @Mock
    private AddCustomerDTO addCustomerDTO;

    @Mock
    private RemoveCustomerDTO removeCustomerDTO;

    private User user;
    private User user2;

    private Drive drive;
    private City cityFrom;
    private City cityTo;


    private UserDTO userDTO;

    private DriveCreateDTO driveCreateDTO;
    private DriveDTO driveDTO;

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

        user2 = new User();
        user2.setLogin("driver2");
        user2.setFirstName("Ben");
        user2.setLastName("Driver 2");
        user2.setPassword("asdoajld");
        user2.setId(2L);

        drive = new Drive();
        drive.setId(2L);
        drive.setFromCity(new City());
        drive.setToCity(new City());
        drive.setCapacity(4);
        drive.setDriver(user);

        cityFrom = new City();
        cityTo = new City();

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

        driveDTO = new DriveDTO();
        driveDTO.setId(2L);
        driveDTO.setCapacity(4);
        driveDTO.setFromCity(new CityDTO());
        driveDTO.setToCity(new CityDTO());
        driveDTO.setDriver(userDTO);

    }

    @Test
    void createDriveTest() {
        when(driveService.addDrive(drive)).thenReturn(drive);
        when(beanMappingService.mapTo(driveCreateDTO, Drive.class)).thenReturn(drive);
        when(beanMappingService.mapTo(driveCreateDTO.getDriver(), User.class)).thenReturn(user);
        when(beanMappingService.mapTo(driveCreateDTO.getFromCity(), City.class)).thenReturn(cityFrom);
        when(beanMappingService.mapTo(driveCreateDTO.getToCity(), City.class)).thenReturn(cityTo);

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
        doNothing().when(userService).addCustomerToDrive(any(), any());

        when(addCustomerDTO.getDriveId()).thenReturn(drive.getId());
        when(addCustomerDTO.getCustomerId()).thenReturn(user2.getId());
        when(driveService.findDriveById(any())).thenReturn(drive);
        when(userService.findUserById(any())).thenReturn(user2);
        driveFacade.addCustomer(addCustomerDTO);

        verify(userService).addCustomerToDrive(addCustomerDTO.getDriveId(), addCustomerDTO.getCustomerId());
    }

    @Test
    void removeCustomerTest() {
        doNothing().when(userService).removeCustomerFromDrive(any(), any());

        when(removeCustomerDTO.getDriveId()).thenReturn(drive.getId());
        when(removeCustomerDTO.getCustomerId()).thenReturn(user2.getId());
        when(driveService.findDriveById(any())).thenReturn(drive);
        when(userService.findUserById(any())).thenReturn(user2);
        driveFacade.removeCustomer(removeCustomerDTO);

        verify(userService).addCustomerToDrive(removeCustomerDTO.getDriveId(), removeCustomerDTO.getCustomerId());

    }

    @Test
    void changeCapacityTest() {
        doNothing().when(driveService).updateDrive(drive);
        when(driveService.findDriveById(drive.getId())).thenReturn(drive);
        driveFacade.changeCapacity(drive.getId(), 5);
        verify(driveService).updateDrive(drive);
        assertThat(drive.getCapacity()).isEqualTo(5);
    }

    @Test
    void changeDriverTest() {
        ChangeDriverDTO changeDriverDTO = new ChangeDriverDTO();
        when(beanMappingService.mapTo(changeDriverDTO.getDriver(), User.class)).thenReturn(user2);
        when(driveService.findDriveById(any())).thenReturn(drive);

        driveFacade.changeDriver(changeDriverDTO);
        verify(driveService).updateDrive(drive);
        assertThat(drive.getDriver()).isEqualToComparingFieldByField(user2);
    }

    @Test
    void changeDate() {
        doNothing().when(driveService).updateDrive(drive);
        when(driveService.findDriveById(drive.getId())).thenReturn(drive);
        driveFacade.changeDate(drive.getId(), new GregorianCalendar(2000, Calendar.JANUARY, 10).getTime());
        verify(driveService).updateDrive(drive);
        assertThat(drive.getDate()).isEqualToIgnoringHours(new GregorianCalendar(2000, Calendar.JANUARY, 10).getTime());
    }
    @Test
    void findDriveById() {
        when(beanMappingService.mapTo(drive, DriveDTO.class)).thenReturn(driveDTO);
        when(driveService.findDriveById(drive.getId())).thenReturn(drive);

        DriveDTO returned = driveFacade.findDriveById(drive.getId());
        assertThat(returned).isEqualToComparingFieldByField(driveDTO);
    }
    @Test
    void findAllDrives() {
        List<Drive> expectedDrives = new ArrayList<>();
        expectedDrives.add(drive);
        when(driveService.findAllDrives()).thenReturn(expectedDrives);

        List<DriveDTO> expectedDrivesDTO = new ArrayList<>();
        expectedDrivesDTO.add(driveDTO);
        when(beanMappingService.mapTo(expectedDrives, DriveDTO.class)).thenReturn(expectedDrivesDTO);

        Collection<DriveDTO> actualDrivesDTO = new ArrayList<>(driveFacade.findAllDrives());

        verify(driveService).findAllDrives();
        assertThat(actualDrivesDTO).containsOnly(driveDTO);
    }



}
