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

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Entity
public class Track {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        private String name;
        private int number;
        @ManyToOne
        private Station station;

        @OneToMany(mappedBy = "track")
        @Fetch(FetchMode.SUBSELECT)
        @Cascade(CascadeType.ALL)
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

        public Station getStation() {
            return station;
        }

        public void setStation(Station station) {
            this.station = station;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

}
