package cz.muni.fi.pa165.blablacar.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The resource already exists")
public class ResourceAlreadyExistException extends RuntimeException {

}
