/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.service.CityService;
import javax.inject.Inject;
import org.dozer.DozerConverter;

/**
 *
 * @author Matus Sakala
 */
public class CityIdConverter extends DozerConverter<City, Long> {
    @Inject
    private CityService cityService;

    public CityIdConverter() {
        super(City.class, Long.class);
    }

    @Override
    public Long convertTo(City city, Long id) {
        return city == null ? null : city.getId();
    }

    @Override
    public City convertFrom(Long id, City place) {
        return id == null ? null : cityService.findById(id);
    }
}
