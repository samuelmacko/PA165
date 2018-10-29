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
 *
 * @author Matus Sakala
 */
public interface UserDao {
    /**
     * Adds user to database
     *
     * @param u user to be added
     * @throws IllegalArgumentException if user is null
     */
    public void addUser(User u) throws IllegalArgumentException;

    /**
     * Removes use from database
     *
     * @param u user to be removed
     * @throws IllegalArgumentException if user is null
     */
    public void removeUser(User u) throws IllegalArgumentException;

    /**
     * Updates user in database
     *
     * @param u user to be updated
     * @throws IllegalArgumentException if user is null
     */
    public void updateUser(User u) throws IllegalArgumentException;

    /**
     * Finds user in database by id
     *
     * @param id id of specific user
     * @return User with specified id
     * @throws IllegalArgumentException if user is null
     */
    public User findUserById(Long id) throws IllegalArgumentException;

    /**
     * Finds user by his full name
     *
     * @param fName first name of user
     * @param lName last name of user
     * @return User with specified name
     * @throws IllegalArgumentException if first or last name is null
     */
    public User findUserByFullName(String fName, String lName) throws IllegalArgumentException;

    /**
     * Finds user by his login
     *
     * @param login login of user
     * @return User with specified login
     * @throws IllegalArgumentException if login is null
     */
    public User findUserByLogin(String login) throws IllegalArgumentException;

    /**
     * Find all users
     *
     * @return list of all users in database
     */
    public List<User> findAll();
}
