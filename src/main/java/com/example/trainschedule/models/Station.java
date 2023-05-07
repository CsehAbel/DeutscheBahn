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

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "station")
public class Station {

    private String shortcode;
    private String name;
    private Validity validity;
    private List<Track> tracks;

    public Station() {
    }

    public Station(String shortcode, String name, Validity validity, List<Track> tracks) {
        this.shortcode = shortcode;
        this.name = name;
        this.validity = validity;
        this.tracks = tracks;
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
