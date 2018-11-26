/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.DriveService;
import cz.muni.fi.pa165.blablacar.service.UserService;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/** Implementation of User facade
 *
 * @author Matus Sakala
 */
public class UserFacadeImpl implements UserFacade{

    private final static Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Inject
    private UserService userService;
    
    @Inject
    private DriveService driveService;

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createUser(UserDTO userDTO) {
        User mappedUser = beanMappingService.mapTo(userDTO, User.class);

        User newUser = userService.createUser(mappedUser);
        log.debug(UserFacadeImpl.class +
                "Created new User: " + newUser.toString());

        return newUser.getId();
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = userService.findUserById(id);
        if (user == null){
            log.debug(UserFacadeImpl.class +
                    "Find user by id: " + id + ", not found");
            return null;
        }

        log.debug(UserFacadeImpl.class +
                "Found user: " + user.toString());

        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByLogin(String login) {
        User user = userService.findUserByLogin(login);
        if(user == null){
            log.debug(UserFacadeImpl.class + "Find user by login: " + 
                    login + ", not found");
        }
        log.debug(UserFacadeImpl.class + "Found user " + user.toString());
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByFullName(String firstName, String lastName) {
        User user = userService.findUserByFullName(firstName, lastName);
        if(user == null){
            log.debug(UserFacadeImpl.class + "Find user by full name " + 
                    firstName + " " + lastName + ", not found");
        }
        log.debug(UserFacadeImpl.class + "Found user " + user.toString());
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public boolean editUser(UserDTO user) {
        userService.editUser(beanMappingService.mapTo(user, User.class));
        log.debug(UserFacadeImpl.class +
                "Edited user: " + user.toString());
        return true;
    }

    @Override
    public boolean removeUser(UserDTO user) {
        userService.deleteUser(beanMappingService.mapTo(user, User.class));
        log.debug(UserFacadeImpl.class + "Deleted user " + user.toString());
        return true;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = (List<User>) userService.findAllUsers();
        log.debug(UserFacadeImpl.class +
                "found " + users.size() + " users");

        return beanMappingService.mapTo(users, UserDTO.class);
    }


    @Override
    public boolean addDriveAsPassenger(Long driveId, Long userId) {
        userService.addCustomerToDrive(driveId, userId);
        log.debug(UserFacadeImpl.class + "Adding user with id " + userId +
                " to drive with id" + driveId);
        return true;
    }

    @Override
    public List<DriveDTO> getDriverDrives(Long id) {
        List<Drive> drives = userService.findDrivesAsDriver(id);
        log.debug(UserFacadeImpl.class + "Get drives as driver, found "
            + drives.size() + " drives");
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }

    @Override
    public List<DriveDTO> getPassengerDrives(Long id) {
        List<Drive> drives = userService.findDrivesAsPassenger(id);
        log.debug(UserFacadeImpl.class + "Get drives as passenger, found "
            + drives.size() + " drives");
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }
    
}
