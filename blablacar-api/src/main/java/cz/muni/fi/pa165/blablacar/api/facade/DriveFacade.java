package cz.muni.fi.pa165.blablacar.api.facade;

import cz.muni.fi.pa165.blablacar.api.dto.*;

import java.util.Date;
import java.util.List;

/**
 * Facade interface for Drive
 *
 * @author Samuel Macko
 */
public interface DriveFacade {

    /**
     * Create a drive
     *
     * @param driveCreateDTO parameters of a Drive
     * @return id of a drive
     */
    Long createDrive(DriveCreateDTO driveCreateDTO);

    /**
     * Remove a drive
     *
     * @param id id of a drive
     */
    void removeDrive(Long id);

    /**
     * Add a customer to a drive
     *
     * @param addCustomerDTO parameters of a new customer
     */
    void addCustomer(AddCustomerDTO addCustomerDTO);

    /**
     * Remove a customer
     *
     * @param removeCustomerDTO parameters of a customer
     */
    void removeCustomer(RemoveCustomerDTO removeCustomerDTO);

    /**
     * Change a capacity
     *
     * @param id          id of a drive
     * @param newCapacity capacity to be assigned
     */
    void changeCapacity(Long id, int newCapacity);

    /**
     * Change a driver
     *
     * @param changeDriverDTO parameters of a driver
     */
    void changeDriver(ChangeDriverDTO changeDriverDTO);

    /**
     * Change a date
     *
     * @param id      id of a drive
     * @param newDate date to be assigned
     */
    void changeDate(Long id, Date newDate);

    /**
     * Find a drive by a drive id
     *
     * @param id id of a drive
     * @return drive with a specified id or null if not found
     */
    DriveDTO findDriveById(Long id);

    /**
     * Find all drives by a "from" city id
     *
     * @param id id of a "from" city
     * @return list of drives with a specified "from" city id
     */
    List<DriveDTO> findDrivesByFromCityId(Long id);

    /**
     * Find all drives by a "to" city id
     *
     * @param id id of a "to" city
     * @return list of drives with a specified "to" city id
     */
    List<DriveDTO> findDrivesByToCityId(Long id);

    /**
     * Find add drives
     *
     * @return list of all drives in database
     */
    List<DriveDTO> findAllDrives();

}
