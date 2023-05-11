package com.example.trainschedule.models;

//<trainNumbers>
//<trainNumber>1098</trainNumber>
//</trainNumbers>

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Entity
public class TrainNumber {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String trainNumber;

    @ManyToOne
    private Train train;

    public TrainNumber() {
    }

    public TrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

}
