package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
import cz.muni.fi.pa165.blablacar.api.facade.CityFacade;
import cz.muni.fi.pa165.blablacar.api.facade.DriveFacade;
import cz.muni.fi.pa165.blablacar.springMvc.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cities")
public class CityController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CityFacade cityFacade;

    @Autowired
    private DriveFacade driveFacade;

    @Autowired
    private UserSession userSession;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("cities", cityFacade.findAllCities());
        return "city/cities";
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public String showCity(@PathVariable Long cityId, Model model) {
        CityDTO cityDTO = cityFacade.findCityById(cityId);
        model.addAttribute("cityDTO", cityDTO);
        model.addAttribute("fromDrives", driveFacade.findDrivesByFromCityId(cityId));
        model.addAttribute("toDrives", driveFacade.findDrivesByToCityId(cityId));
        return "city/city";
    }

    @ModelAttribute(name = "userSession")
    public UserSession addUserSession() {
        return userSession;
    }
}