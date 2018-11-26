package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.CityDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Tests for City Service
 *
 * @author Samuel Macko
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CityServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CityDao cityDao;

    @Autowired
    @InjectMocks
    private CityService cityService;

    private City city;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void initMethod() {
        city = new City();
        city.setId(1L);
        city.setName("Mrkvickovo");

    }

    @Test
    void createCityTest() {
        doNothing().when(cityDao).addCity(any());
        City createdCity = cityService.createCity(city);
        verify(cityDao).addCity(city);
        assertThat(createdCity).isEqualToComparingFieldByField(city);
    }

    @Test
    void createCityNullTest() {
        assertThatThrownBy(() -> cityService.createCity(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateCityNameTest() {
        doNothing().when(cityDao).updateCity(any());
        city.setName("Kalerabovo");
        cityService.updateCity(city);
        verify(cityDao).updateCity(city);
    }

    @Test
    void updateCityNullTest() {
        assertThatThrownBy(() -> cityService.updateCity(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void removeCityTest() {
        doNothing().when(cityDao).removeCity(any());
        cityService.deleteCity(city);
        verify(cityDao).removeCity(city);
    }

    @Test
    void removeCityNullTest() {
        assertThatThrownBy(() -> cityService.deleteCity(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findCityByIdTest() {
        when(cityDao.findCityById(city.getId())).thenReturn(city);
        City resultCity = cityService.findById(city.getId());
        assertThat(resultCity).isEqualToComparingFieldByField(city);
    }

    @Test
    void findCityByIdNullTest() {
        assertThatThrownBy(() -> cityService.findById(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findCityByNameTest() {
        when(cityDao.findCityByName(city.getName())).thenReturn(city);
        City resultCity = cityService.findByName(city.getName());
        assertThat(resultCity).isEqualToComparingFieldByField(city);
    }

    @Test
    void findCityByNameNullTest() {
        assertThatThrownBy(() -> cityService.findByName(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findAllCitiesTest() {
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(city);
        when(cityDao.findAll()).thenReturn(expectedCities);
        List<City> actualCities = cityService.findAll();
        assertThat(actualCities).containsExactlyInAnyOrder(city);
    }
}
