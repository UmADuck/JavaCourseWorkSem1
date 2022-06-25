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
public class PanelOwner {

    private int ownerId;
    private String fullName;
    private String ownAddress;

    @Transient
    public String getHeaders(){

        return "ownerId" + ", " + "fullName" + ", " + "ownAddress:";
    }


    public String toCSV(){

        return ownerId + ", " + fullName + ", " + ownAddress;
    }
}
