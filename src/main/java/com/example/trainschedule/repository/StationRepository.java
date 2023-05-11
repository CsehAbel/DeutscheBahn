package com.example.trainschedule.repository;

import com.example.trainschedule.models.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<Station, Integer> {
}
