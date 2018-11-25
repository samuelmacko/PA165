package cz.muni.fi.pa165.blablacar.api.facade;

import cz.muni.fi.pa165.blablacar.api.dto.*;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;

import java.util.Date;
import java.util.List;

/**
 * Facade interface for City
 *
 * @author Bruno Mizik
 */
public interface CityFacade {

    /**
     * Create a city
     *
     * @param cityCreateDTO parameters of the City
     * @return id of the city
     */
    Long createCity(CityCreateDTO cityCreateDTO);

    /**
     * Remove a city
     *
     * @param id id of a city
     */
    void deleteCity(Long id);

    /**
     * Change a name
     *
     * @param id id of a city
     * @param newName new name of the city
     */
    void changeName(Long id, String newName);

    /**
     * Find city by id
     *
     * @param id id of a City
     * @return city with a specified id or null if not found
     */
    CityDTO findCityById(Long id);

    /**
     * Find city by name
     *
     * @param name name of a City
     * @return city with a specified name or null if not found
     */
    CityDTO findCityByName(String name);

    /**
     * Find add cities
     *
     * @return list of all cities in database
     */
    List<CityDTO> findAllCities();

}
