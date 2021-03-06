/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of UserDao interface
 *
 * @author Matus Sakala
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User u) throws IllegalArgumentException {
        if (u == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.persist(u);
    }

    @Override
    public void updateUser(User u) throws IllegalArgumentException {
        if (u == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.merge(u);
    }

    @Override
    public void removeUser(User u) throws IllegalArgumentException {
        if (u == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.remove(u);
    }

    @Override
    public User findUserById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return em.find(User.class, id);
    }

    @Override
    public User findUserByFullName(String fName, String lName) throws IllegalArgumentException {
        if (fName == null) {
            throw new IllegalArgumentException("First name is null");
        }
        if (lName == null) {
            throw new IllegalArgumentException("Last name is null");
        }
        try {
            return em.createQuery("select u from User u where firstName = :fName and lastName = :lName", User.class)
                    .setParameter("fName", fName)
                    .setParameter("lName", lName)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public User findUserByLogin(String login) throws IllegalArgumentException {
        if (login == null) {
            throw new IllegalArgumentException("Login is null");
        }
        try {
            return em.createQuery("select u from User u where login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

}
