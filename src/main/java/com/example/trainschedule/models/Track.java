package com.example.trainschedule.models;

//<tracks>
//		<track>
//			<name>Gleis 1</name>
//			<number>1</number>
//			<trains>
//				<train>
//				<train>
//			</trains>
//		</track>
//</tracks>

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType( name = "track")
public class Track {

        private String name;
        private int number;

        @XmlElementWrapper(name = "trains")
        private List<Train> trains;

        public Track() {
        }

        public Track(String name, int number, List<Train> trains) {
            this.name = name;
            this.number = number;
            this.trains = trains;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<Train> getTrains() {
            return trains;
        }

        public void setTrains(List<Train> trains) {
            this.trains = trains;
        }

}