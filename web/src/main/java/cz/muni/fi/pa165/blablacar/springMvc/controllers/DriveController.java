package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/drives")
public class DriveController {

    private final static Logger log = LoggerFactory.getLogger(DriveController.class);

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
                              BindingResult bindingResult,
                              HttpServletResponse response) throws ParseException {
        
        if(drive.getFromCityId() == drive.getToCityId()){
            redirectAttributes.addFlashAttribute("alert_warning", "Start and destination must differ.");
            return "redirect:/drives/new";
        }
        
        String dateStr = request.getParameter("dateOld");
        Date formattedDate;
        if(dateStr == ""){
            redirectAttributes.addFlashAttribute("alert_warning", "Date must be set.");
            return "redirect:/drives/new";
        }else{
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = formater.parse(dateStr);
            log.info("Date = " + formattedDate.toString());
        }
        
        if(formattedDate.before(new Date())){
            redirectAttributes.addFlashAttribute("alert_warning", "Date must be in the future.");
            return "redirect:/drives/new";
        }
        
        log.info("DriveForm = " + drive.toString());
        
        if(drive.getPrice() == null){
            redirectAttributes.addFlashAttribute("alert_warning", "Price must be set.");
            return "redirect:/drives/new";
        }
        if(drive.getPrice().intValue() < 0){
            redirectAttributes.addFlashAttribute("alert_warning", "Price must be positive number.");
            return "redirect:/drives/new";
        }
        
        if(drive.getCapacity() <= 0){
            redirectAttributes.addFlashAttribute("alert_warning", "Capacity must be > 0.");
            return "redirect:/drives/new";
        }
        DriveCreateDTO driveCreateDTO = new DriveCreateDTO();
        driveCreateDTO.setDriver(userSession.getUser());
        driveCreateDTO.setCapacity(drive.getCapacity());
        driveCreateDTO.setPrice(drive.getPrice());
        
        if (formattedDate == null) {
            driveCreateDTO.setDate(new Date());
        } else {
            driveCreateDTO.setDate(formattedDate);
        }
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
        return "redirect:/user/show/"+userSession.getUserId().toString();
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
    public String findDrive(Model model) {
        log.debug("find()");
        DriveFormDTO driveFormDTO = new DriveFormDTO();
        driveFormDTO.setDate(new Date());
        model.addAttribute("driveFormDTO", driveFormDTO);
        return "drives/find";
    }
    
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findDrive(@ModelAttribute("driveFormDTO") DriveFormDTO drive,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response) throws ParseException {
        String dateStr = request.getParameter("dateOld");
        Date formattedDate;
        if(dateStr == ""){
            redirectAttributes.addFlashAttribute("alert_warning", "Date must be set.");
            return "redirect:/drives/find";
        }else{
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = formater.parse(dateStr);
            log.info("Date = " + formattedDate.toString());
        }
        
        if(formattedDate.before(new Date())){
            redirectAttributes.addFlashAttribute("alert_warning", "Date must be in the future.");
            return "redirect:/drives/find";
        }
        
        if(drive.getFromCityId() == drive.getToCityId()){
            redirectAttributes.addFlashAttribute("alert_warning", "Start and destination must differ.");
            return "redirect:/drives/find";
        }
        List<DriveDTO> res = new ArrayList();
        for(DriveDTO d : driveFacade.findDrivesByFromCityId(drive.getFromCityId())){
            if(d.getToCity().getId().equals(drive.getToCityId()) && d.getDate().after(new Date())){
                res.add(d);
            }
        }
        model.addAttribute("drives", res);
        return "drives/list";
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

  @RequestMapping(value = "/join/{id}")
  public String joinDrive(    @PathVariable Long id,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response){
        DriveDTO driveDTO = driveFacade.findDriveById(id);
        AddCustomerDTO newCustomer = new AddCustomerDTO();
        newCustomer.setCustomerId(userSession.getUserId());
        newCustomer.setDriveId(id);
        driveFacade.addCustomer(newCustomer);
        driveFacade.changeCapacity(driveDTO.getId(), driveDTO.getCapacity() - 1);
        return "redirect:/user/show/"+userSession.getUserId().toString();
    }
  
  @RequestMapping(value = "/leave/{id}")
  public String leaveDrive(    @PathVariable Long id,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response){
        DriveDTO driveDTO = driveFacade.findDriveById(id);
        if(driveDTO.getDate().before(new Date())){
            redirectAttributes.addFlashAttribute("alert_warning", "Drive already happened, no point in leaving");
            return "redirect:/user/show/"+userSession.getUserId().toString();
        }
        RemoveCustomerDTO newCustomer = new RemoveCustomerDTO();
        newCustomer.setCustomerId(userSession.getUserId());
        newCustomer.setDriveId(id);
        driveFacade.removeCustomer(newCustomer);
        return "redirect:/user/show/"+userSession.getUserId().toString();
    }
    
  @RequestMapping(value = "/find/all")
  public String findDrives(   Model model,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response){
        List<DriveDTO> res = new ArrayList();
        for(DriveDTO d : driveFacade.findAllDrives()){
            if(d.getDate().after(new Date())){
                res.add(d);
            }
        }
        model.addAttribute("drives", res);
        return "drives/list";
    }
    
  
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editDrive(@ModelAttribute("editDriveDTO") DriveDTO drive,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            HttpServletResponse response,
                            HttpServletRequest request) {

        log.debug("update(drive) " + drive);

        if(drive != null && drive.getId() != null){
            driveFacade.changeCapacity(drive.getId(), drive.getCapacity());
            if(drive.getDate() != null){
                driveFacade.changeDate(drive.getId(), drive.getDate());
            }
        }

        redirectAttributes.addFlashAttribute("alert_success", "Drive " + " has been updated.");

        return "redirect:/drives/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editExistingDrive(@PathVariable long id, Model model) {
        DriveDTO driveDTO = driveFacade.findDriveById(id);
        log.debug("editing drive " + driveDTO);

        model.addAttribute("editDriveDTO", driveDTO);
        return "drives/edit";
    }


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



}
