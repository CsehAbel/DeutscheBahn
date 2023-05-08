package com.example.trainschedule.models;

//<subtrains>
//<subtrain>
//<destination>
//<destinationName>Hamburg-Altona</destinationName>
//<destinationVia>
//<item>Hamburg Hbf</item>
//<item>Hamburg Dammtor</item>
//</destination>
//<sections>
//<identifier>A</identifier>
//<identifier>B</identifier>
//<identifier>C</identifier>
//<identifier>D</identifier>
//</sections>
//</subtrain>
//</subtrains>

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "subtrain")
public class Subtrain {

    @XmlElement(name = "destination")
    private Destination destination;

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "section")
    private List<Section> sections;

    private String uuid;

    public Subtrain() {
    }

    public Subtrain(Destination destination, List<Section> sections) {
        this.destination = destination;
        this.sections = sections;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
