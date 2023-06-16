package util;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Class that implements the planar coordinate with row and colum indexes
 */
public class PlanarCoordinate implements Serializable {
    int row;
    int column;

    /**
     * Construct a new PlanarCoordinate
     *
     * @param row is the row
     * @param column is the column
     */
    public PlanarCoordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Construct a new PlanarCoordinate based on an old one
     *
     * @param planarCoordinate is the coordinate to copy
     */
    public PlanarCoordinate(PlanarCoordinate planarCoordinate) {
        this.row = planarCoordinate.row;
        this.column = planarCoordinate.column;
    }

    /**
     * @return the row of the PlanarCoordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column of the PlanarCoordinate
     */
    public int getColumn() {
        return column;
    }

    /**
     * @return the coordinate at the right of this
     */
    public PlanarCoordinate getRight() {
        return new PlanarCoordinate(this.getRow() + 1, this.getColumn());
    }

    /**
     * @return the coordinate at the left of this
     */
    public PlanarCoordinate getLeft() {
        return new PlanarCoordinate(this.getRow() - 1, this.getColumn());
    }

    /**
     * @return the coordinate at the north of this
     */
    public PlanarCoordinate getUp() {
        return new PlanarCoordinate(this.getRow(), this.getColumn() - 1);
    }

    /**
     * @return the coordinate at the south of this
     */
    public PlanarCoordinate getDown() {
        return new PlanarCoordinate(this.getRow(), this.getColumn() + 1);
    }

    /**
     * Transform an array of integer in an array of PlanarCoordinates
     *
     * @param coordinatesArray is the array with the integers
     *
     * @return an array of PlanarCoordinate based on the input integer array
     */
    public static PlanarCoordinate[] intArrayToCordsArray(int[] coordinatesArray) {
        PlanarCoordinate[] result = new PlanarCoordinate[coordinatesArray.length/2];
        for (int i = 0; i < coordinatesArray.length / 2; i++) {
            result[i] = (new PlanarCoordinate(coordinatesArray[2 * i], coordinatesArray[2 * i + 1]));
        }
        return result;
    }

    /**
     * Transform an array of integer in a list of PlanarCoordinates
     *
     * @param coordinatesArray is the array with the integers
     *
     * @return a list of PlanarCoordinate based on the input integer array
     */
    public static List<PlanarCoordinate> intArrayToCordsList(int[] coordinatesArray) {
        return Arrays.stream(intArrayToCordsArray(coordinatesArray)).toList();
    }
}