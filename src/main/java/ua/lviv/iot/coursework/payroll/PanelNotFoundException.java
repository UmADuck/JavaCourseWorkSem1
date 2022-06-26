package ua.lviv.iot.coursework.payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PanelNotFoundException extends RuntimeException{
    public PanelNotFoundException(String message){
        super(message);
    }
}
