/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.service.DriveService;
import javax.inject.Inject;
import org.dozer.DozerConverter;

/**
 *
 * @author Matus Sakala
 */
public class DriveIdConverter extends DozerConverter<Drive, Long> {
    @Inject
    private DriveService driveService;

    public DriveIdConverter() {
        super(Drive.class, Long.class);
    }

    @Override
    public Long convertTo(Drive drive, Long id) {
        return drive == null ? null : drive.getId();
    }

    @Override
    public Drive convertFrom(Long id, Drive drive) {
        return id == null ? null : driveService.findDriveById(id);
    }
}
