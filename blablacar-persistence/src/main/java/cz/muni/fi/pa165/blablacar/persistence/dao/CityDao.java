/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.City;

import java.util.List;

/**
 * City DAO interface
 *
 * @author Bruno Mizik
 */
public interface CityDao {
    /**
     * Adds city to database
     *
     * @param c city to be added
     */
    public void addCity(City c);

    /**
     * Removes city from database
     *
     * @param c city to be removed
     */
    public void removeCity(City c);

    /**
     * Updates city in database
     *
     * @param c city to be updated
     */
    public void updateCity(City c);

    /**
     * Finds city in database by id
     *
     * @param id id of specific city
     * @return City with specified id
     */
    public City findCityById(Long id);

    /**
     * Finds city by its name
     *
     * @param name name of city
     * @return City with specified name
     */
    public City findCityByName(String name);


    /**
     * Find all cities
     *
     * @return list of all cities in database
     */
    public List<City> findAll();
}
