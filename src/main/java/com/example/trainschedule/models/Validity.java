package com.example.trainschedule.models;

//<validity>
//		<from>2016-06-12</from>
//		<to>2016-12-10</to>
//</validity>

import javax.xml.bind.annotation.XmlType;

@XmlType( name = "validity")
public class Validity {

        private String from;
        private String to;

        public Validity() {
        }

        public Validity(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
}
