package com.example.trainschedule.repository;

import com.example.trainschedule.models.Train;
import org.springframework.data.repository.CrudRepository;

public interface TrainRepository extends CrudRepository<Train, String> {
}
