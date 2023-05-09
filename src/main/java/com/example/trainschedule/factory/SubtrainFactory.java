package com.example.trainschedule.factory;

import com.example.trainschedule.models.Destination;
import com.example.trainschedule.models.Subtrain;
import com.example.trainschedule.models.Section;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class SubtrainFactory {

    public Object createSubtrain(String type, Destination destination, List<Section> sections) {
        if (type.equals("subtrain")) {
            Subtrain subtrain = new Subtrain();
            //using UUID5, set the namespace to UUID4 and a base
            //UUID namespace = UUID.fromString("3da1fca1-bea1-45f0-9742-2c0cffbeabd1"); // todo generate a UUID v4.
            //String input = "input";
            //UUID uuid = UuidCreator.getNameBasedSha1(namespace, input);
            UUID uuid = UUID.randomUUID();

            subtrain.setDestination(destination);
            subtrain.setSections(sections);
            subtrain.setUuid(uuid.toString());
            return subtrain;
        } else {
            return null;
        }
    }
}
