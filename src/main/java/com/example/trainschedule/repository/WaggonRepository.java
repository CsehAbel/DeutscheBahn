package com.example.trainschedule.repository;

import com.example.trainschedule.models.Train;
import com.example.trainschedule.models.Waggon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WaggonRepository extends CrudRepository<Waggon, Integer> {

    @Query("SELECT w FROM Waggon w WHERE w.train.id = :trainId and w.position = :position")
    Waggon findByTrainAndPosition(Integer trainId, String position);
}
