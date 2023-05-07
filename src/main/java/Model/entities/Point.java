package Model.entities;

import java.io.Serializable;

public class Point implements Serializable {
        private int value;
        private String origin;

        public Point(int value, String origin) {
            this.value = value;
            this.origin = origin;
        }

        public int getValue() {
            return value;
        }
        public String getOrigin() {
            return origin;
        }

}
