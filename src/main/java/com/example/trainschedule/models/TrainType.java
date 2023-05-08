package com.example.trainschedule.models;

//                          <traintypes>
//                            <traintype>IC</traintype>
//                        </traintypes>

import javax.xml.bind.annotation.XmlType;

@XmlType( name = "traintypes")
public class TrainType {

    private String trainType;

    public TrainType() {
    }

    public TrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }
}
