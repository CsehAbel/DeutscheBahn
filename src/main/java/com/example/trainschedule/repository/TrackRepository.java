package com.example.trainschedule.repository;

import com.example.trainschedule.models.Station;
import com.example.trainschedule.models.Track;
import com.example.trainschedule.models.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, String> {

    //@Query("SELECT t FROM Track t WHERE t.station.shortcode = :station")
    //List<Track> findByStation(String station);
    //List<Track> findByStation(Station station);



}
