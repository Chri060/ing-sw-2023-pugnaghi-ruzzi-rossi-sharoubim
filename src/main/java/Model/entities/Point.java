package Model.entities;

public class Point {
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
