package com.example.trainschedule;

import com.example.trainschedule.models.Destination;
import com.example.trainschedule.models.Section;
import com.example.trainschedule.models.Waggon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class WaggonDeserializeTest {

    @Test
    void contextLoads() {
    }

    //test for Waggon class deserialize from xml
    @Test
    void testWaggonDeserialize() throws ParserConfigurationException, IOException, SAXException {

        //access a file under test resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("waggons.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(inputStream);

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        //iterate through the nodes
        List<Waggon> waggonList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            //check if the node is an element
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //check if the element is a waggon
                if (element.getNodeName().equals("waggon")) {
                    //create a waggon object
                    Waggon waggon = new Waggon();
                    //set the waggon properties
                    waggon.setPosition(Integer.parseInt(element.getElementsByTagName("position").item(0).getTextContent()));
                    waggon.setIsWaggon(Integer.parseInt(element.getElementsByTagName("isWaggon").item(0).getTextContent()));
                    waggon.setNumber(element.getElementsByTagName("number").item(0).getTextContent());
                    waggon.setType(element.getElementsByTagName("type").item(0).getTextContent());
                    waggon.setSymbols(element.getElementsByTagName("symbols").item(0).getTextContent());
                    waggon.setDifferentDestination(element.getElementsByTagName("differentDestination").item(0).getTextContent());
                    waggon.setLength(Integer.parseInt(element.getElementsByTagName("length").item(0).getTextContent()));
                    //print the waggon object
                    waggonList.add(waggon);
                }
            }
        }
        assert waggonList.size()>0;

    }


    public Destination parseDestination(NodeList nodeList,String tagName) {
        Destination destination = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if(node.getNodeName().equals(tagName)){
                Element element = (Element) node;
                if(element.getNodeName().equals(tagName)){

                    String destinationName = element.getElementsByTagName("destinationName").item(0).getFirstChild().getTextContent();
                    String destinationVia = element.getAttribute("destinationVia");
                    destination = this.createDestination(destinationName,destinationVia);
                }

            }
        }
        return destination;

    }

    public Destination createDestination(String destinationName, String destinationVia) {
        Destination destination = new Destination();
        UUID uuid = UUID.randomUUID();
        destination.setUuid(uuid.toString());
        destination.setDestinationName(destinationName);
        destination.setDestinationVia(destinationVia);
        return destination;
    }

    public List<Section> parseSections(NodeList nodeList, String tagName) {
        List<Section> objects = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            if(element.getNodeName().equals(tagName)){
                String type = element.getAttribute("type");
                String identifier = element.getAttribute("identifier");
                Section obj = this.createSection(type,identifier);//subtrainFactory.createSubtrain(tagName, destination, sections);
                objects.add(obj);
            }
        }
        return objects;
    }

    public Section createSection(String type, String identifier) {
        if (type.equals("section")) {
            Section section = new Section();
            //using UUID5, set the namespace to UUID4 and a base
            //UUID namespace = UUID.fromString("3da1fca1-bea1-45f0-9742-2c0cffbeabd1"); // todo generate a UUID v4.
            String input = "input";
            UUID uuid = UUID.randomUUID();

            section.setIdentifier(identifier);
            return section;
        } else {
            return null;
        }
    }


}
