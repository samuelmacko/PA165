/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.UserService;
import javax.inject.Inject;
import org.dozer.DozerConverter;

/**
 *
 * @author Matus Sakala
 */
public class UserIdConverter extends DozerConverter<User, Long> {
    @Inject
    private UserService userService;

    public UserIdConverter() {
        super(User.class, Long.class);
    }

    @Override
    public Long convertTo(User user, Long id) {
        return user == null ? null : user.getId();
    }

    @Override
    public User convertFrom(Long id, User user) {
        return id == null ? null : userService.findUserById(id);
    }
}
