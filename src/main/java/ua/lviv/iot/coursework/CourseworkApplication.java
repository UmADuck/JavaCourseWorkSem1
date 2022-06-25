package ua.lviv.iot.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = {"ua.lviv.iot.coursework.controllers",
		"ua.lviv.iot.coursework.logic", "ua.lviv.iot.coursework.csvmanagers"})
public class CourseworkApplication {

	public static void main(String[] args) {

		SpringApplication.run(CourseworkApplication.class, args);

	}
}
