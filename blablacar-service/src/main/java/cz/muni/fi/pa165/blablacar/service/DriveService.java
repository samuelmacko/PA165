package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Drive
 *
 * @author Samuel Macko
 */
@Service
public interface DriveService {

    /**
     * Creates a drive
     *
     * @param d a drive to be added
     * @return added drive
     * @throws IllegalArgumentException if d is null
     */
    Drive addDrive(Drive d) throws IllegalArgumentException;

    /**
     * Removes a drive
     *
     * @param d drive to be removed
     * @throws IllegalArgumentException if d is null
     */
    void removeDrive(Drive d) throws IllegalArgumentException;

    /**
     * Updates a drive
     *
     * @param d drive to be updated
     * @throws IllegalArgumentException if d is null
     */
    void updateDrive(Drive d) throws IllegalArgumentException;

    /**
     * Adds a customer to a drive
     *
     * @param d drive
     * @param u user to be added
     * @throws IllegalArgumentException if d or u is null
     */
    void addCustomer(Drive d, User u) throws IllegalArgumentException;

    /**
     * Removes a customer
     *
     * @param d drive
     * @param u customer to be removed
     * @throws IllegalArgumentException if d or u is null
     */
    void removeCustomer(Drive d, User u) throws IllegalArgumentException;

    /**
     * Adds a comment
     *
     * @param d drive
     * @param c comment to be added
     * @throws IllegalArgumentException if d or c is null
     */
    void addComment(Drive d, Comment c) throws IllegalArgumentException;

    /**
     * Removes a comment
     *
     * @param d drive
     * @param c comment to be removed
     * @throws IllegalArgumentException if d or c is null
     */
    void removeComment(Drive d, Comment c) throws IllegalArgumentException;

    /**
     * Finds a drive by a specific id
     *
     * @param id id
     * @return drive with specified id
     * @throws IllegalArgumentException if id is null
     */
    Drive findDriveById(Long id) throws IllegalArgumentException;

    /**
     * Finds all drives
     *
     * @return list of all drives
     */
    List<Drive> findAllDrives();

}
