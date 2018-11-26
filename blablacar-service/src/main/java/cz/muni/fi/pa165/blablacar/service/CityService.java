package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.entity.City;

import java.util.List;

public interface CityService {

    City createCity(City city) throws IllegalArgumentException;
    void updateCity(City city) throws IllegalArgumentException;
    void deleteCity(City city) throws IllegalArgumentException;
    City findById(Long id) throws IllegalArgumentException;
    City findByName(String name) throws IllegalArgumentException;
    List<City> findAll();
}
