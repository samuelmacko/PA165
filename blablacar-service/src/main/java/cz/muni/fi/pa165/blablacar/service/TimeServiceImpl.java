/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Matus Sakala
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentTime() {
        return new Date();
    }

}
