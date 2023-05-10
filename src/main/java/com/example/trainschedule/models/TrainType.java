package com.example.trainschedule.models;

//                          <traintypes>
//                            <traintype>IC</traintype>
//                        </traintypes>

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TrainType {

    @Id
    private String trainType;

    @ManyToMany
    private List<Train> train;

    public TrainType() {
    }

}
