package cz.muni.fi.pa165.blablacar.persistence.dao;


import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.test.annotation.Rollback;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DriveDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private DriveDao driveDao;

    private Drive storedDrive;

    private Date date = new GregorianCalendar(2018, Calendar.APRIL, 1).getTime();

    @BeforeMethod
    private void setUp() {
        storedDrive = new Drive();

        User driver = new User();
        driver.setFirstName("John");
        driver.setLastName("Doe");
        driver.setLogin("j.doe");
        driver.setPassword("johnDoe");
        em.persist(driver);

        storedDrive.setDriver(driver);

        City cityA = new City();
        cityA.setName("Praha");
        em.persist(cityA);

        City cityB = new City();
        cityB.setName("Brno");
        em.persist(cityB);

        storedDrive.setFromCity(cityA);
        storedDrive.setToCity(cityB);

        storedDrive.setCapacity(4);

        storedDrive.setPrice(100.0);
        storedDrive.setDate(date);

        em.persist(storedDrive);

    }

    private Drive createTestDrive() {
        Drive drive = new Drive();

        User driver = new User();
        driver.setFirstName("Johnny");
        driver.setLastName("Bravo");
        driver.setLogin("j.bravo");
        driver.setPassword("johnnyBravo");
        em.persist(driver);

        drive.setDriver(driver);

        City cityA = new City();
        cityA.setName("Bratislava");
        em.persist(cityA);

        City cityB = new City();
        cityB.setName("Ostrava");
        em.persist(cityB);

        drive.setFromCity(cityA);
        drive.setToCity(cityB);

        drive.setCapacity(4);

        drive.setPrice(99.9);
        drive.setDate(date);

        return drive;
    }

    @Test
    public void addDriveTest() {
        Drive drive = createTestDrive();
        driveDao.addDrive(drive);

        assertThat(drive.getId()).isNotNull();

        Drive found = em.createQuery("select d from Drive d where d.id = :id", Drive.class)
                .setParameter("id", drive.getId())
                .getSingleResult();

        assertThat(found).isEqualTo(drive);
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void addNullDriveTest() {
        driveDao.addDrive(null);
    }


    @Test
    public void findDriveByIdTest() {
        assertThat(driveDao.findDriveById(storedDrive.getId())).isEqualTo(storedDrive);
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void findDriveByNullIdTest() {
        driveDao.findDriveById(null);
    }

    @Test
    public void updateDriveTest() {
        storedDrive.setPrice(55.5);
        driveDao.updateDrive(storedDrive);
        Drive foundDrive = driveDao.findDriveById(storedDrive.getId());
        assertThat(foundDrive.getPrice()).isEqualTo(55.5);
        assertThat(foundDrive.getCapacity()).isEqualTo(4);
        assertThat(foundDrive.getFromCity().getName()).isEqualTo("Praha");
        assertThat(foundDrive.getToCity().getName()).isEqualTo("Brno");
        assertThat(foundDrive.getDriver().getLogin()).isEqualTo("j.doe");
        assertThat(foundDrive.getDate()).isEqualTo(date);
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void updateNullDriveTest() {
        driveDao.updateDrive(null);
    }

    @Test
    public void findAllDrivesTest() {
        Drive drive = createTestDrive();
        drive.setPrice(1000);
        driveDao.addDrive(drive);

        Long countDrives = (Long) em.createQuery("select count(d) from Drive d").getSingleResult();

        assertThat(driveDao.findAll().size()).isEqualTo(countDrives.intValue());
    }

    @Test
    public void removeDriveTest() {
        driveDao.removeDrive(storedDrive);
        assertThat(driveDao.findDriveById(storedDrive.getId())).isNull();
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void removeNullDriveTest() {
        driveDao.removeDrive(null);
    }

}
