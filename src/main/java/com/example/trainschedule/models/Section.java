package com.example.trainschedule.models;

//<sections>
//<identifier>A</identifier>
//<identifier>B</identifier>
//<identifier>C</identifier>
//<identifier>D</identifier>
//</sections>

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Section {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        private String identifier;

        @ManyToOne
        private Waggon waggon;

        public Section() {
        }
        public Section(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public Waggon getWaggon() {
            return waggon;
        }

        public void setWaggon(Waggon waggon) {
            this.waggon = waggon;
        }
}
