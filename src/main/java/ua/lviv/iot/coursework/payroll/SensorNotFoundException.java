package ua.lviv.iot.coursework.payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String message){
        super(message);
    }
}
