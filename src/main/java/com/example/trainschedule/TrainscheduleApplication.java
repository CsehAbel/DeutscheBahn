package com.example.trainschedule;

import com.example.trainschedule.models.*;
import com.example.trainschedule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class TrainscheduleApplication {


    @Autowired
    StationRepository stationRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    TrainNumberRepository trainNumberRepository;
    @Autowired
    TrainRepository trainRepository;
    @Autowired
    TrackRepository trackRepository;
    @Autowired
    WaggonRepository waggonRepository;


    public static void main(String[] args) {
        SpringApplication.run(TrainscheduleApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/station/{shortcode}/train/{trainNumber}/waggon/{waggonNumber}")
        public List<Section> getSectionsForStationTrain(@PathVariable("shortcode") String shortcode, @PathVariable("trainNumber") String trainNumber, @PathVariable("waggonNumber") String waggonNumber) {
            Station station = stationRepository.findByShortcode(shortcode);
            List<Track> tracks = trackRepository.findByStation(station);
            TrainNumber trainNumberObject = trainNumberRepository.findByTrainNumber(trainNumber);
            Train train=null;
            for (Track track : tracks) {
                Train trainSearched = trainRepository.findByTrainNumberAndTrack(trainNumberObject, track);
                if (trainSearched != null) {
                    train = trainSearched;
                }
            }
            Waggon waggon = waggonRepository.findByTrainAndNumber(train, waggonNumber);
            return sectionRepository.findByWaggon(waggon);
    }

}
