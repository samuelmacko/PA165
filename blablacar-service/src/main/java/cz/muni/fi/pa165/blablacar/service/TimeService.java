/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service;

import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matus Sakala
 */
@Service
public interface TimeService {
    Date getCurrentTime();
}