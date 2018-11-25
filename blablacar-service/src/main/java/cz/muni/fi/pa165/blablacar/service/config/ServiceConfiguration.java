package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Martin Geletka
 */

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan("cz.muni.fi.pa165.blablacar.service.")
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }

}