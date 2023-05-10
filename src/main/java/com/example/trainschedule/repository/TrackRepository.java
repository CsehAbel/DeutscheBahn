package com.example.trainschedule.repository;

import com.example.trainschedule.models.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<Track, String> {
}
