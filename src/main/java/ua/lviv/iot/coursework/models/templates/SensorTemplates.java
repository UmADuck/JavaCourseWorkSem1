package ua.lviv.iot.coursework.models.templates;

import ua.lviv.iot.coursework.models.Sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SensorTemplates {

    SolarPanelTemplates panelTemplate = new SolarPanelTemplates();

    Sensor sensor1 = new Sensor(1, 45);
    Sensor sensor2 = new Sensor(2, 40);
    Sensor sensor3 = new Sensor(3, 50);

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate sensor1Start = LocalDate.parse("2022/06/10", formatter);
    LocalDate sensor2Start = LocalDate.parse("2022/06/10", formatter);
    LocalDate sensor3Start = LocalDate.parse("2022/06/10", formatter);

    final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("hh:mm");
    LocalTime sensor1StartTime = LocalTime.of(8, 0);
    LocalTime sensor2StartTime = LocalTime.of(6, 0);
    LocalTime sensor3StartTime = LocalTime.of(7, 0);

    //estimate time of sunset is 21:00
    LocalTime  sunsetTime = LocalTime.of(22, 0);

    LocalDateTime now = LocalDateTime.now();

    int difference1;
    int difference2;
    int difference3;

    int calculation1;
    int calculation2;
    int calculation3;

    int currentHour = now.getHour();
    int priceForElectricity;

    public LinkedList<Sensor> getTemplateList(){

        //randomizing price for electricity
        if (currentHour>0 && currentHour<7){
            priceForElectricity = (int) (Math.random()*100);
            if(priceForElectricity<10){
                priceForElectricity = (int) (Math.random()*100);
            }
        }
        else if(currentHour>6 && currentHour<13){
            priceForElectricity = (int) (Math.random()*100);
            if(priceForElectricity<10){
                priceForElectricity = (int) (Math.random()*100);
            }
        }
        else if(currentHour>12 && currentHour<19) {
            priceForElectricity = (int) (Math.random() * 100);
            if (priceForElectricity < 10) {
                priceForElectricity = (int) (Math.random() * 100);
            }
        }
        else {
            priceForElectricity = (int) (Math.random() * 100);
            if (priceForElectricity < 10) {
                priceForElectricity = (int) (Math.random() * 100);
            }
        }

        var tempList = new LinkedList<Sensor>();
        difference1 = (int) ChronoUnit.HOURS.between(sensor1StartTime, LocalDateTime.now().toLocalTime());
        difference2 = (int) ChronoUnit.HOURS.between(sensor2StartTime, LocalDateTime.now().toLocalTime());
        difference3 = (int) ChronoUnit.HOURS.between(sensor3StartTime, LocalDateTime.now().toLocalTime());

        sensor1.setTimeOfWork(Math.max(difference1, 0));
        sensor2.setTimeOfWork(Math.max(difference2, 0));
        sensor3.setTimeOfWork(Math.max(difference3, 0));

        calculation1 = (int) ChronoUnit.HOURS.between(sunsetTime, sensor1StartTime);
        calculation2 = (int) ChronoUnit.HOURS.between(sunsetTime, sensor2StartTime);
        calculation3 = (int) ChronoUnit.HOURS.between(sunsetTime, sensor3StartTime);

        var calculationList = new ArrayList<Integer>();
        calculationList.add(calculation1);
        calculationList.add(calculation2);
        calculationList.add(calculation3);

        sensor1.setTimeOfLaunch(sensor1Start);
        sensor2.setTimeOfLaunch(sensor2Start);
        sensor3.setTimeOfLaunch(sensor3Start);
        tempList.add(sensor1);
        tempList.add(sensor2);
        tempList.add(sensor3);
        var tempPanelList = panelTemplate.getTemplateList();
        for(int i = 0; i < tempList.size(); i++) {
            for (ua.lviv.iot.coursework.models.SolarPanel solarPanel : tempPanelList) {
                if (tempList.get(i).getSensorId() == solarPanel.getPanelId()) {
                    //counting energy given per day depending on what time is it right now
                    if (calculationList.get(i) <0){}
                    else {
                        tempList.get(i).setEnergyGivenPerDay(calculationList.get(i) *
                                solarPanel.getPanelPower());
                    }
                    //energy given per hour = solar panel power
                    tempList.get(i).setEnergyGivenPerHour(solarPanel.getPanelPower());
                    //counting current level of battery of panel
                    tempList.get(i).setLevelOfBatteryCapacity((solarPanel.getBatteryCapacity()
                            / calculationList.get(i)) * tempList.get(i).getTimeOfWork());
                    //counting current angle of the solar panel(let it change 5 up per hour)
                    tempList.get(i).setCurrentAngle(tempList.get(i).getCurrentAngle() +
                            5 * (tempList.get(i).getTimeOfWork()));
                    //i don't know how actually to get energy price so let it be random number(sorry for that)
                    //getting it by multiplying energy per hour with current hour price for 1 piece of electricity
                    tempList.get(i).setCurrentPriceForElectricity(priceForElectricity);
                    tempList.get(i).setPriceOfSoldEnergyPerHour(solarPanel.getPanelPower() *
                            tempList.get(i).getCurrentPriceForElectricity());
                }
            }
        }
        tempList.clear();
        tempList.add(sensor1);
        tempList.add(sensor2);
        tempList.add(sensor3);
        return tempList;
    }

    public List<Integer> getIdList(){
        var tempList = new ArrayList<Integer>();
        tempList.add(sensor1.getSensorId());
        tempList.add(sensor2.getSensorId());
        tempList.add(sensor3.getSensorId());
        return tempList;
    }

}
