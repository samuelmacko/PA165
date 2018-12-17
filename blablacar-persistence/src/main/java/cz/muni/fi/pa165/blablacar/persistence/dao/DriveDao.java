package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;

import java.util.List;

/**
 * Drive DAO interface
 *
 * @author Samuel Macko
 */
public interface DriveDao {
    /**
     * Adds drive to database
     *
     * @param d drive to be added
     * @throws IllegalArgumentException if d is null
     */
    public void addDrive(Drive d) throws IllegalArgumentException;

    /**
     * Removes drive from database
     *
     * @param d drive to be removed
     * @throws IllegalArgumentException if d is null
     */
    public void removeDrive(Drive d) throws IllegalArgumentException;

    /**
     * Updates drive in database
     *
     * @param d drive to be updated
     * @throws IllegalArgumentException if d is null
     */
    public void updateDrive(Drive d) throws IllegalArgumentException;

    /**
     * Finds drive in database by specific id
     *
     * @param id id of specific drive
     * @return Drive with specified id or null if id was not found
     * @throws IllegalArgumentException if id is null
     */
    public Drive findDriveById(Long id) throws IllegalArgumentException;

    /**
     * Finds drives in database by specific "from" city id
     *
     * @param id id of specific "from" city
     * @return List of drives with specified "from" city id
     * @throws IllegalArgumentException if id is null
     */
    public List<Drive> findDrivesFromCityId(Long id) throws IllegalArgumentException;

    /**
     * Finds drives in database by specific "to" city id
     *
     * @param id id of specific "to" city
     * @return List of drives with specified "to" city id
     * @throws IllegalArgumentException if id is null
     */
    public List<Drive> findDrivesToCityId(Long id) throws IllegalArgumentException;

    /**
     * Find all drives
     *
     * @return list of all drives in database
     */
    public List<Drive> findAll();
}
