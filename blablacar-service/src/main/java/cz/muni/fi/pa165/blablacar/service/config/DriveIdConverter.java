/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.service.DriveService;
import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author Matus Sakala
 */
public class DriveIdConverter extends DozerConverter<Drive, Long> {
    @Inject
    private DriveService driveService;

    private final static Logger log = LoggerFactory.getLogger(DriveIdConverter.class);


    public DriveIdConverter() {
        super(Drive.class, Long.class);
    }

    @Override
    public Long convertTo(Drive drive, Long id) {
        return drive == null ? null : drive.getId();
    }

    @Override
    public Drive convertFrom(Long id, Drive drive) {
        if (id == null) {
            return null;
        }
        Drive foundDrive = null;
        try {
            foundDrive = driveService.findDriveById(id);
        } catch (Exception ex) {
            log.debug("Cannot find drive with id" + id);
        }
        return foundDrive;
    }
}
