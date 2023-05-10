package com.example.trainschedule.models;

//<trains>
//				<train>
//					<trainNumbers>
//						<trainNumber>1098</trainNumber>
//					</trainNumbers>
//					<anno>
//					</anno>
//					<time>06:16:00</time>
//					<additionalText>Mo bis Do</additionalText>
//					<subtrains>
//						<subtrain>...</subtrain>
//					</subtrains>
//					<waggons>
//						<waggon>...</waggon>
//					</waggons>
//                  <traintypes>
//                      <traintype>IC</traintype>
//                  </traintypes>
//              </train>
//</trains>

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Entity
@Data
public class Train {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @ManyToOne
        private Track track;


        @ManyToMany(mappedBy = "train")
        private List<TrainNumber> trainNumbers;

        private String anno;
        private String time;
        private String additionalText;


        @OneToMany(mappedBy = "train")
        private List<Waggon> waggons;


        @ManyToMany(mappedBy = "train")
        private List<TrainType> traintypes;

        public Train() {
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public List<TrainNumber> getTrainNumbers() {
                return trainNumbers;
        }

        public void setTrainNumbers(List<TrainNumber> trainNumbers) {
                this.trainNumbers = trainNumbers;
        }

        public String getAnno() {
                return anno;
        }

        public void setAnno(String anno) {
                this.anno = anno;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public String getAdditionalText() {
                return additionalText;
        }

        public void setAdditionalText(String additionalText) {
                this.additionalText = additionalText;
        }

        public List<Waggon> getWaggons() {
                return waggons;
        }

        public void setWaggons(List<Waggon> waggons) {
                this.waggons = waggons;
        }

        public List<TrainType> getTraintypes() {
                return traintypes;
        }

        public void setTraintypes(List<TrainType> traintypes) {
                this.traintypes = traintypes;
        }
}
