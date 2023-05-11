package com.example.trainschedule.repository;

import com.example.trainschedule.models.Track;
import com.example.trainschedule.models.Train;
import com.example.trainschedule.models.TrainNumber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainRepository extends CrudRepository<Train, String> {

    //if trainNumber is a member of Train.trainNumbers and track equals Train.track
    @Query("SELECT t FROM Train t WHERE ?1 MEMBER OF t.trainNumbers AND t.track = ?2")
    Train findByTrainNumberAndTrack(TrainNumber trainNumber, Track track);
}
