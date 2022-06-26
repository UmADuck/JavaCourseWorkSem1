package ua.lviv.iot.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.beans.Transient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SolarPanel{

    private int panelId;
    private PanelTypes panelType;
    private int panelPower;
    private int batteryCapacity;
    private String panelAddress;
    private int ownerId;

    @Transient
    public String getHeaders(){

        return "panelId" + ", " + "panelType" + ", " + "panelPower" + ", " +
                "batteryCapacity" + ", " + "panelAddress" + ", " + "ownerId";
    }


    public String toCSV(){

        return panelId + ", " + panelType + ", " + panelPower + ", " +
                batteryCapacity + ", " + panelAddress + ", " + ownerId;
    }
}
