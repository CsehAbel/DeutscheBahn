package com.example.trainschedule.models;

//<sections>
//<identifier>A</identifier>
//<identifier>B</identifier>
//<identifier>C</identifier>
//<identifier>D</identifier>
//</sections>

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Section {

        @Id
        private String identifier;

        @ManyToMany(mappedBy = "sections")
        private List<Waggon> waggons;

        public Section() {
        }
        public Section(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }
}
