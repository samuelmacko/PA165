package cz.muni.fi.pa165.blablacar.persistence.dao;


import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Tests for City dao implementation
 * @author Samuel Macko
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CityDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CityDao cityDao;

    private City c1;

    @BeforeMethod
    public void setUp() {
        c1 = new City();
        c1.setName("City1");
        cityDao.addCity(c1);
    }

    @Test
    public void testFindCityById() {
        City found = cityDao.findCityById(c1.getId());
        Assert.assertEquals(c1.getId(), found.getId());
        Assert.assertEquals(c1.getName(), found.getName());
    }

    @Test
    public void testFindCityByName() {
        City found = cityDao.findCityByName(c1.getName());
        Assert.assertEquals(c1.getId(), found.getId());
        Assert.assertEquals(c1.getName(), found.getName());
    }

    @Test
    public void testUpdate() {
        City found = cityDao.findCityById(c1.getId());
        found.setName("TestCity");
        cityDao.updateCity(found);
        found = cityDao.findCityById(c1.getId());
        Assert.assertEquals(c1.getName(), found.getName());
    }

    @Test
    public void testFindAll() {
        City c2 = new City();
        c2.setName("City2");
        cityDao.addCity(c2);
        List<City> found = cityDao.findAll();
        Assert.assertEquals(found.size(), 2);
    }

    @Test
    public void testRemove() {
        cityDao.removeCity(c1);
        Assert.assertNull(cityDao.findCityById(c1.getId()));
    }

}
