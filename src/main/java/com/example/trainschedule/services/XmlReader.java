package com.example.trainschedule.services;

import com.example.trainschedule.models.Station;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


@Service
public class XmlReader {
    //load a file from resources folder and unmarshal it to an object

    @PostConstruct
    public void init() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Station trainSchedule = (Station) jaxbUnmarshaller.unmarshal(new File("src/main/resources/trainSchedule.xml"));
            System.out.println(trainSchedule);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
