package com.example.trainschedule.models;

//<validity>
//		<from>2016-06-12</from>
//		<to>2016-12-10</to>
//</validity>

import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@Embeddable
public class Validity {

    private String from;
    private String to;

    public Validity() {
    }

    public Validity(String from, String to) {
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
            return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Validity validity = (Validity) o;
        return Objects.equals(from, validity.from) && Objects.equals(to, validity.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
