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

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Entity
@Data
public class Subtrain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Train train;


    private String destination;

    private String sections;

    public Subtrain() {
    }
}
