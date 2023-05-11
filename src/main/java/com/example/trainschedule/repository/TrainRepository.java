package com.example.trainschedule.repository;

import com.example.trainschedule.models.Track;
import com.example.trainschedule.models.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainRepository extends CrudRepository<Train, String> {

    List<Train> findByTrack(Track track);
}
