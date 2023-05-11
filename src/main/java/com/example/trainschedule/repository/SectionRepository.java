package com.example.trainschedule.repository;

import com.example.trainschedule.models.Section;
import com.example.trainschedule.models.Waggon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectionRepository extends CrudRepository<Section, String> {

    List<Section> findByWaggon(Waggon wagon);
}
