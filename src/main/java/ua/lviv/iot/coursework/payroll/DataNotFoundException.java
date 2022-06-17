package ua.lviv.iot.coursework.payroll;

public class DataNotFoundException extends RuntimeException{

    DataNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
