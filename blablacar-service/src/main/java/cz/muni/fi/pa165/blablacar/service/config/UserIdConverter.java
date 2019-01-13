/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.UserService;
import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author Matus Sakala
 */
public class UserIdConverter extends DozerConverter<User, Long> {
    @Inject
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserIdConverter.class);


    public UserIdConverter() {
        super(User.class, Long.class);
    }

    @Override
    public Long convertTo(User user, Long id) {
        return user == null ? null : user.getId();
    }

    @Override
    public User convertFrom(Long id, User user) {
        if (id == null) {
            return null;
        }
        User foundDrive = null;
        try {
            foundDrive = userService.findUserById(id);
        } catch (Exception ex) {
            log.debug("Cannot find drive with id" + id);
        }
        return foundDrive;

    }
}
