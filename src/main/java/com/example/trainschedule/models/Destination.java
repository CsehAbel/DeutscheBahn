package com.example.trainschedule.models;

 //                         <destination>
 //								<destinationName>Hamburg-Altona</destinationName>
 //								<destinationVia>
 //									<item>Hamburg Hbf</item>
 //									<item>Hamburg Dammtor</item>
 //								</destinationVia>
 //							</destination>


import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"destinationName", "destinationVia"}, name = "destination")
public class Destination {

    private String destinationName;
    private String destinationVia;

    public Destination() {
    }

    public Destination(String destinationName, String destinationVia) {
        this.destinationName = destinationName;
        this.destinationVia = destinationVia;
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
