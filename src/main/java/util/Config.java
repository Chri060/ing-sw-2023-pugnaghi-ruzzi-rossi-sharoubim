package util;

import Exceptions.parser.BadJSONFormatException;
import Model.entities.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.patterns.privateObj.PrivateObjectivePattern;

import java.io.IOException;
import java.util.List;

public class Config {

    private static int numberOfCardsOfEachType;

    private static int shelfRows;
    private static int shelfColumns;

    private static int dashboardRows;
    private static int dashboardColumns;

    private static int[] twoPlayersDashboardPattern = {
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 1, 1, 0, 0, 0, 0,
        0, 0, 0, 1, 1, 1, 0, 0, 0,
        0, 0, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 0, 0,
        0, 0, 0, 1, 1, 1, 0, 0, 0,
        0, 0, 0, 0, 1, 1, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int[] threePlayersDashboardPattern = {
        0, 0, 0, 1, 0, 0, 0, 0, 0,
        0, 0, 0, 1, 1, 0, 0, 0, 0,
        0, 0, 1, 1, 1, 1, 1, 0, 0,
        0, 0, 1, 1, 1, 1, 1, 1, 1,
        0, 1, 1, 1, 1, 1, 1, 1, 0,
        1, 1, 1, 1, 1, 1, 1, 0, 0,
        0, 0, 1, 1, 1, 1, 1, 0, 0,
        0, 0, 0, 0, 1, 1, 0, 0, 0,
        0, 0, 0, 0, 0, 1, 0, 0, 0};
    private static int[] fourPlayersDashboardPattern = {
        0, 0, 0, 1, 1, 0, 0, 0, 0,
        0, 0, 0, 1, 1, 1, 0, 0, 0,
        0, 0, 1, 1, 1, 1, 1, 0, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 0, 1, 1, 1, 1, 1, 0, 0,
        0, 0, 0, 1, 1, 1, 0, 0, 0,
        0, 0, 0, 0, 1, 1, 0, 0, 0};

    private static int[] customDashboardPattern;

    private static int[] twoPlayersCommonPoints = {8, 4};
    private static int[] threePlayersCommonPoints = {8, 6, 4};
    private static int[] fourPlayersCommonPoints = {8, 6, 4, 2};
    private static int[] customCommonPoints;

    private static int numberOfCommonObjectives;
    private static int numberOfPrivateObjectives;

    private static int[] privateObjectivePoints;
    private static PrivateObjectivePattern[] privateObjectivePatterns;

    private static int availablePrivateObjectives;


    public static void initialise(int numberOfPlayers) {
            //Parses Shelf size
            try {
                Parser parser = new Parser("src/main/resources/Model/shelfConfig.json");
                shelfRows = parser.getInt("rows");
                shelfColumns = parser.getInt("columns");
                if (shelfColumns <= 0 || shelfRows <= 0) {
                    throw new BadJSONFormatException("Shelf Rows and Columns cannot be negative");
                }
            } catch (IOException | ParseException | BadJSONFormatException e) {
                System.err.println("Custom config loading failed while loading shelf config:");
                System.out.println(e.getMessage());
                defaultInitialise(numberOfPlayers);
            }

            //Parses dashBoard size and pattern
            try {
                Parser parser = new Parser("src/main/resources/Model/dashboardConfig.json");
                dashboardRows = parser.getInt("rows");
                dashboardColumns = parser.getInt("columns");
                customDashboardPattern = parser.getIntArray(numberOfPlayers + "PlayersPattern");

                if (dashboardRows <= 0 || dashboardColumns <= 0) {
                    throw new BadJSONFormatException("Dashboard Rows and Columns cannot be negative");
                }

                if (customDashboardPattern.length != dashboardRows * dashboardColumns) {
                    throw new BadJSONFormatException("Dashboard pattern length and pattern mismatch");
                }

            } catch (IOException | ParseException | BadJSONFormatException e) {
                System.err.println("Custom config loading failed while loading dashboard config:");
                System.out.println(e.getMessage());
                defaultInitialise(numberOfPlayers);
            }

            //Parses number of cards for each type of cards
            try {
                Parser parser = new Parser("src/main/resources/Model/bagConfig.json");
                numberOfCardsOfEachType = parser.getInt("numberOfCardsOfEachType");
                if (numberOfCardsOfEachType <= 0) {
                    throw new BadJSONFormatException("Number of Cards of each type cannot be negative");
                } //Total number of cards must full every player shelves except one tile
                if (numberOfCardsOfEachType * Card.Type.values().length <= ((shelfRows * shelfColumns - 1) * numberOfPlayers) + 1) {
                    throw new BadJSONFormatException("Number of cards doesn't secure the mathematical end of the game");
                }
            } catch (IOException | ParseException | BadJSONFormatException e) {
                System.err.println("Custom config loading failed while loading bag config:");
                System.out.println(e.getMessage());
                defaultInitialise(numberOfPlayers);
            }

            //Parses common objectives points
            try {
                Parser parser = new Parser("src/main/resources/Model/commonObjectiveConfig.json");
                customCommonPoints = parser.getIntArray(numberOfPlayers + "PlayerPoints");
                if (customCommonPoints.length != numberOfPlayers) {
                    throw new BadJSONFormatException("Players number and points values mismatch");
                }
            } catch (IOException | ParseException | BadJSONFormatException e) {
                System.err.println("Custom config loading failed while loading common objective config:");
                System.out.println(e.getMessage());
                defaultInitialise(numberOfPlayers);
            }

            //Parses Private objective patterns
            try {
                Parser parser = new Parser("src/main/resources/Model/privateObjectiveConfig.json");
                //Gets patterns array object
                JSONArray JsonPrivateObjectivePatterns = parser.getArray("patterns");
                privateObjectivePoints = parser.getIntArray("points");
                //Verifies are all valid
                for (Object pattern : JsonPrivateObjectivePatterns) {
                    Checker.verifyPrivateJsonPattern((JSONObject) pattern, privateObjectivePoints.length);
                }
                //If they are all valid allocates JsonPrivateObjectivesPatterns size
                availablePrivateObjectives = JsonPrivateObjectivePatterns.size();
                privateObjectivePatterns = new PrivateObjectivePattern[availablePrivateObjectives];
                //Iterates on the json patterns
                for (int i = 0; i < JsonPrivateObjectivePatterns.size(); i++) {
                    //For each pattern gets the coordinates and the types
                    JSONObject jsonPattern = (JSONObject) JsonPrivateObjectivePatterns.get(i);
                    int[] coordinates = Parser.getIntArray("objectiveCoordinates", jsonPattern);
                    PlanarCoordinate[] planarCoordinates = PlanarCoordinate.intArrayToCordsArray(coordinates);
                    Card.Type[] types = Parser.getTypesArray("objectiveItems", jsonPattern);
                    //And creates the new corresponding pattern
                    privateObjectivePatterns[i] = new PrivateObjectivePattern(planarCoordinates, types);
                }

            } catch (IOException | ParseException | BadJSONFormatException e) {
                System.err.println("Custom config loading failed while loading private Objective config:");
                System.out.println(e.getMessage());
                defaultInitialise(numberOfPlayers);
            }
            //Parses number of objectives
            try {
                Parser parser = new Parser("src/main/resources/Model/gameConfig.json");
                numberOfCommonObjectives = parser.getInt("commonObjectives");
                numberOfPrivateObjectives = parser.getInt("privateObjectives");
                if (numberOfCommonObjectives < 0 || numberOfPrivateObjectives < 0) {
                    throw new BadJSONFormatException("Invalid number of objectives");
                }
                if (numberOfPrivateObjectives * numberOfPlayers > availablePrivateObjectives) {
                    throw new BadJSONFormatException("Not enough private objectives for everyone");
                }
            } catch (IOException | ParseException | BadJSONFormatException e) {
                System.err.println("Custom config loading failed while loading game objective config:");
                System.out.println(e.getMessage());
                defaultInitialise(numberOfPlayers);
            }
        }

    //Uses default config defined for the game
    public static void defaultInitialise(int playerNumber) {

        System.out.println("Using default config for " + playerNumber + " players");

        if (playerNumber <= 4 && playerNumber >= 2) {

            shelfRows = 6;
            shelfColumns = 5;

            dashboardRows = 9;
            dashboardColumns = 9;

            numberOfCardsOfEachType = 22;

            numberOfCommonObjectives = 2;
            numberOfPrivateObjectives = 1;

            switch (playerNumber) {
                case 2 -> {
                    customDashboardPattern = twoPlayersDashboardPattern;
                    customCommonPoints = twoPlayersCommonPoints;
                }
                case 3 -> {
                    customDashboardPattern = threePlayersDashboardPattern;
                    customCommonPoints = threePlayersCommonPoints;
                }
                case 4 -> {
                    customDashboardPattern = fourPlayersDashboardPattern;
                    customCommonPoints = fourPlayersCommonPoints;
                }
            }

        }
        else {
            System.err.println("No default settings available for " + playerNumber + " players:" +
                    "\nEnding process");
            System.exit(-1);
        }

    }


    public static int getNumberOfCardsOfEachType() {
        return numberOfCardsOfEachType;
    }

    public static int getShelfRows() {
        return shelfRows;
    }
    public static int getShelfColumns() {
        return shelfColumns;
    }

    public static int getDashboardColumns() {
        return dashboardColumns;
    }

    public static int getDashboardRows() {
        return dashboardRows;
    }

    public static boolean[][] getDashboardPattern() {
        boolean[][] result = new boolean[dashboardRows][dashboardColumns];

        for (int i = 0; i < dashboardRows; i++) {
            for (int j = 0; j < dashboardColumns; j++) {
                result[i][j] = 1 == customDashboardPattern[j + i * dashboardColumns];
            }
        }
        return result;
    }

    public static int[] getCommonPoints() {
        return customCommonPoints.clone();
    }

    public static int[] getPrivatePoints() {
        return privateObjectivePoints.clone();
    }
    public static PrivateObjectivePattern getPrivateObjectivePattern(int i) {
        return privateObjectivePatterns[i];
    }
    public static int getAvailablePrivateObjectives() {
        return availablePrivateObjectives;
    }

    public static int getNumberOfCommonObjectives() {
        return numberOfCommonObjectives;
    }
    public static int getNumberOfPrivateObjectives() {
        return numberOfPrivateObjectives;
    }

}
