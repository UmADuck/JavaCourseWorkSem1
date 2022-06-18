package ua.lviv.iot.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.csvmanagers.PanelOwnerCSVManager;
import ua.lviv.iot.coursework.csvmanagers.SensorCSVManager;
import ua.lviv.iot.coursework.models.templates.PanelOwnerTemplates;
import ua.lviv.iot.coursework.models.templates.SensorTemplates;
import ua.lviv.iot.coursework.models.templates.SolarPanelTemplates;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = {"ua.lviv.iot.coursework.controllers",
		"ua.lviv.iot.coursework.logic"})
public class CourseworkApplication {

	public static void main(String[] args) {

		SpringApplication.run(CourseworkApplication.class, args);

		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		Calendar todayHour = Calendar.getInstance();
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		SensorCSVManager sensorCSVManager= new SensorCSVManager();
		SensorTemplates sensorTemplates = new SensorTemplates();

		PanelOwnerCSVManager panelOwnerCSVManager = new PanelOwnerCSVManager();
		PanelOwnerTemplates panelOwnerTemplates = new PanelOwnerTemplates();

		PanelCSVManager panelCSVManager = new PanelCSVManager();
		SolarPanelTemplates panelTemplates = new SolarPanelTemplates();

		sensorCSVManager.addStartingValuesToHash(sensorTemplates.getIdList());
		panelOwnerCSVManager.addStartingValuesToHash(panelOwnerTemplates.getIdList());
		panelCSVManager.addStartingValuesToHash(panelTemplates.getIdList());

		TimerTask creatingCSVEachDay = new TimerTask() {
			@Override
			public void run() {
				try {sensorCSVManager.creatingCSVEachDay();
					sensorCSVManager.creatingOnlyObjectDataCSV();

					panelOwnerCSVManager.creatingCSVEachDay();
					panelOwnerCSVManager.creatingOnlyObjectDataCSV();

					panelCSVManager.creatingCSVEachDay();
					panelCSVManager.creatingOnlyObjectDataCSV();

				}
				catch (IOException e) {e.printStackTrace();}
			}
		};

		TimerTask creatingCSVEachHour = new TimerTask() {
			@Override
			public void run() {
				try {sensorCSVManager.updateCSVEachHour();}
				catch (IOException e) {e.printStackTrace();}
			}
		};

		Timer oneDayTimer = new Timer();

		oneDayTimer.schedule(creatingCSVEachDay,today.getTime(),
				TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

		Timer oneHourTimer = new Timer();

		oneHourTimer.schedule(creatingCSVEachHour,today.getTime(),
				TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS));

	}

}
