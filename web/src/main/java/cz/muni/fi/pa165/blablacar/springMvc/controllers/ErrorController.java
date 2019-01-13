package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest) {
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 404: {
                return "error404";
            }
            case 500: {
                return "error";
            }
        }
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
