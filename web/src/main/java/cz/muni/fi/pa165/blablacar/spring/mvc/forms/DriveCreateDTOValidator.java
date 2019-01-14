/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.spring.mvc.forms;

import cz.muni.fi.pa165.blablacar.api.dto.DriveCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.DriveFormDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Matus Sakala
 */
public class DriveCreateDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return DriveCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DriveFormDTO driveFormDTO = (DriveFormDTO) o;
        if (driveFormDTO.getCapacity() == 0) return;
        if (driveFormDTO.getPrice().intValue() < 0) return;
    }
    
}
