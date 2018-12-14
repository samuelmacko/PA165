package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.CityDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;

import javax.inject.Inject;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Inject
    private CityDao cityDao;

    @Override
    public City createCity(City city) throws IllegalArgumentException {
        if (city == null) {
            throw new IllegalArgumentException("City was null.");
        }

        cityDao.addCity(city);
        return city;
    }

    @Override
    public void updateCity(City city) throws IllegalArgumentException {
        if (city == null) {
            throw new IllegalArgumentException("City was null.");
        }

        cityDao.updateCity(city);
    }

    @Override
    public void deleteCity(City city) throws IllegalArgumentException {
        if (city == null) {
            throw new IllegalArgumentException("City was null.");
        }

        cityDao.removeCity(city);
    }

    @Override
    public City findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("City id null.");
        }

        return cityDao.findCityById(id);
    }

    @Override
    public City findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("City name null.");
        }

        return cityDao.findCityByName(name);
    }

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }
}
