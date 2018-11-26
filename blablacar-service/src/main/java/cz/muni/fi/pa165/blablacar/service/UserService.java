package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Matus Sakala
 */

public interface UserService {
    /**
     *
     * @param user
     * @return
     * @throws IllegalArgumentException
     */
    public User createUser(User user) throws IllegalArgumentException;

    public void editUser(User user) throws IllegalArgumentException;

    public void deleteUser(User user) throws IllegalArgumentException;

    public User findUserById(Long id) throws IllegalArgumentException;

    public User findUserByFullName(String firstName, String lastName) throws IllegalArgumentException;

    public User findUserByLogin(String login) throws IllegalArgumentException;

    public List<User> findAllUsers();

    public Set<Drive> findDrivesAsDriver(Long id) throws IllegalArgumentException;

    public Set<Drive> findDrivesAsPassenger(Long id) throws IllegalArgumentException;

    public void addCustomerToDrive(Long driveId, Long userId);

    public void removeCustomerFromDrive(Long driveId, Long userId);
}


}

