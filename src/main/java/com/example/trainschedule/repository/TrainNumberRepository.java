package com.example.trainschedule.repository;

import com.example.trainschedule.models.TrainNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TrainNumberRepository extends CrudRepository<TrainNumber, String> {
}
