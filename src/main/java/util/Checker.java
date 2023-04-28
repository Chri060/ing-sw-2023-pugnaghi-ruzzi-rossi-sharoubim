package util;

import Exceptions.parser.BadJSONFormatException;
import Model.entities.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Checker {

    public static boolean shelfCoordinatesAreValid(PlanarCoordinate planarCoordinate) {
        int rows = Config.getShelfRows();
        int columns = Config.getShelfColumns();

        if (planarCoordinate.getRow() < 0 || planarCoordinate.getRow() >= rows
            || planarCoordinate.getColumn() < 0 || planarCoordinate.getColumn() >= columns) {
            return false;
        }
        return true;
    }

    public static boolean shelfCoordinatesAreValid(List<PlanarCoordinate> planarCoordinate) {
        return planarCoordinate.stream().noneMatch(x -> !shelfCoordinatesAreValid(x));
    }

    public static boolean dashboardCoordinatesAreValid(PlanarCoordinate planarCoordinate) {
        int rows = Config.getDashboardRows();
        int columns = Config.getDashboardColumns();

        if (planarCoordinate.getRow() < 0 || planarCoordinate.getRow() >= rows
                || planarCoordinate.getColumn() < 0 || planarCoordinate.getColumn() >= columns) {
            return false;
        }
        return true;
    }

    public static boolean dashboardCoordinatesAreValid(List<PlanarCoordinate> planarCoordinate) {
        return planarCoordinate.stream().noneMatch(x -> !dashboardCoordinatesAreValid(x));
    }

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
