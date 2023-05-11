package com.example.trainschedule;

import com.example.trainschedule.models.*;
import com.example.trainschedule.repository.*;
import com.example.trainschedule.services.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class TrainscheduleApplication {


    @Autowired
    StationRepository stationRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    TrainRepository trainRepository;
    @Autowired
    TrackRepository trackRepository;
    @Autowired
    WaggonRepository waggonRepository;

    @Autowired
    public XmlReader xmlReader;


    public static void main(String[] args) {
        SpringApplication.run(TrainscheduleApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

        @GetMapping("/station/{shortcode}/train/{trainNumber}/waggon/{position}")
        @ResponseBody
        public Map.Entry<String, String[]> getSectionsForStationTrain(@PathVariable("shortcode") String shortcode, @PathVariable("trainNumber") String trainNumber, @PathVariable("position") String position) {
            Station station = stationRepository.findByShortcode(shortcode);
            List<Track> tracks = trackRepository.findByStation(station);
            Train train = null;
            for (Track track : tracks) {
                train = trainRepository.findByTrainNumberAndTrack(trainNumber, track.getId());
                if (train != null) {
                    break;
                }
            }
            Waggon waggon = waggonRepository.findByTrainAndPosition(train.getId(), position);
            List<Section> sections = waggon.getSections();
            //detach object references and deep copy sections to Map<String, String[]> sectionsMap {"sections": ["A", "B", "C"]}
            Map<String,String[]> sectionsMap = new HashMap<>();
            String[] sectionsArray = new String[sections.size()];
            for (int i = 0; i < sections.size(); i++) {
                sectionsArray[i] = sections.get(i).getIdentifier();
            }
            sectionsMap.put("sections", sectionsArray);
            //return an entry from sectionsMap
            Map.Entry<String, String[]> entry = sectionsMap.entrySet().iterator().next();
            return entry;
    }

}
