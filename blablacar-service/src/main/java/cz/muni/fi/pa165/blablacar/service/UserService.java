package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Matus Sakala
 */
@Service
public interface UserService {
    /**
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

    public List<Drive> findDrivesAsDriver(Long id) throws IllegalArgumentException;

    public List<Drive> findDrivesAsPassenger(Long id) throws IllegalArgumentException;

    public void addCustomerToDrive(Long driveId, Long userId);

    public void removeCustomerFromDrive(Long driveId, Long userId);

    public Map<User, BigDecimal> getUsersReward();

    public Map<User, BigDecimal> getUsersSpending();

    public Map<User, BigDecimal> getUsersTotalProfit();

    public boolean isAdmin(User u);

    public boolean authenticate(User u, String password);

    public void registerUser(User u, String unencryptedPassword);


}

