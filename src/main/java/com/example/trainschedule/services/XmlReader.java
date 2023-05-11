package com.example.trainschedule.services;

import com.example.trainschedule.models.*;
import com.example.trainschedule.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class XmlReader {
    //load a file from resources folder and unmarshal it to an object

    @PostConstruct
    public void init() throws IOException, ParserConfigurationException, SAXException {
        this.readXmls();
    }

    @Autowired
    private WaggonRepository waggonRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private StationRepository stationRepository;

    void persistHook(Object obj, String tagName, Object oneToMany){
        //check if obj is a List of objects or a single object
        //if it is a list, call persistCollection(obj, tagName,oneToMany)
        if(obj instanceof List){
            List<Object> list = (List<Object>) obj;
            persistCollection(list, tagName, oneToMany);
        }
//        //Waggon
//        if (tagName.equals("waggon")) {
//            Waggon waggon = (Waggon) obj;
//            //persist waggon
//            waggonRepository.save(waggon);
//        }
//        //Section
//        else if (tagName.equals("section")) {
//            Section section = (Section) obj;
//            //persist section
//            sectionRepository.save(section);
//        } else if(tagName.equals("train")){
//            Train train = (Train) obj;
//            //persist train
//            trainRepository.save(train);
//        } else if(tagName.equals("track")){
//            Track track = (Track) obj;
//            //persist track
//            trackRepository.save(track);
//        } else
        if(tagName.equals("station")){
            Station station = (Station) obj;
            //persist station
            stationRepository.save(station);
        }
//        else if(tagName.equals("trainNumber")){
//            TrainNumber trainNumber = (TrainNumber) obj;
//            //persist trainNumber
//            trainNumberRepository.save(trainNumber);
//        }
    }

    private void persistCollection(List<Object> list, String tagName, Object oneToMany) {
        //iterate through the list
        for (Object obj : list) {
            if(tagName.equals("waggons")){
                Waggon waggon = (Waggon) obj;
                waggon.setTrain((Train) oneToMany);
                //persist the Train later
            } else if(tagName.equals("sections")){
                Section section = (Section) obj;
                section.setWaggon((Waggon) oneToMany);
                //persist the Train later
            } else if(tagName.equals("tracks")){
                Track track = (Track) obj;
                track.setStation((Station) oneToMany);
                //persist the Station later
            } else if(tagName.equals("trains")){
                Train train = (Train) obj;
                train.setTrack ((Track) oneToMany);
            }

        }
    }


    void readXmls() throws IOException, ParserConfigurationException, SAXException {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:xml/*.xml");
        //for each resource expose an input stream
        for (Resource resource : resources) {
            InputStream inputStream = resource.getInputStream();
            //parse the input stream
            this.deserializeStation(inputStream);
        }

    }

    void deserializeStation(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);

        NodeList nodeList = document.getChildNodes();
        //iterate through the nodes
        List<Object> aList= new ArrayList<>();
        this.parseTrain(nodeList,"station",aList);
        //assert that aList is not null, contains an intanceOf Train and has a size of 1
        assert aList != null;
        assert aList.get(0) instanceof Station;
        assert aList.size() == 1;
    }

    void parseTrain(NodeList nodeList,String tagName,List<Object> objectsList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            //check if the node is an element
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //check if the element is a train
                if(element.getNodeName().equals("station")){
                    String shortcode = element.getElementsByTagName("shortcode").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    Object validity = this.createValidity(element.getElementsByTagName("validity").item(0).getChildNodes());
                    List<Object> tracks = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("tracks").item(0).getChildNodes(),"track",tracks);
                    Object obj=this.createStation(shortcode,name,validity,tracks);
                    objectsList.add(obj);
                    this.persistHook(tracks,"tracks",obj);
                    this.persistHook(obj,"station",null);
                }
                else if(element.getNodeName().equals("track")){
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    Integer number = Integer.parseInt(element.getElementsByTagName("number").item(0).getTextContent());
                    List<Object> trains = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("trains").item(0).getChildNodes(),"train",trains);
                    Object obj=this.createTrack(name,number,trains);
                    objectsList.add(obj);
                    this.persistHook(trains,"trains",obj);
                }
                else if (element.getNodeName().equals("train")) {
                    //create a train object
                    List<Object> trainNumbers = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("trainNumbers").item(0).getChildNodes(),"trainNumber",trainNumbers);

                    String anno = element.getElementsByTagName("anno").item(0).getTextContent();
                    String time = element.getElementsByTagName("time").item(0).getTextContent();
                    String additionalText = element.getElementsByTagName("additionalText").item(0).getTextContent();

                    List<Object> waggons = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("waggons").item(0).getChildNodes(),"waggon",waggons);
                    List<Object> trainTypes = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("traintypes"),"traintype",trainTypes);

                    if (((ArrayList) trainNumbers).size()!=0){
                        String trainNumber = (String) trainNumbers.get(0);
                        Object obj = this.createTrain(trainNumber,anno,time,additionalText,waggons,trainTypes);
                        objectsList.add(obj);
                        this.persistHook(waggons,"waggons",obj);
                    }

                }
                else if (element.getNodeName().equals("trainNumber")) {
                    String trainNumber=element.getTextContent();
                    objectsList.add(trainNumber);
                }
                else if(element.getNodeName().equals("waggon")){
                    //create a waggon object
                    Waggon waggon = new Waggon();
                    //set the waggon properties
                    Integer position=Integer.parseInt(element.getElementsByTagName("position").item(0).getTextContent());
                    Integer isWaggon=Integer.parseInt(element.getElementsByTagName("isWaggon").item(0).getTextContent());
                    List<Object> sections = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("sections").item(0).getChildNodes(),"section",sections);
                    String number=element.getElementsByTagName("number").item(0).getTextContent();
                    String type=element.getElementsByTagName("type").item(0).getTextContent();
                    String symbols=element.getElementsByTagName("symbols").item(0).getTextContent();
                    String differentDestination=element.getElementsByTagName("differentDestination").item(0).getTextContent();
                    Integer length=Integer.parseInt(element.getElementsByTagName("length").item(0).getTextContent());
                    Object obj=this.createWaggon(position,isWaggon,sections,number,type,symbols,differentDestination,length);
                    objectsList.add(obj);
                    this.persistHook(sections, "sections", obj);
                }
                else if(element.getNodeName().equals("identifier")){
                    String identifier=element.getTextContent();
                    Object obj=this.createSection(identifier);
                    objectsList.add(obj);
                }


            }
        }
    }

    private Object createTrack(String name, Integer number, List<Object> trains) {
        Track track = new Track();
        track.setName(name);
        track.setNumber(number);
        //cast the list of objects to a list of trains
        List<Train> trains1 = trains.stream().map(x -> (Train) x).collect(Collectors.toList());
        track.setTrains(trains1);
        return track;
    }

    private Object createStation(String shortcode, String name, Object validity, List<Object> tracks) {
        Station station = new Station();
        station.setShortcode(shortcode);
        station.setName(name);
        station.setValidity((Validity) validity);
        //cast the list of objects to a list of tracks
        List<Track> tracks1 = tracks.stream().map(x -> (Track) x).collect(Collectors.toList());
        station.setTracks(tracks1);
        return station;
    }

    private Object createValidity(NodeList validity) {
        Validity validity1 = new Validity();
        for (int i = 0; i < validity.getLength(); i++) {
            Node node = validity.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if(element.getNodeName().equals("from")){
                    String from = element.getTextContent();
                    validity1.setFrom(from);
                }
                else if(element.getNodeName().equals("to")){
                    String to = element.getTextContent();
                    validity1.setTo(to);
                }
            }
        }
        return validity1;
    }

    private Object createSection(String identifier) {
        Section section = new Section();
        section.setIdentifier(identifier);
        return section;
    }

    private Object createWaggon(Integer position, Integer isWaggon, List<Object> sections, String number, String type, String symbols, String differentDestination, Integer length) {
        Waggon waggon = new Waggon();
        waggon.setPosition(position);
        waggon.setIsWaggon(isWaggon);
        //Cast the list of objects to a list of Sections
        List<Section> sections1 = new ArrayList<>();
        for (Object obj:sections){
            sections1.add((Section) obj);
        }
        waggon.setSections(sections1);
        waggon.setNumber(number);
        waggon.setType(type);
        waggon.setSymbols(symbols);
        waggon.setDifferentDestination(differentDestination);
        waggon.setLength(length);
        return waggon;

    }


    //class Train{
    //    private String uuid;
    //    private List<TrainNumber> trainNumbers;
    //    private String anno;
    //    private String time;
    //    private String additionalText;
    //    private List<Subtrain> subtrains;
    //    private List<Waggon> waggons;
    //    private List<TrainType> traintypes;
    public Train createTrain(String trainNumber,String anno, String time, String additionalText, List<Object> waggons, List<Object> traintypes) {
        Train train = new Train();

        train.setTrainNumber(trainNumber);

        train.setAnno(anno);
        train.setTime(time);
        train.setAdditionalText(additionalText);

        //Cast the list of objects to a list of Waggons
        List<Waggon> waggons1 = new ArrayList<>();
        for (Object obj: waggons) {
            waggons1.add((Waggon) obj);
        }
        train.setWaggons(waggons1);
        //Cast the list of objects to a list of TrainTypes
        List<TrainType> traintypes1 = new ArrayList<>();
        for (Object obj: traintypes) {
            traintypes1.add((TrainType) obj);
        }
        train.setTraintypes(traintypes1);
        return train;

    }

}
