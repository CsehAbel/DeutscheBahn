package com.example.trainschedule.models;

//<?xml version="1.0" encoding="UTF-8"?>
//<station>
//<shortcode>KK</shortcode>
//<name>Köln Hbf</name>
//<validity>
//<from>2016-06-12</from>
//<to>2016-12-10</to>
//</validity>
//<tracks>...</tracks>
//</station>

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String shortcode;
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="from",column=@Column(name="fromDate")),
            @AttributeOverride(name="to",column=@Column(name="toDate"))
            })
    private Validity validity;

    @OneToMany(mappedBy = "station")
    @Fetch(FetchMode.SUBSELECT)
    private List<Track> tracks;

    public Station() {
    }

    public Station(String shortcode, String name, Validity validity, List<Track> tracks) {
        this.shortcode = shortcode;
        this.name = name;
        this.validity = validity;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Validity getValidity() {
        return validity;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
