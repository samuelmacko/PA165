package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.city.CityCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.CityService;
import cz.muni.fi.pa165.blablacar.service.CityServiceImpl;
import cz.muni.fi.pa165.blablacar.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for City facade
 *
 * @author Samuel Macko
 */

public class CityFacadeTest {

    @Autowired
    @InjectMocks
    private CityFacadeImpl cityFacade;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private CityServiceImpl cityService;

    private City city1;
    private City city2;

    private CityDTO cityDTO1;
    private CityDTO cityDTO2;

    private CityCreateDTO cityCreateDTO;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init() {
        cityCreateDTO = new CityCreateDTO();
        cityCreateDTO.setName("Mrkvickovo");

        cityDTO1 = new CityDTO();
        cityDTO1.setId(1L);
        cityDTO1.setName("Mrkvickovo");

        cityDTO2 = new CityDTO();
        cityDTO2.setId(2L);
        cityDTO2.setName("Kalerabovo");

        city1 = new City();
        city1.setId(1L);
        city1.setName("Mrkvickovo");

        city2 = new City();
        city2.setId(2L);
        city2.setName("Kalerabovo");
    }

    @AfterMethod
    void reset() {
        Mockito.reset(cityService);
    }

    @Test
    void createCityTest() {
        when(beanMappingService.mapTo(cityCreateDTO, City.class)).thenReturn(city1);
        when(cityService.createCity(city1)).thenReturn(city1);
        Long createdId = cityFacade.createCity(cityCreateDTO);
        verify(cityService).createCity(city1);
        assertThat(createdId).isEqualTo(city1.getId());
    }

    @Test
    void creatCityNullTest() {
        assertThatThrownBy(() -> cityFacade.createCity(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateCityNameTest() {
        doNothing().when(cityService).updateCity(any());
        when(cityService.findById(city1.getId())).thenReturn(city1);
        cityFacade.changeName(city1.getId(), "Petrzlenovo");
        city1.setName("Petrzlenovo");
        verify(cityService).updateCity(city1);
    }

    @Test
    void changeCityNameIdNullTest() {
        assertThatThrownBy(() -> cityFacade.changeName(null, "Petrzlenovo"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deleteCityTest() {
        doNothing().when(cityService).deleteCity(any());
        when(cityService.findById(city1.getId())).thenReturn(city1);
        cityFacade.deleteCity(cityDTO1.getId());
        verify(cityService).deleteCity(city1);
    }

    @Test
    void deleteCityNullTest() {
        assertThatThrownBy(() -> cityFacade.findCityById(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findCityByIdTest() {
        when(beanMappingService.mapTo(city1, CityDTO.class)).thenReturn(cityDTO1);
        when(cityService.findById(city1.getId())).thenReturn(city1);
        CityDTO tempCityDTO = cityFacade.findCityById(city1.getId());
        assertThat(tempCityDTO).isEqualToComparingFieldByField(cityDTO1);
    }

    @Test
    void findCityByIdNullTest() {
        assertThatThrownBy(() -> cityFacade.findCityById(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findCityByNameTest() {
        when(beanMappingService.mapTo(city1, CityDTO.class)).thenReturn(cityDTO1);
        when(cityService.findByName(city1.getName())).thenReturn(city1);
        CityDTO tempCityDTO = cityFacade.findCityByName(city1.getName());
        assertThat(tempCityDTO).isEqualToComparingFieldByField(cityDTO1);
    }

    @Test
    void fingCityByNameNullTest() {
        assertThatThrownBy(() -> cityFacade.findCityByName(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void fingAllCitiesTest() {
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(city1);
        expectedCities.add(city2);
        when(cityService.findAll()).thenReturn(expectedCities);

        List<CityDTO> expectedCitiesDTO = new ArrayList<>();
        expectedCitiesDTO.add(cityDTO1);
        expectedCitiesDTO.add(cityDTO2);
        when(beanMappingService.mapTo(expectedCities, CityDTO.class)).thenReturn(expectedCitiesDTO);

        List<CityDTO> actualCitiesDTO = cityFacade.findAllCities();
        verify(cityService).findAll();
        assertThat(actualCitiesDTO).containsExactlyInAnyOrder(cityDTO1, cityDTO2);
    }

}
