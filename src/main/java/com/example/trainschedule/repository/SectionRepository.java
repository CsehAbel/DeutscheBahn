package com.example.trainschedule.repository;

import com.example.trainschedule.models.Section;
import com.example.trainschedule.models.Waggon;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends CrudRepository<Section, String> {


    @Query("SELECT s FROM Section s WHERE s.waggon.id = :waggon")
    List<Section> findSectionsByWaggon(@Param("waggon") Integer waggonId);
}
