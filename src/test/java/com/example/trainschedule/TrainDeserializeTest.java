package com.example.trainschedule;

import com.example.trainschedule.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TrainDeserializeTest {

    @Test
    void deserializeTrain() throws ParserConfigurationException, IOException, SAXException {

        //access a file under test resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("train.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);

        NodeList nodeList = document.getChildNodes();
        //iterate through the nodes
        List<Object> aList= new ArrayList<>();
        this.parseTrain(nodeList,"train",aList);
        //assert that aList is not null, contains an intanceOf Train and has a size of 1
        assert aList != null;
        assert aList.get(0) instanceof Train;
        assert aList.size() == 1;
    }

    void parseTrain(NodeList nodeList,String tagName,List<Object> objectsList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            //check if the node is an element
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //check if the element is a train
                if (element.getNodeName().equals("train")) {
                    //create a train object
                    List<Object> trainNumbers = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("trainNumbers").item(0).getChildNodes(),"trainNumber",trainNumbers);
                    String anno = element.getElementsByTagName("anno").item(0).getTextContent();
                    String time = element.getElementsByTagName("time").item(0).getTextContent();
                    String additionalText = element.getElementsByTagName("additionalText").item(0).getTextContent();
                    List<Object> subtrains = new ArrayList<>();
//                    this.parseTrain(element.getElementsByTagName("subtrains"),"subtrain",subtrains);
                    List<Object> waggons = new ArrayList<>();
                    this.parseTrain(element.getElementsByTagName("waggons").item(0).getChildNodes(),"waggon",waggons);
                    List<Object> trainTypes = new ArrayList<>();
//                    this.parseTrain(element.getElementsByTagName("traintypes"),"traintype",traintypes);
                    Object obj = this.createTrain(trainNumbers,anno,time,additionalText,subtrains,waggons,trainTypes);
                    objectsList.add(obj);

                }
                else if (element.getNodeName().equals("trainNumber")) {
                    String trainNumber=element.getTextContent();
                    Object obj = this.createTrainNumber(trainNumber);
                    objectsList.add(obj);
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
                }


            }
        }
    }

    private Object createWaggon(Integer position, Integer isWaggon, List<Object> sections, String number, String type, String symbols, String differentDestination, Integer length) {
        Waggon waggon = new Waggon();
        waggon.setPosition(position);
        waggon.setIsWaggon(isWaggon);
        //Cast the list of objects to a list of Sections
        List<Section> sections1 = new ArrayList<>();
        waggon.setSections(sections1);
        waggon.setNumber(number);
        waggon.setType(type);
        waggon.setSymbols(symbols);
        waggon.setDifferentDestination(differentDestination);
        waggon.setLength(length);
        return waggon;

    }

    //    class TrainNumber {
//        private String trainNumber;
    public TrainNumber createTrainNumber(String trainNumber){
        TrainNumber aTrainNumber=new TrainNumber();
        aTrainNumber.setTrainNumber(trainNumber);
        return aTrainNumber;
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
    public Train createTrain(List<Object> trainNumbers, String anno, String time, String additionalText, List<Object> subtrains, List<Object> waggons, List<Object> traintypes) {
        Train train = new Train();
        train.setUuid(UUID.randomUUID().toString());
        //Cast the list of objects to a list of TrainNumbers
        List<TrainNumber> trainNumbers1 = new ArrayList<>();
        for (Object obj: trainNumbers) {
            trainNumbers1.add((TrainNumber) obj);
        }
        train.setTrainNumbers(trainNumbers1);
        train.setAnno(anno);
        train.setTime(time);
        train.setAdditionalText(additionalText);
        //Cast the list of objects to a list of Subtrains
        List<Subtrain> subtrains1 = new ArrayList<>();
        for (Object obj: subtrains) {
            subtrains1.add((Subtrain) obj);
        }
        train.setSubtrains(subtrains1);
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