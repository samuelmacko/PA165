/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matus Sakala
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.blablacar.service")
public class ServiceConfiguration {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozer.xml");
        dozer.setMappingFiles(mappingFiles);
        return dozer;
    }
}
