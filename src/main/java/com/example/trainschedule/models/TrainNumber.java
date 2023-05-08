package com.example.trainschedule.models;

//<trainNumbers>
//<trainNumber>1098</trainNumber>
//</trainNumbers>

import javax.xml.bind.annotation.XmlType;

@XmlType( name = "trainNumbers")
public class TrainNumber {

    private String trainNumber;

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
