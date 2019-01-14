package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.*;
import cz.muni.fi.pa165.blablacar.api.facade.DriveFacade;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.DriveService;
import cz.muni.fi.pa165.blablacar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Implementation of DriveFacade interface
 *
 * @author Samuel Macko
 */
@Service
@Transactional
public class DriveFacadeImpl implements DriveFacade {

    @Inject
    private DriveService driveService;

    @Inject
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createDrive(DriveCreateDTO driveCreateDTO) {
        Drive mappedDrive = new Drive();
        mappedDrive.setDate(driveCreateDTO.getDate());
        mappedDrive.setPrice(driveCreateDTO.getPrice());
        mappedDrive.setCapacity(driveCreateDTO.getCapacity());
        mappedDrive.setToCity(beanMappingService.mapTo(driveCreateDTO.getToCity(), City.class));
        mappedDrive.setFromCity(beanMappingService.mapTo(driveCreateDTO.getFromCity(), City.class));

        User driver = beanMappingService.mapTo(driveCreateDTO.getDriver(), User.class);
        mappedDrive.setDriver(driver);
        driver.addToBeingDriver(mappedDrive);

        mappedDrive.setCapacity(driveCreateDTO.getCapacity());

        City fromCity = beanMappingService.mapTo(driveCreateDTO.getFromCity(), City.class);
        mappedDrive.setFromCity(fromCity);
        fromCity.addFromDrive(mappedDrive);

        City toCity = beanMappingService.mapTo(driveCreateDTO.getToCity(), City.class);
        mappedDrive.setToCity(toCity);
        toCity.addToDrive(mappedDrive);

        mappedDrive.setPrice(driveCreateDTO.getPrice());

        mappedDrive.setDate(driveCreateDTO.getDate());

        Drive d = driveService.addDrive(mappedDrive);
        return d.getId();
    }

    @Override
    public void removeDrive(Long id) {
        Drive d = driveService.findDriveById(id);
        driveService.removeDrive(d);
    }

    @Override
    public void addCustomer(AddCustomerDTO addCustomerDTO) {
        Drive d = driveService.findDriveById(addCustomerDTO.getDriveId());
        User u = userService.findUserById(addCustomerDTO.getCustomerId());
        if (!d.getCustomers().contains(u)) {
            userService.addCustomerToDrive(d.getId(), u.getId());
        }
    }

    @Override
    public void removeCustomer(RemoveCustomerDTO removeCustomerDTO) {
        Drive d = driveService.findDriveById(removeCustomerDTO.getDriveId());
        User u = userService.findUserById(removeCustomerDTO.getCustomerId());
        if (d.getCustomers().contains(u)) {
            userService.removeCustomerFromDrive(d.getId(), u.getId());
        }
    }

    @Override
    public void changeCapacity(Long id, int newCapacity) {
        Drive d = driveService.findDriveById(id);
        d.setCapacity(newCapacity);
        driveService.updateDrive(d);
    }

    @Override
    public void changeDriver(ChangeDriverDTO changeDriverDTO) {
        Drive d = driveService.findDriveById(changeDriverDTO.getDriveId());
        d.setDriver(beanMappingService.mapTo(changeDriverDTO.getDriver(), User.class));
        driveService.updateDrive(d);
    }

    @Override
    public void changeDate(Long id, Date newDate) {
        Drive d = driveService.findDriveById(id);
        d.setDate(newDate);
        driveService.updateDrive(d);
    }

    @Override
    public DriveDTO findDriveById(Long id) {
        Drive d = driveService.findDriveById(id);
        return beanMappingService.mapTo(d, DriveDTO.class);
    }

    @Override
    public List<DriveDTO> findDrivesByFromCityId(Long id) {
        List<Drive> drives = driveService.findDrivesByFromCityId(id);
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }

    @Override
    public List<DriveDTO> findDrivesByToCityId(Long id) {
        List<Drive> drives = driveService.findDrivesByToCityId(id);
        return beanMappingService.mapTo(drives, DriveDTO.class);
    }


    @Override
    public List<DriveDTO> findAllDrives() {
        return beanMappingService.mapTo(driveService.findAllDrives(), DriveDTO.class);
    }
}
