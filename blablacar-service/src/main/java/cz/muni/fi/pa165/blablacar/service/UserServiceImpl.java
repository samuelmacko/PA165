/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.DriveDao;
import cz.muni.fi.pa165.blablacar.persistence.dao.UserDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matus Sakala
 */
@Service
public class UserServiceImpl implements UserService {

    final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private UserDao userDao;
    
    @Inject
    private DriveDao driveDao;
    
    @Override
    public User createUser(User user) throws IllegalArgumentException{
        if(user == null) throw new IllegalArgumentException(
                UserServiceImpl.class + "Create: user is null");
        if(user.getFirstName() == null || user.getLastName() == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "User does not have Full name");
        if(user.getLogin() == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "User does not have login");
        userDao.addUser(user);
        log.debug(UserServiceImpl.class +
                "User created: " + user.toString());
        return user;
    }

    @Override
    public User findUserById(Long id) {
        if(id == null) throw new IllegalArgumentException(UserServiceImpl.class
            + "Find user by id: id is null");
        User result = userDao.findUserById(id);
        log.debug(UserServiceImpl.class + "User found: " + result.toString());
        return result;
    }

    @Override
    public void addCustomerToDrive(Long driveId, Long userId) {
        if(userId == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Add customer to drive: "
                    + "userID is null");
        if(driveId == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Add customer to drive: "
                    + "driveID is null");
        Drive d = driveDao.findDriveById(driveId);
        if(d == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Add customer to drive: "
                    + "drive with specified ID not found");
        User u = userDao.findUserById(userId);
        if(u == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Add customer to drive: "
                    + "user with specified ID not found");
        if(d.getCustomers().size() == d.getCapacity()) throw new IllegalArgumentException(
            UserServiceImpl.class + "Add customer to drive: "
                    + "drive has full capacity");
        d.addCustomer(u);
        u.addToBeingCustomer(d);
        log.debug(UserServiceImpl.class + "Adding customer +" + u.toString() +
                "to drive " + d.toString());
    }

    @Override
    public void removeCustomerFromDrive(Long driveId, Long userId) {
        if(userId == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Remove customer from drive: "
                    + "userID is null");
        if(driveId == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Remove customer from drive: "
                    + "driveID is null");
        Drive d = driveDao.findDriveById(driveId);
        if(d == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Remove customer from drive: "
                    + "drive with specified ID not found");
        User u = userDao.findUserById(userId);
        if(u == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Remove customer from drive: "
                    + "user with specified ID not found");
        if(!d.getCustomers().contains(u) || !u.getBeingCustomer().contains(d)) throw new IllegalArgumentException(
            UserServiceImpl.class + "Remove customer from drive: "
                    + "specified user is not customer on specified drive -- bidirectional relation not matching");
        d.removeCustomer(u);
        u.getBeingCustomer().remove(d);
        log.debug(UserServiceImpl.class + "removing customer: "
                + u.toString() + "from drive: " + d.toString());
    }

    @Override
    public void editUser(User user) throws IllegalArgumentException {
        if(user == null) throw new IllegalArgumentException(
                UserServiceImpl.class +"Edit: user is null");
        userDao.updateUser(user);
        log.debug(UserServiceImpl.class + "User updated:" + user.toString());
    }

    @Override
    public void deleteUser(User user) throws IllegalArgumentException {
        if(user == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Delete: user is null");
        if(userDao.findUserById(user.getId()) == null) throw new IllegalArgumentException(
            UserServiceImpl.class + "Delete: user not found");
        userDao.removeUser(user);
        log.debug(UserServiceImpl.class + "User deleted:" + user.toString());
    }

    @Override
    public User findUserByFullName(String firstName, String lastName) throws IllegalArgumentException {
        if(firstName == null) throw new IllegalArgumentException(UserServiceImpl.class
            + "Find user by FullName: firstName is null");
        if(lastName == null) throw new IllegalArgumentException(UserServiceImpl.class
            + "Find user by FullName: lastName is null");
        User result = userDao.findUserByFullName(firstName, lastName);
        log.debug(UserServiceImpl.class + "User found: " + result.toString());
        return result;
    }

    @Override
    public User findUserByLogin(String login) throws IllegalArgumentException {
        if(login == null) throw new IllegalArgumentException(UserServiceImpl.class
            + "Find user by Login: login is null");
        User result = userDao.findUserByLogin(login);
        log.debug(UserServiceImpl.class + "User found: " + result.toString());
        return result;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> found = userDao.findAll();
        log.debug(UserServiceImpl.class + "Find all users: found " + 
                found.size() + " users");
        return found;
    }

    @Override
    public List<Drive> findDrivesAsDriver(Long id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException(UserServiceImpl.class
            + "Find drives as driver: user id is null");
        User u = userDao.findUserById(id);
        List<Drive> result = new ArrayList<>(u.getBeingDriver());
        log.debug(UserServiceImpl.class + "Find drives as driver, found " + 
                result.size() + " drives");
        return result;
    }

    @Override
    public List<Drive> findDrivesAsPassenger(Long id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException(UserServiceImpl.class
            + "Find drives as passenger: user id is null");
        User u = userDao.findUserById(id);
        List<Drive> result = new ArrayList<>(u.getBeingCustomer());
        log.debug(UserServiceImpl.class + "Find drives as passenger, found " + 
                result.size() + " drives");
        return result;
    }
    
}
