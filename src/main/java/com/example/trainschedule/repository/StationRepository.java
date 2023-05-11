package com.example.trainschedule.repository;

import com.example.trainschedule.models.Section;
import com.example.trainschedule.models.Station;
import com.example.trainschedule.models.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StationRepository extends CrudRepository<Station, Integer> {

        //@Query("SELECT s FROM Station s WHERE s.shortcode = :shortcode")
        //Station findByShortcode(@Param("shortcode") String shortcode);
        Station findByShortcode(String shortcode);


}
