package com.example.trainschedule.repository;

import com.example.trainschedule.models.Track;
import com.example.trainschedule.models.Train;
import com.example.trainschedule.models.TrainNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainRepository extends CrudRepository<Train, String> {

    Train findByTrainNumberAndTrack(TrainNumber trainNumber, Track track);
}
