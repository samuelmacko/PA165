/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Implementation of UserDao interface
 * @author Matus Sakala
 */
public class UserDaoImpl implements UserDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addUser(User u) {
        em.persist(u);
    }

    @Override
    public void updateUser(User u) {
        em.merge(u);
    }

    @Override
    public void removeUser(User u) {
        em.remove(u);
    }

    @Override
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findUserByFullName(String fName, String lName) {
        try{
            return em.createQuery("select u from User u where firstName = :fName and lastName = :lName", User.class)
                .setParameter(":fName", fName)
                .setParameter(":lName", lName)
                .getSingleResult();
        } catch(NoResultException nre){
            return null;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        try{
            return em.createQuery("select u from User u where login = :login", User.class)
                .setParameter(":login", login)
                .getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
    
}
