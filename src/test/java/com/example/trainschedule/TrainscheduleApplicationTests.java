package com.example.trainschedule;

import com.example.trainschedule.models.Waggon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SpringBootTest
class TrainscheduleApplicationTests {

    @Test
    void contextLoads() {
    }

    //test for Waggon class deserialize from xml
    @Test
    void testWaggonDeserialize() throws JAXBException {
        String xml = "<waggon>\n" +
                "<position>0</position>\n" +
                "<isWaggon>0</isWaggon>\n" +
                "<sections>\n" +
                "<identifier>A</identifier>\n" +
                "</sections>\n" +
                "<number></number>\n" +
                "<type>s</type>\n" +
                "<symbols></symbols>\n" +
                "<differentDestination></differentDestination>\n" +
                "<length>2</length>\n" +
                "</waggon>";

        JAXBContext jaxbContext = JAXBContext.newInstance(Waggon.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Waggon waggon = (Waggon) unmarshaller.unmarshal(new javax.xml.transform.stream.StreamSource(new java.io.StringReader(xml)));


        assert waggon.getPosition() == 0;
        assert waggon.getIsWaggon() == 0;
        assert waggon.getSections().get(0).getIdentifier().equals("A");
        assert waggon.getNumber().equals("");
        assert waggon.getType().equals("s");
        assert waggon.getSymbols().equals("");
        assert waggon.getDifferentDestination().equals("");
        assert waggon.getLength() == 2;
    }

}
