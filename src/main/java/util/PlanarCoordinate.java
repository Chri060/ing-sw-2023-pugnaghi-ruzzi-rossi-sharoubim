package util;


import java.util.Arrays;
import java.util.List;

public class PlanarCoordinate {
    int row;
    int column;

    public PlanarCoordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public PlanarCoordinate(PlanarCoordinate planarCoordinate) {
        this.row = planarCoordinate.row;
        this.column = planarCoordinate.column;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    public PlanarCoordinate getRight() {
        return new PlanarCoordinate(this.getRow() + 1, this.getColumn());
    }

    public PlanarCoordinate getLeft() {
        return new PlanarCoordinate(this.getRow() - 1, this.getColumn());
    }

    public PlanarCoordinate getUp() {
        return new PlanarCoordinate(this.getRow(), this.getColumn() - 1);
    }

    public PlanarCoordinate getDown() {
        return new PlanarCoordinate(this.getRow(), this.getColumn() + 1);
    }

    public static PlanarCoordinate[] intArrayToCordsArray(int[] coordinatesArray) {
        PlanarCoordinate[] result = new PlanarCoordinate[coordinatesArray.length/2];
        for (int i = 0; i < coordinatesArray.length / 2; i++) {
            result[i] = (new PlanarCoordinate(coordinatesArray[2 * i], coordinatesArray[2 * i + 1]));
        }
        return result;
    }

    public static List<PlanarCoordinate> intArrayToCordsList(int[] coordinatesArray) {
        return Arrays.stream(intArrayToCordsArray(coordinatesArray)).toList();
    }
}
