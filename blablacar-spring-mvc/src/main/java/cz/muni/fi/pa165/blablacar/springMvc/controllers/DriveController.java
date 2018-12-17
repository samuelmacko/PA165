package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.AddCustomerDTO;
import cz.muni.fi.pa165.blablacar.api.dto.DriveCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import cz.muni.fi.pa165.blablacar.api.dto.RemoveCustomerDTO;
import cz.muni.fi.pa165.blablacar.api.facade.CityFacade;
import cz.muni.fi.pa165.blablacar.api.facade.DriveFacade;
import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import cz.muni.fi.pa165.blablacar.springMvc.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/drives")
public class DriveController {

    final static Logger log = LoggerFactory.getLogger(DriveController.class);

    @Autowired
    private DriveFacade driveFacade;
    @Autowired
    private CityFacade cityFacade;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private UserSession userSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDrive(@Valid @ModelAttribute("driveCreateDTO") DriveCreateDTO drive,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        log.debug("create(drive={})", drive);

        if (drive.getFromCity().getId().equals(drive.getToCity().getId())){
            model.addAttribute("alert_danger", "From and To cities must be different.");
            return "drives/new";
        }
//        if (!isValidBinding(result, model)) {
//            return "drives/new";
//        }

        Long id = driveFacade.createDrive(drive);
        redirectAttributes.addFlashAttribute("alert_success", "Drive was successfully created.");
        //redirect to ride with this comment
        return "redirect:/drive/list";
    }

//    private boolean isValidBinding(BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            for (ObjectError ge : result.getGlobalErrors()) {
//                log.trace("ObjectError: {}", ge);
//            }
//            for (FieldError fe : result.getFieldErrors()) {
//                model.addAttribute(fe.getField() + "_error", true);
//                log.trace("FieldError: {}", fe);
//            }
//            return false;
//        }
//        return true;
//    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewDrive(){
        return "drives/new";
    }

//    @ModelAttribute
//    public void addRideForm(ModelMap model) {
//        RideCreateDTO newRide = new RideCreateDTO();
//        List<PlaceDTO> places = new ArrayList<>(placeFacade.getAllPlaces());
//        newRide.setDriverId(userSession.getUserId());
//        model.addAttribute("rideCreateDTO", newRide);
//        model.addAttribute("places" , places);
//    }

    @RequestMapping(value = "/showDrive/{driveId}", method = RequestMethod.GET)
    public String showDrive(@PathVariable Long driveId, Model model) {
        DriveDTO driveDTO = driveFacade.findDriveById(driveId);
        model.addAttribute("driveDTO", driveDTO);
        return "drives/drive";
    }

//    @RequestMapping(value = "/showRide", method = RequestMethod.GET)
//    public String showRide2(@RequestParam(name = "rideId") Long rideId, Model model) {
//        RideDTO rideDTO = rideFacade.getRideWithId(rideId);
//        model.addAttribute("rideDTO", rideDTO);
//        return "rides/ride";
//    }

    @RequestMapping(value = "/updateDrive", method = RequestMethod.POST)
    public String showDrive(@Valid @ModelAttribute("driveDTO") DriveDTO drive,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes,
                           HttpServletResponse response,
                           HttpServletRequest request) {

        log.debug("update(drive={})", drive);

//        if ( isValidBinding(result, model)) {
//            model.addAttribute("rideCreateDTO", ride);
//            String referer = request.getHeader("Referer");
//            return "redirect:" + referer;
//        }

//        driveFacade.changePrice(ride.getId(), ride.getSeatPrice());
        driveFacade.changeCapacity(drive.getId(), drive.getCapacity());
        driveFacade.changeDate(drive.getId(), drive.getDate());

        redirectAttributes.addFlashAttribute("alert_success", "Drive " + userSession.getUserId() + " has been updated.");

        return "redirect:/drive/showDrive/" + drive.getId();
    }

    @RequestMapping(value = "/addCustomer")
    public String addCustomerToDrive(@RequestParam(name = "driveId") Long driveId,
                                     Model model,
                                     RedirectAttributes redirectAttributes,
                                     HttpServletResponse response,
                                     HttpServletRequest request) {

        AddCustomerDTO addCustomerDTO = new AddCustomerDTO();
        addCustomerDTO.setCustomerId(userSession.getUserId());
        addCustomerDTO.setDriveId(driveId);
        driveFacade.addCustomer(addCustomerDTO);

        model.addAttribute("drives", driveFacade.findAllDrives());

        redirectAttributes.addFlashAttribute("alert_success", "Customer has been added to the drive.");
        return "redirect:/drives/list-customers";
    }

    @RequestMapping(value = "/removeCustomer")
    public String removeCustomerFromDrive(@RequestParam(name = "driveId") Long driveId,
                                        Model model,
                                        RedirectAttributes redirectAttributes,
                                        HttpServletResponse response,
                                        HttpServletRequest request) {

        RemoveCustomerDTO removeCustomerDTO = new RemoveCustomerDTO();

        removeCustomerDTO.setCustomerId(userSession.getUserId());
        removeCustomerDTO.setDriveId(driveId);
        driveFacade.removeCustomer(removeCustomerDTO);

        redirectAttributes.addFlashAttribute("alert_success", "Customer has been removes from the drive.");
        return "redirect:/drive/list";
    }

    @RequestMapping(value = "/delete")
    public String deleteDrive(@RequestParam(value = "driveId", required = true) Long driveId, Model model,
                             RedirectAttributes redirectAttributes, HttpServletRequest request,
                             HttpServletResponse response) {

        driveFacade.removeDrive(driveId);

        redirectAttributes.addFlashAttribute("alert_success", "Drive has been removed.");
        return "redirect:/drive/list";
    }

//    @RequestMapping(value = "/list-customers", method = RequestMethod.GET)
//    public String listAllUserRidesAsPassenger(Model model, HttpServletRequest request, HttpServletResponse response) {
//        List<RideDTO> rides = new ArrayList<>(userFacade.getUserRidesAsPassenger(Long.valueOf(userSession.getUserId())));
//        model.addAttribute("rides", rides);
//        return "rides/list-pass";
//    }

//    @RequestMapping(value = "/list-driver", method = RequestMethod.GET)
//    public String listAllUserRidesAsDriver(Model model, HttpServletRequest request, HttpServletResponse response) {
//        List<RideDTO> rides = new ArrayList<>(userFacade.getUserRidesAsDriver(Long.valueOf(userSession.getUserId())));
//        model.addAttribute("rides", rides);
//        return "rides/list-driver";
//    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAllDrives(Model model, HttpServletRequest request, HttpServletResponse response) {
        List<DriveDTO> drives = driveFacade.findAllDrives();
        model.addAttribute("drives", drives);
        return "drives/all";
    }

//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String listSearchResult(Model model, HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("placeForm", new PlaceForm());
//        model.addAttribute("places", placeFacade.getAllPlaces());
//        return "rides/search"; //create new view with search on top
//    }

    @RequestMapping("")
    public String redirectTo404Page(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "error404";
    }

    //    @RequestMapping(value = "/find", method = RequestMethod.POST)
//    public String findRides(@Valid @ModelAttribute("placeForm") PlaceForm placeForm,
//                                   Model model,
//                                   BindingResult result,
//                                   RedirectAttributes redirectAttributest) {
//        model.addAttribute("rides", placeFacade.getRidesWithOriginatingAndDestinationPlace(Long.valueOf(placeForm.getFrom()),Long.valueOf(placeForm.getTo())));
//        return "rides/search";
//    }
//    @RequestMapping(value = "/connection", method = RequestMethod.GET)
//    public String listSearchResult(@RequestParam(required=true) String placeFrom,
//                                   @RequestParam(required=true) String placeTo,
//                                   Model model) {
//
//        model.addAttribute("rides",placeFacade.getRidesWithOriginatingAndDestinationPlaceByName(placeFrom,placeTo));
//        return "rides/all"; //create new view with search on top
//    }

//    @RequestMapping(value = "/find", method = RequestMethod.POST)
//    public String findRides(@Valid @ModelAttribute("placeForm") PlaceForm placeForm,
//                            BindingResult result,
//                            RedirectAttributes redirectAttributest) {
//
//        return "redirect:/ride/connection?placeFrom=" + placeForm.getFrom() + "&placeTo=" + placeForm.getTo();
//    }


    @ModelAttribute(name = "userSession")
    public UserSession addUserSession(){
        return userSession;
    }
}
