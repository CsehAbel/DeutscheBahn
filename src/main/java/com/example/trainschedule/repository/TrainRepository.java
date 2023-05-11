package com.example.trainschedule.repository;

import com.example.trainschedule.models.Track;
import com.example.trainschedule.models.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepository extends CrudRepository<Train, Integer> {

    @Query("SELECT t FROM Train t WHERE t.trainNumber = :trainNumber and t.track.id = :trackid")
    Train findByTrainNumberAndTrack(@Param("trainNumber") String trainNumber, @Param("trackid") Integer trackid);
}
