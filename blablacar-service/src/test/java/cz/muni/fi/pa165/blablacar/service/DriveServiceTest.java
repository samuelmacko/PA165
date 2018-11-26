package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriveServiceTest {
    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private DriveService driveService;

    private User validUser;
    private Drive validDrive;
    private City validFromCity;
    private City validToCity;

    private UserDTO validUserDTO;
    private CityDTO validCityDTO;
    private DriveDTO validDriveDTO;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init() {
    }


    @Test
    void testAddDrive() {

    }
    @Test
    void testRemoveDrive() {

    }
    @Test
    void testUpdateDrive() {

    }
    @Test
    void testAddCustomer() {

    }
    @Test
    void testRemoveCustomer() {

    }
    @Test
    void testAddComment() {

    }
    @Test
    void testRemoveComment() {

    }
    @Test
    void findDriveById() {

    }


    @Test(expectedExceptions=IllegalArgumentException.class)
    void testAddDriveException() {
        driveService.addDrive(null);
    }
    @Test(expectedExceptions=IllegalArgumentException.class)
    void testRemoveDriveException() {
        driveService.removeDrive(null);
    }
    @Test(expectedExceptions=IllegalArgumentException.class)
    void testUpdateDriveException() {
        driveService.updateDrive(null);
    }
    @Test(expectedExceptions=IllegalArgumentException.class)
    void testAddCustomerException() {
        driveService.addCustomer(null, null);
    }
    @Test(expectedExceptions=IllegalArgumentException.class)
    void testRemoveCustomerException() {
        driveService.removeCustomer(null, null);
    }
    @Test(expectedExceptions=IllegalArgumentException.class)
    void testAddCommentException() {
        driveService.addComment(null, null);
    }
    @Test(expectedExceptions=IllegalArgumentException.class)
    void testRemoveCommentException() {
        driveService.removeComment(null, null);
    }
    @Test(expectedExceptions=IllegalArgumentException.class)
    void testFindDriveByIdException() {
        driveService.findDriveById(null);
    }


}
