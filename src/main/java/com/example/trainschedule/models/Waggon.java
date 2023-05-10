package com.example.trainschedule.models;

//<waggon>
//<position>0</position>
//<isWaggon>0</isWaggon>
//<sections>
//<identifier>A</identifier>
//</sections>
//<number></number>
//<type>s</type>
//<symbols></symbols>
//<differentDestination></differentDestination>
//<length>2</length>
//</waggon>

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import javax.xml.bind.annotation.*;

@Entity
@Data
public class Waggon {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @ManyToOne
        private Train train;

        private int position;

        private int isWaggon;

        @ManyToMany
        @JoinTable(name = "waggon_section",
                joinColumns = @jakarta.persistence.JoinColumn(name = "waggon_id"),
                inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "section_id"))
        private List<Section> sections;

        private String number;
        private String type;
        private String symbols;
        private String differentDestination;
        private int length;

        public Waggon() {
        }

        public Waggon(int position, int isWaggon, java.util.List<Section> sections, String number, String type, String symbols, String differentDestination, int length) {
            this.position = position;
            this.isWaggon = isWaggon;
            this.sections = sections;
            this.number = number;
            this.type = type;
            this.symbols = symbols;
            this.differentDestination = differentDestination;
            this.length = length;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsWaggon() {
        return isWaggon;
    }

    public void setIsWaggon(int isWaggon) {
        this.isWaggon = isWaggon;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public String getDifferentDestination() {
        return differentDestination;
    }

    public void setDifferentDestination(String differentDestination) {
        this.differentDestination = differentDestination;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
