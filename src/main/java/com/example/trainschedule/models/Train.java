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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "train")
public class Train {

        @XmlElementWrapper(name = "trainNumbers")
        @XmlElement(name = "trainNumber")
        private List<TrainNumber> trainNumbers;
        private String anno;
        private String time;
        private String additionalText;
        private List<Subtrain> subtrains;
        private List<Waggon> waggons;
        private List<TrainType> traintypes;

        public Train() {
        }

        public Train(List<TrainNumber> trainNumbers, String anno, String time, String additionalText, List<Subtrain> subtrains, List<Waggon> waggons, List<TrainType> traintypes) {
            this.trainNumbers = trainNumbers;
            this.anno = anno;
            this.time = time;
            this.additionalText = additionalText;
            this.subtrains = subtrains;
            this.waggons = waggons;
            this.traintypes = traintypes;
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

        public List<Subtrain> getSubtrains() {
            return subtrains;
        }

        public void setSubtrains(List<Subtrain> subtrains) {
            this.subtrains = subtrains;
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
