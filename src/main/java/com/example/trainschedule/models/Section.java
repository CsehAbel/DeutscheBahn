package com.example.trainschedule.models;

//<sections>
//<identifier>A</identifier>
//<identifier>B</identifier>
//<identifier>C</identifier>
//<identifier>D</identifier>
//</sections>

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"identifier"}, name = "section")
public class Section {

        private String identifier;

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
}
