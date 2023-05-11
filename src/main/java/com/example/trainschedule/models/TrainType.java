package com.example.trainschedule.models;

//                          <traintypes>
//                            <traintype>IC</traintype>
//                        </traintypes>

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TrainType {

    @Id
    private String trainType;

    @ManyToOne
    private Train train;

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

    public Train getTrain() {
        return train;
    }

}
