package cz.muni.fi.pa165.blablacar.rest.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.*;
import cz.muni.fi.pa165.blablacar.api.facade.DriveFacade;
import cz.muni.fi.pa165.blablacar.rest.ApiUris;
import cz.muni.fi.pa165.blablacar.rest.exceptions.ResourceAlreadyExistException;
import cz.muni.fi.pa165.blablacar.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_DRIVES)
public class DrivesController {

    final static Logger logger = LoggerFactory.getLogger(DrivesController.class);

    @Inject
    private DriveFacade driveFacade;

//    @Inject
//    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DriveDTO> getDrives() {
        logger.debug("rest getRides()");

        return driveFacade.findAllDrives();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO gedDriveById(@PathVariable long id) throws ResourceNotFoundException {
        logger.debug("rest gedDriveById({})", id);

        DriveDTO driveDTO = driveFacade.findDriveById(id);
        if (driveDTO == null) {
            throw new ResourceNotFoundException();
        }
        return driveDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteDrive(@PathVariable Long id) throws ResourceNotFoundException /*, ResourceCannotBeDeletedException*/ {
        logger.debug("rest deleteDrive({})", id);

        try {
            driveFacade.removeDrive(id);
//        } catch (JpaSystemException|TransactionSystemException e) {
//            throw new ResourceCannotBeDeletedException();
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO createDrive(@RequestBody DriveCreateDTO driveCreateDTO) throws ResourceAlreadyExistException {
        logger.debug("rest createDrive()");

        try {
            Long id = driveFacade.createDrive(driveCreateDTO);
            return driveFacade.findDriveById(id);
        } catch (Exception e) {
            throw new ResourceAlreadyExistException();
        }
    }

    @RequestMapping(value = "/add-customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO addCustomer(@RequestBody AddCustomerDTO addCustomerDTO) throws ResourceNotFoundException {
        logger.debug("rest addCustomer(driveId={}, customerId={})", addCustomerDTO.getDriveId(), addCustomerDTO.getCustomerId());

        try {
            driveFacade.addCustomer(addCustomerDTO);
            return driveFacade.findDriveById(addCustomerDTO.getDriveId());
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/remove-customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO removeCustomer(@RequestBody RemoveCustomerDTO removeCustomerDTO) throws ResourceNotFoundException {
        logger.debug("rest removeCustomer(driveId={}, customerId={})", removeCustomerDTO.getDriveId(), removeCustomerDTO.getCustomerId());

        try {
            driveFacade.removeCustomer(removeCustomerDTO);
            return driveFacade.findDriveById(removeCustomerDTO.getDriveId());
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/edit-capacity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO editCapacity(@RequestBody DriveDTO driveDTO) throws ResourceNotFoundException {
        logger.debug("rest editCapacity(driveId={}, capacity={})", driveDTO.getId(), driveDTO.getCapacity());

        try {
            driveFacade.changeCapacity(driveDTO.getId(), driveDTO.getCapacity());
            return driveFacade.findDriveById(driveDTO.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/edit-driver", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO editDriver(@RequestBody ChangeDriverDTO changeDriverDTO) throws ResourceNotFoundException {
        logger.debug("rest editDriver(driveId={}, driverId={})", changeDriverDTO.getDriveId(), changeDriverDTO.getDriver().getId());

        try {
            driveFacade.changeDriver(changeDriverDTO);
            return driveFacade.findDriveById(changeDriverDTO.getDriveId());
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

}
