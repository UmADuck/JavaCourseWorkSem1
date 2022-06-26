package ua.lviv.iot.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Sensor {

    private int sensorId;
    private LocalDate timeOfLaunch;
    private int daysOfWork;
    private int energyGivenPerHour;
    private int energyGivenPerDay;
    private int levelOfBatteryCapacity;
    private int currentAngle;
    private int currentPriceForElectricity;
    private int priceOfSoldEnergyPerHour;
    private int panelId;

    public Sensor(int sensorId, LocalDate timeOfLaunch, int daysOfWork, int energyGivenPerHour,
                  int energyGivenPerDay, int levelOfBatteryCapacity, int currentAngle,
                  int currentPriceForElectricity, int panelId) {
        this.sensorId = sensorId;
        this.timeOfLaunch = timeOfLaunch;
        this.daysOfWork = daysOfWork;
        this.energyGivenPerHour = energyGivenPerHour;
        this.energyGivenPerDay = energyGivenPerDay;
        this.levelOfBatteryCapacity = levelOfBatteryCapacity;
        this.currentAngle = currentAngle;
        this.currentPriceForElectricity = currentPriceForElectricity;
        this.priceOfSoldEnergyPerHour = this.energyGivenPerHour * this.currentPriceForElectricity;
        this.panelId = panelId;

    }

    @Transient
    public String getHeaders() {
        return "sensorId:" + ", " + "timeOfLaunch" + ", " + "daysOfWork" + ", " + "energyGivenPerHour" +
                ", " + "energyGivenPerDay" + ", " + "levelOfBatteryCapacity"
                + ", " + "currentAngle" + ", " + "currentPriceForElectricity" + ", "
                + "priceOfSoldEnergyPerHour" + ", " + "panelId";
    }

    @Transient
    public String toCSV() {
        return sensorId + ", " + timeOfLaunch + ", " + daysOfWork + ", " + energyGivenPerHour +
                ", " + energyGivenPerDay + ", " + levelOfBatteryCapacity + ", " +
                currentAngle + ", " + currentPriceForElectricity + ", "
                + priceOfSoldEnergyPerHour + ", " + panelId;
    }
}
