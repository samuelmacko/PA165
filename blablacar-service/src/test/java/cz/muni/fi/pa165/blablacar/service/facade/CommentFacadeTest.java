/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.service.config.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Sakala
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CommentFacadeTest {
    @Test
    public void test(){
        assert(true);
    }
    
}
