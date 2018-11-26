package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.city.CityCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
import cz.muni.fi.pa165.blablacar.api.facade.CityFacade;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CityFacadeImpl implements CityFacade
{
    private final static Logger log = LoggerFactory.getLogger(CityFacadeImpl.class);

    @Inject
    private CityService cityService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createCity(CityCreateDTO cityCreateDTO) {
        if (cityCreateDTO == null) {
            throw new IllegalArgumentException("cityCreateDTO was null.");
        }

        City mappedCity = beanMappingService.mapTo(cityCreateDTO, City.class);
        City createdCity = cityService.createCity(mappedCity);

        log.info("City has been created id(" + createdCity.getId() + ")");

        return createdCity.getId();
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityService.findById(id);
        cityService.deleteCity(city);

        log.info("City id(" + city.getId() + ") has been deleted");
    }

    public void changeName(Long id, String newName) {
        if (id == null) {
            throw new IllegalArgumentException("City id was null.");
        }
        City city = cityService.findById(id);
        city.setName(newName);
        cityService.updateCity(city);

        log.info("City name has been updated for city id(" + city.getId() + ")");
    }

    public List<CityDTO> findAllCities()
    {
        return beanMappingService.mapTo(cityService.findAll(), CityDTO.class);
    }

    @Override
    public CityDTO findCityByName(String name)
    {
        if (name == null) {
            throw new IllegalArgumentException("City name was null.");
        }
        City city = cityService.findByName(name);
        return (city == null) ? null : beanMappingService.mapTo(city, CityDTO.class);
    }

    @Override
    public CityDTO findCityById(Long id)
    {
        if (id == null) {
            throw new IllegalArgumentException("City id was null.");
        }
        City city = cityService.findById(id);
        return (city == null) ? null : beanMappingService.mapTo(city, CityDTO.class);
    }

}
