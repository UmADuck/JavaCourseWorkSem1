package ua.lviv.iot.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Sensor{

    private int sensorId;
    private LocalDateTime timeOfLaunch;
    private int timeOfWork;
    private int energyGivenPerHour;
    private int energyGivenPerDay;
    private int levelOfBatteryCapacity;
    private int currentAngle;
    private int currentPriceForElectricity;
    private int priceOfSoldEnergyPerHour;

    public Sensor(int id, int currentAngle){
        this.sensorId = id;
        this.currentAngle = currentAngle;
    }
    @Transient
    public String getStaticHeaders(){

        return "sensorId:";
    }
    @Transient
    public String getNonStaticHeaders(){

        return "timeOfLaunch" + ", " + "timeOfWork" + ", " + "energyGivenPerHour" +
                ", " + "energyGivenPerDay" + ", " + "levelOfBatteryCapacity"
                + ", " + "currentAngle" + ", " + "priceOfSoldEnergyPerHour:";
    }


    public String staticToCSV(){

        return "" + sensorId;
    }

    public String nonStaticToCSV(){

        return  timeOfLaunch + ", " + timeOfWork + ", " + energyGivenPerHour +
                ", " + energyGivenPerDay + ", " + levelOfBatteryCapacity + ", " +
                currentAngle + ", " + priceOfSoldEnergyPerHour;
    }
}
