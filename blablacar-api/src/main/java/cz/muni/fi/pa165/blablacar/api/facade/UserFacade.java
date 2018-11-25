/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.api.facade;

import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import java.util.Collection;

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

    UserDTO findUserById(Long id);

    UserDTO findUserByLogin(String login);

    UserDTO findUserByFullName(String firstName, String lastName);

    boolean editUser(UserDTO user);

    boolean removeUser(UserDTO user);

    Collection<UserDTO> getAllUsers();

    boolean addDriveAsDriver(Long userId, Long driveId);

    boolean addDriveAsPassenger(Long userId, Long driveId);

    Collection<DriveDTO> getDriverDrives(Long id);

    Collection<DriveDTO> getPassengerDrives(Long id);

}
