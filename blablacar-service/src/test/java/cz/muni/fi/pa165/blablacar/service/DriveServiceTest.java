package cz.muni.fi.pa165.blablacar.service;

import com.google.common.collect.Lists;
import cz.muni.fi.pa165.blablacar.persistence.dao.DriveDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//import cz.muni.fi.pa165.blablacar.service.config.ServiceConfiguration;

//@ContextConfiguration(classes = ServiceConfiguration.class)
public class DriveServiceTest {
    @Mock
    private DriveDao driveDao;

    @Autowired
    @InjectMocks
    private DriveServiceImpl driveService;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private User validUser;
    private User validUser2;
    private User validUser3;

    private Drive validDrive;

    private City validFromCity;
    private City validToCity;

    private Comment validComment;


    @BeforeMethod
    public void init() {
        validUser = new User();
        validUser.setFirstName("John");
        validUser.setLastName("Driver");
        validUser.setPassword("cicada3301");
        validUser.setLogin("driver");
        validUser.setId(1L);

        validUser2 = new User();
        validUser2.setFirstName("John");
        validUser2.setLastName("Passenger");
        validUser2.setPassword("cicadasdfsafa3301");
        validUser2.setLogin("passanger");
        validUser2.setId(2L);

        validUser3 = new User();
        validUser3.setFirstName("John");
        validUser3.setLastName("Passenger2");
        validUser3.setPassword("abcasd123");
        validUser3.setLogin("passanger2");
        validUser3.setId(3L);

        validFromCity = new City();
        validFromCity.setName("Brno");

        validToCity = new City();
        validToCity.setName("Praha");

        validDrive = new Drive();
        validDrive.setCapacity(4);
        validDrive.setDriver(validUser);
        validDrive.setPrice(new BigDecimal(100.0));
        validDrive.setFromCity(validFromCity);
        validDrive.setToCity(validToCity);
        validDrive.setId(1L);

        validComment = new Comment();
        validComment.setAuthor(validUser);
        validComment.setContent("Best day ever");
        validComment.setDrive(validDrive);
    }

    @AfterMethod
    void reset() {
        Mockito.reset(driveDao);
    }


    @Test
    void testAddDrive() {
        doNothing().when(driveDao).addDrive(any());
        Drive addedDrive = driveService.addDrive(validDrive);

        verify(driveDao).addDrive(validDrive);
        assertThat(addedDrive).isEqualToComparingFieldByField(validDrive);
    }

    @Test
    void testRemoveDrive() {
        doNothing().when(driveDao).removeDrive(validDrive);
        driveService.removeDrive(validDrive);
        verify(driveDao).removeDrive(validDrive);
    }

    @Test
    void testUpdateDrive() {
        validDrive.setCapacity(5);
        doNothing().when(driveDao).updateDrive(validDrive);
        driveService.updateDrive(validDrive);
        verify(driveDao).updateDrive(validDrive);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testAddCustomer() {
        driveService.addCustomer(validDrive, validUser2);
        assertThat(validDrive.getCustomers().contains(validUser2));
        driveService.addCustomer(validDrive, validUser2);
    }

    @Test
    void testAddPresentCustomer() {
        driveService.addCustomer(validDrive, validUser2);
        assertThat(validDrive.getCustomers().contains(validUser2));
    }

    @Test
    void testRemoveCustomer() {
        driveService.addCustomer(validDrive, validUser2);
        assertThat(validDrive.getCustomers().contains(validUser2));

        driveService.removeCustomer(validDrive, validUser2);
        assertThat(!validDrive.getCustomers().contains(validUser2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testRemoveNonexistentCustomer() {
        driveService.removeCustomer(validDrive, validUser2);
        assertThat(validDrive.getCustomers().contains(validUser3));
    }

    @Test
    void testAddComment() {
        driveService.addComment(validDrive, validComment);
        assertThat(validDrive.getComments().contains(validComment));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testAddExistingComment() {
        driveService.addComment(validDrive, validComment);
        driveService.addComment(validDrive, validComment);
    }

    @Test
    void testRemoveComment() {
        driveService.addComment(validDrive, validComment);
        assertThat(validDrive.getComments().contains(validComment));
        driveService.removeComment(validDrive, validComment);
        assertThat(!validDrive.getComments().contains(validComment));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testRemoveNonexistentComment() {
        driveService.removeComment(validDrive, validComment);
    }

    @Test
    void findDriveById() {
        when(driveDao.findDriveById(validDrive.getId())).thenReturn(validDrive);

        Drive result = driveService.findDriveById(validDrive.getId());

        verify(driveDao).findDriveById(validDrive.getId());
        assertThat(result).isEqualTo(validDrive);
    }

    @Test
    void findAllTest() {
        when(driveDao.findAll()).thenReturn(Lists.newArrayList(validDrive));

        List<Drive> result = driveService.findAllDrives();

        verify(driveDao).findAll();
        assertThat(result).isEqualTo(Lists.newArrayList(validDrive));
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    void testAddDriveException() {
        driveService.addDrive(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testRemoveDriveException() {
        driveService.removeDrive(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testUpdateDriveException() {
        driveService.updateDrive(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testAddCustomerException() {
        driveService.addCustomer(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testRemoveCustomerException() {
        driveService.removeCustomer(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testAddCommentException() {
        driveService.addComment(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testRemoveCommentException() {
        driveService.removeComment(null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testFindDriveByIdException() {
        driveService.findDriveById(null);
    }


}
