package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;

import java.util.Collection;
import java.util.List;

public class UserService {

    public User createUser(User user) {
        return null;
    }

    public User editUser(User user) {
        return null;
    }

    public User findUserById(Long id) {
        return null;
    }

    public void addCustomerToDrive(Long driveId, Long userId) {

    }

    public void removeCustomerFromDrive(Long driveId, Long userId) {

    }

    public void removeUser(User user) {
    }

    public List<User> findAllUsers() {
        return null;
    }

    public User findUserByLogin(String login) {
        return null;
    }

    public User findUserByFullName(String firstName, String lastName) {
        return null;
    }

    public boolean addDriveAsDriver(Long userId, Long driveId) {
        return false;
    }

    public boolean addDriveAsPassenger(Long userId, Long driveId) {
        return false;
    }

    public Collection<Drive> getDriverDrives(Long id) {
        return null;
    }

    public Collection<Drive> getPassengerDrives(Long id) {
        return null;
    }


}

