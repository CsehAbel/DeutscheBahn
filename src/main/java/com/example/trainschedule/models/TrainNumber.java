package com.example.trainschedule.models;

//<trainNumbers>
//<trainNumber>1098</trainNumber>
//</trainNumbers>

import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Entity
public class TrainNumber {

    @Id
    private String trainNumber;

    @ManyToMany
    @JoinTable(name = "train_trainnumber",
            joinColumns = @jakarta.persistence.JoinColumn(name = "trainnumber_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "train_id"))
    private List<Train> train;

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
}
