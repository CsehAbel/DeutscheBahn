package com.example.trainschedule.repository;

import com.example.trainschedule.models.Train;
import com.example.trainschedule.models.Waggon;
import org.springframework.data.repository.CrudRepository;

public interface WaggonRepository extends CrudRepository<Waggon, String> {

    Waggon findByTrainAndNumber(Train train, String number);
}
