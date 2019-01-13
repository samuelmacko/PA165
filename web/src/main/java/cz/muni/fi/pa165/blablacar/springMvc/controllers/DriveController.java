package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.DriveCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import cz.muni.fi.pa165.blablacar.api.dto.DriveFormDTO;
import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
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

    @ModelAttribute(name = "userSession")
    public UserSession addUserSession() {
        return userSession;
    }

    @ModelAttribute(name = "driveFacade")
    public DriveFacade addDriveFacade() {
        return driveFacade;
    }

    @ModelAttribute(name = "userFacade")
    public UserFacade addUserFacade() {
        return userFacade;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDrive(@ModelAttribute("driveFormDTO") DriveFormDTO drive,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        log.info("DriveForm = " + drive.toString());


        DriveCreateDTO driveCreateDTO = new DriveCreateDTO();
        driveCreateDTO.setDriver(userSession.getUser());
        driveCreateDTO.setCapacity(drive.getCapacity());
        driveCreateDTO.setPrice(drive.getPrice());
        driveCreateDTO.setDate(drive.getDate());
        driveCreateDTO.setFromCity(cityFacade.findCityById(drive.getFromCityId()));
        driveCreateDTO.setToCity(cityFacade.findCityById(drive.getToCityId()));

        log.info("DriveCreate = " + driveCreateDTO.toString());

//
//        log.debug("create(drive={})", drive);
//      
//        if (!isValidBinding(result, model)) {
//            return "drives/new";
//        }
        log.info(drive.toString());

        Long id = driveFacade.createDrive(driveCreateDTO);
//        Long id = driveFacade.createDrive(driveCreateDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Drive was successfully created.");

        //redirect to ride with this comment
//        return "redirect:/drives/list-driver";
        return "redirect:/drives/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewDrive(Model model) {
        log.debug("new()");
        DriveFormDTO driveFormDTO = new DriveFormDTO();
        driveFormDTO.setDate(new Date());
        model.addAttribute("driveFormDTO", driveFormDTO);
        return "drives/new";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findDrive() {
        return "drives/find";
    }


    @ModelAttribute("cities")
    public List<CityDTO> addCities() {
        List<CityDTO> cities = new ArrayList<>(cityFacade.findAllCities());
        return cities;
    }

    //
    @RequestMapping(value = "/view/{driveId}", method = RequestMethod.GET)
    public String showDrive(@PathVariable Long driveId, Model model) {
        DriveDTO driveDTO = driveFacade.findDriveById(driveId);
        model.addAttribute("driveDTO", driveDTO);
        return "drives/drive";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editDrive(@Valid @ModelAttribute("driveDTO") DriveDTO drive,
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

//        return "redirect:/drive/showDrive/" + drive.getId();
        return "redirect:/drives/list";
    }

    //    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editExistingDrive() {
        return "drives/edit";
    }

//    @ModelAttribute
//    public void editDriveForm(ModelMap model) {
//        DriveDTO drive = new DriveDTO();
////        DriveFormDTO newDrive = new DriveFormDTO();
////        newDrive.setDriver(userSession.getUser());
//        model.addAttribute("driveDTO", drive);
////        model.addAttribute("driveFormDTO", newDrive);
//    }

    @RequestMapping(value = "/delete")
    public String deleteDrive(@RequestParam(value = "driveId", required = true) Long driveId, Model model,
                              RedirectAttributes redirectAttributes, HttpServletRequest request,
                              HttpServletResponse response) {

        driveFacade.removeDrive(driveId);

//        redirectAttributes.addFlashAttribute("alert_success", "Drive has been removed.");
        return "redirect:/drives/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        DriveDTO driveDTO = driveFacade.findDriveById(id);
        driveFacade.removeDrive(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Drive \"" + driveDTO.getId() + "\" was deleted.");
//        return "redirect:" + uriBuilder.path("/product/list").toUriString();
        return "redirect:/drives/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAllDrives(Model model, HttpServletRequest request, HttpServletResponse response) {
        List<DriveDTO> drives = driveFacade.findAllDrives();
        model.addAttribute("drives", drives);
        return "drives/drives";
    }

//    @RequestMapping("")
//    public String redirectTo404Page(Model model, HttpServletRequest request, HttpServletResponse response) {
//        return "error404";
//    }
//
//    //    @RequestMapping(value = "/find", method = RequestMethod.POST)
////    public String findRides(@Valid @ModelAttribute("placeForm") PlaceForm placeForm,
////                                   Model model,
////                                   BindingResult result,
////                                   RedirectAttributes redirectAttributest) {
////        model.addAttribute("rides", placeFacade.getRidesWithOriginatingAndDestinationPlace(Long.valueOf(placeForm.getFrom()),Long.valueOf(placeForm.getTo())));
////        return "rides/search";
////    }
////    @RequestMapping(value = "/connection", method = RequestMethod.GET)
////    public String listSearchResult(@RequestParam(required=true) String placeFrom,
////                                   @RequestParam(required=true) String placeTo,
////                                   Model model) {
////
////        model.addAttribute("rides",placeFacade.getRidesWithOriginatingAndDestinationPlaceByName(placeFrom,placeTo));
////        return "rides/all"; //create new view with search on top
////    }
//
//

}
