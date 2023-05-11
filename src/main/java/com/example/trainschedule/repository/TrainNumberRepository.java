package com.example.trainschedule.repository;

import com.example.trainschedule.models.Train;
import com.example.trainschedule.models.TrainNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TrainNumberRepository extends CrudRepository<TrainNumber, Integer> {
        //findByTrainNumber
        TrainNumber findByTrainNumber(String trainNumber);

}
