/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import java.util.List;

/**
 * User DAO interface
 * @author Matus Sakala
 */
public interface UserDao {
    /**
     * Adds user to database
     * @param u user to be added
     */
    public void addUser(User u);
    
    /**
     * Removes use from database
     * @param u user to be removed
     */
    public void removeUser(User u);
    
    /**
     * Updates user in database
     * @param u user to be updated
     */
    public void updateUser(User u);
    
    /**
     * Finds user in database by id
     * @param id id of specific user
     * @return User with specified id
     */
    public User findUserById(Long id);
    
    /**
     * Finds user by his full name
     * @param fName first name of user
     * @param lName last name of user
     * @return User with specified name
     */
    public User findUserByFullName(String fName, String lName);
    
    /**
     * Finds user by his login
     * @param login login of user
     * @return User with specified login
     */
    public User findUserByLogin(String login);
    
    /**
     * Find all users
     * @return list of all users in database
     */
    public List<User> findAll();
}
