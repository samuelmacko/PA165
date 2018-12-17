/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.api.facade;

import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Matus Sakala
 */
public interface UserFacade {

    /**
     * Creates a user
     * @param userDTO userDTO object from which User will be created
     * @return user id
     */
    Long createUser(UserDTO userDTO);

    /**
     * Finds user with specified id
     * @param id user's id
     * @return user with specified id
     */
    UserDTO findUserById(Long id);

    /**
     * Finds user with specified login
     * @param login user's login
     * @return user with specified login
     */
    UserDTO findUserByLogin(String login);
    /**
     * Finds user with specified first and last name
     * @param firstName user's first name
     * @param lastName user's last name
     * @return user with specified name
     */
    UserDTO findUserByFullName(String firstName, String lastName);
    /**
     * Updates user's parameters according to @param user
     * @param user user with specified parameters
     * @return true/false according to outcome
     */
    boolean editUser(UserDTO user);
    /**
     * Removes a user
     * @param user to be removed
     * @return true/false according to outcome
     */
    boolean removeUser(UserDTO user);
    /**
     * Finds all users
     * @return Collection of all users
     */
    List<UserDTO> getAllUsers();

    /**
     * Adds drive to user drivesAsPassenger, also adds user to drive's passengers
     * @param userId user to be added
     * @param driveId specific drive
     * @return
     */
    boolean addDriveAsPassenger(Long driveId, Long userId);
    /**
     * Gets all user's drives, where he was drives
     * @param id id of user
     * @return collection of drives
     */
    List<DriveDTO> getDriverDrives(Long id);
    /**
     * Gets all user's drives, where he was passenger
     * @param id if of user
     * @return collection of drives
     */
    List<DriveDTO> getPassengerDrives(Long id);
    
    boolean isAdmin(UserDTO u);
    
    boolean authenticate(UserDTO u);

}
