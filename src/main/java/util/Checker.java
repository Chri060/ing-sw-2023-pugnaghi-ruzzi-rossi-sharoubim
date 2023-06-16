package util;

import Exceptions.parser.BadJSONFormatException;
import Model.entities.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Class that checks if the coordinates given are correct or not
 */
public class Checker {

    /**
     * Checks if the given shelf coordinate is valid
     *
     * @param planarCoordinate is a coordinate to check
     *
     * @return true if the coordinate is valid, false otherwise
     */
    public static boolean shelfCoordinatesAreValid(PlanarCoordinate planarCoordinate) {
        int rows = Config.getShelfRows();
        int columns = Config.getShelfColumns();
        if (planarCoordinate.getRow() < 0 || planarCoordinate.getRow() >= rows
            || planarCoordinate.getColumn() < 0 || planarCoordinate.getColumn() >= columns) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the given shelf coordinates are valid
     *
     * @param planarCoordinate is a list of coordinates to check
     *
     * @return true if the coordinates are valid, false otherwise
     */
    public static boolean shelfCoordinatesAreValid(List<PlanarCoordinate> planarCoordinate) {
        return planarCoordinate.stream().noneMatch(x -> !shelfCoordinatesAreValid(x));
    }

    /**
     * Checks if the given dashboard coordinate is valid
     *
     * @param planarCoordinate is a coordinate to check
     *
     * @return true if the coordinate is valid, false otherwise
     */
    public static boolean dashboardCoordinatesAreValid(PlanarCoordinate planarCoordinate) {
        int rows = Config.getDashboardRows();
        int columns = Config.getDashboardColumns();
        if (planarCoordinate.getRow() < 0 || planarCoordinate.getRow() >= rows
                || planarCoordinate.getColumn() < 0 || planarCoordinate.getColumn() >= columns) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the given dashboard coordinates are valid
     *
     * @param planarCoordinate is a list of coordinates to check
     *
     * @return true if the coordinates are valid, false otherwise
     */
    public static boolean dashboardCoordinatesAreValid(List<PlanarCoordinate> planarCoordinate) {
        return planarCoordinate.stream().noneMatch(x -> !dashboardCoordinatesAreValid(x));
    }

    /**
     * Checks if the given JSON pattern is valid
     *
     * @param pattern is the JSONObject retrieved from the JSON file
     * @param length is the correct length of the patter
     *
     * @throws BadJSONFormatException on error with the format of the JSON files
     */
    public static void verifyPrivateJsonPattern(JSONObject pattern, int length) throws BadJSONFormatException {
        int[] coordinates = Parser.getIntArray("objectiveCoordinates", pattern);
        List<PlanarCoordinate> planarCoordinateList = PlanarCoordinate.intArrayToCordsList(coordinates);
        if (planarCoordinateList.size() + 1 != length) {
            throw new BadJSONFormatException("Private objectives patterns and points mismatch");
        }
        if (!Checker.shelfCoordinatesAreValid(planarCoordinateList)) {
            throw new BadJSONFormatException("Private objectives patterns aren't compatible with shelf");
        }
        JSONArray typesPattern = Parser.getArray("objectiveItems", pattern);
        for (Object item : typesPattern) {
            try {
                Card.Type.valueOf((String) item);
            } catch (IllegalArgumentException e) {
                throw new BadJSONFormatException("Invalid Type");
            }
        }
    }
}