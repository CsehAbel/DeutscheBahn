package com.example.trainschedule.models;

 //                         <destination>
 //								<destinationName>Hamburg-Altona</destinationName>
 //								<destinationVia>
 //									<item>Hamburg Hbf</item>
 //									<item>Hamburg Dammtor</item>
 //								</destinationVia>
 //							</destination>


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Data
public class Destination {

    @Id
    private String uuid;

    @XmlElement(name = "destinationName")
    private String destinationName;

    @XmlElement(name = "destinationVia")
    private String destinationVia;

    public Destination() {
    }

    public Destination(String destinationName, String destinationVia) {
        this.destinationName = destinationName;
        this.destinationVia = destinationVia;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getDestinationVia() {
        return destinationVia;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setDestinationVia(String destinationVia) {
        this.destinationVia = destinationVia;
    }
}
