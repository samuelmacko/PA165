/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.service.CityService;
import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author Matus Sakala
 */
public class CityIdConverter extends DozerConverter<City, Long> {
    @Inject
    private CityService cityService;

    private final static Logger log = LoggerFactory.getLogger(CityIdConverter.class);


    public CityIdConverter() {
        super(City.class, Long.class);
    }

    @Override
    public Long convertTo(City city, Long id) {
        return city == null ? null : city.getId();
    }

    @Override
    public City convertFrom(Long id, City place) {
        if (id == null) {
            return null;
        }
        City foundDrive = null;
        try {
            foundDrive = cityService.findById(id);
        } catch (Exception ex) {
            log.debug("Cannot find drive with id" + id);
        }
        return foundDrive;
    }
}
