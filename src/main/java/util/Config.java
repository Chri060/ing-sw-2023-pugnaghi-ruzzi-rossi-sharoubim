package util;

import Exceptions.parser.BadJSONFormatException;
import Model.entities.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.patterns.privateObj.PrivateObjectivePattern;

import java.io.IOException;
import java.util.Arrays;

/**
 * Class used to get the data from the JSON file
 */
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
    private static int[] shelfPoints = {0, 0, 2, 3, 5, 8};
    private static int[] customShelfPoints;
    private static int numberOfCommonObjectives;
    private static int numberOfPrivateObjectives;
    private static int[] privateObjectivePoints = {0, 1, 2, 4, 6, 9, 12};

    private static PlanarCoordinate[] coordinates1 = {
            new PlanarCoordinate(0, 0),
            new PlanarCoordinate(0, 2),
            new PlanarCoordinate(1, 4),
            new PlanarCoordinate(2, 3),
            new PlanarCoordinate(3, 1),
            new PlanarCoordinate(5, 5)
    };
    private static Card.Type[] objects1 = {
            Card.Type.PLANT, Card.Type.FRAME, Card.Type.CAT, Card.Type.BOOK, Card.Type.GAME, Card.Type.TROPHY
    };

    private static PlanarCoordinate[] coordinates2 = {
            new PlanarCoordinate(1, 1),
            new PlanarCoordinate(2, 0),
            new PlanarCoordinate(2, 2),
            new PlanarCoordinate(3, 4),
            new PlanarCoordinate(4, 3),
            new PlanarCoordinate(5, 4)
    };
    private static Card.Type[] objects2 = {
            Card.Type.PLANT, Card.Type.CAT, Card.Type.GAME, Card.Type.BOOK, Card.Type.TROPHY, Card.Type.FRAME
    };

    private static PlanarCoordinate[] coordinates3 = {
            new PlanarCoordinate(1, 0),
            new PlanarCoordinate(1, 3),
            new PlanarCoordinate(2, 2),
            new PlanarCoordinate(3, 1),
            new PlanarCoordinate(3, 4),
            new PlanarCoordinate(5, 0)
    };
    private static Card.Type[] objects3 = {
            Card.Type.FRAME, Card.Type.GAME, Card.Type.PLANT, Card.Type.CAT, Card.Type.TROPHY, Card.Type.BOOK
    };

    private static PlanarCoordinate[] coordinates4 = {
            new PlanarCoordinate(0, 4),
            new PlanarCoordinate(2, 0),
            new PlanarCoordinate(2, 2),
            new PlanarCoordinate(3, 3),
            new PlanarCoordinate(4, 1),
            new PlanarCoordinate(4, 2)
    };
    private static Card.Type[] objects4 = {
            Card.Type.GAME, Card.Type.TROPHY, Card.Type.FRAME, Card.Type.PLANT, Card.Type.BOOK, Card.Type.CAT
    };

    private static PlanarCoordinate[] coordinates5 = {
            new PlanarCoordinate(1, 1),
            new PlanarCoordinate(3, 1),
            new PlanarCoordinate(3, 2),
            new PlanarCoordinate(4, 4),
            new PlanarCoordinate(5, 0),
            new PlanarCoordinate(5, 3)
    };
    private static Card.Type[] objects5 = {
            Card.Type.TROPHY, Card.Type.FRAME, Card.Type.BOOK, Card.Type.PLANT, Card.Type.GAME, Card.Type.CAT
    };


    private static PlanarCoordinate[] coordinates6 = {
            new PlanarCoordinate(0, 2),
            new PlanarCoordinate(0, 4),
            new PlanarCoordinate(2, 3),
            new PlanarCoordinate(4, 1),
            new PlanarCoordinate(4, 3),
            new PlanarCoordinate(5, 0)
    };
    private static Card.Type[] objects6 = {
            Card.Type.TROPHY, Card.Type.CAT, Card.Type.BOOK, Card.Type.GAME, Card.Type.FRAME, Card.Type.PLANT
    };

    private static PlanarCoordinate[] coordinates7 = {
            new PlanarCoordinate(0, 0),
            new PlanarCoordinate(1, 3),
            new PlanarCoordinate(2, 1),
            new PlanarCoordinate(3, 0),
            new PlanarCoordinate(4, 4),
            new PlanarCoordinate(5, 2)
    };
    private static Card.Type[] objects7 = {
            Card.Type.CAT, Card.Type.FRAME, Card.Type.PLANT, Card.Type.TROPHY, Card.Type.GAME, Card.Type.BOOK
    };

    private static PlanarCoordinate[] coordinates8 = {
            new PlanarCoordinate(0, 4),
            new PlanarCoordinate(1, 1),
            new PlanarCoordinate(2, 2),
            new PlanarCoordinate(3, 0),
            new PlanarCoordinate(4, 3),
            new PlanarCoordinate(5, 3)
    };
    private static Card.Type[] objects8 = {
            Card.Type.FRAME, Card.Type.CAT, Card.Type.TROPHY, Card.Type.PLANT, Card.Type.BOOK, Card.Type.GAME
    };

    private static PlanarCoordinate[] coordinates9 = {
            new PlanarCoordinate(0, 2),
            new PlanarCoordinate(2, 2),
            new PlanarCoordinate(3, 4),
            new PlanarCoordinate(4, 1),
            new PlanarCoordinate(4, 4),
            new PlanarCoordinate(5, 0)
    };
    private static Card.Type[] objects9 = {
            Card.Type.GAME, Card.Type.CAT, Card.Type.BOOK, Card.Type.TROPHY, Card.Type.PLANT, Card.Type.FRAME
    };

    private static PlanarCoordinate[] coordinates10 = {
            new PlanarCoordinate(0, 4),
            new PlanarCoordinate(1, 1),
            new PlanarCoordinate(2, 0),
            new PlanarCoordinate(3, 3),
            new PlanarCoordinate(4, 1),
            new PlanarCoordinate(5, 3)
    };
    private static Card.Type[] objects10 = {
            Card.Type.TROPHY, Card.Type.GAME, Card.Type.BOOK, Card.Type.CAT, Card.Type.FRAME, Card.Type.PLANT
    };

    private static PlanarCoordinate[] coordinates11 = {
            new PlanarCoordinate(0, 2),
            new PlanarCoordinate(1, 1),
            new PlanarCoordinate(2, 0),
            new PlanarCoordinate(3, 2),
            new PlanarCoordinate(4, 4),
            new PlanarCoordinate(5, 3)
    };
    private static Card.Type[] objects11 = {
            Card.Type.PLANT, Card.Type.BOOK, Card.Type.GAME, Card.Type.FRAME, Card.Type.CAT, Card.Type.TROPHY
    };

    private static PlanarCoordinate[] coordinates12 = {
            new PlanarCoordinate(0, 2),
            new PlanarCoordinate(1, 1),
            new PlanarCoordinate(2, 2),
            new PlanarCoordinate(3, 3),
            new PlanarCoordinate(4, 4),
            new PlanarCoordinate(5, 0)
    };
    private static Card.Type[] objects12 = {
            Card.Type.BOOK, Card.Type.PLANT, Card.Type.FRAME, Card.Type.TROPHY, Card.Type.GAME, Card.Type.CAT
    };



    private static PrivateObjectivePattern[] privateObjectivePatterns = {
            new PrivateObjectivePattern(coordinates1, objects1),
            new PrivateObjectivePattern(coordinates2, objects2),
            new PrivateObjectivePattern(coordinates3, objects3),
            new PrivateObjectivePattern(coordinates4, objects4),
            new PrivateObjectivePattern(coordinates5, objects5),
            new PrivateObjectivePattern(coordinates6, objects6),
            new PrivateObjectivePattern(coordinates7, objects7),
            new PrivateObjectivePattern(coordinates8, objects8),
            new PrivateObjectivePattern(coordinates9, objects9),
            new PrivateObjectivePattern(coordinates10, objects10),
            new PrivateObjectivePattern(coordinates11, objects11),
            new PrivateObjectivePattern(coordinates12, objects12),
    };




    private static int availablePrivateObjectives = 12;

    /**
     * Parser for the variable from the JSON files
     *
     * @param numberOfPlayers is the number of player used to select the correct configurations
     */
    public static void initialise(int numberOfPlayers) {
        //Parses Shelf size and shelf points
        try {
            Parser parser = new Parser("./shelfConfig.json");
            shelfRows = parser.getInt("rows");
            shelfColumns = parser.getInt("columns");
            if (shelfColumns <= 0 || shelfRows <= 0) {
                throw new BadJSONFormatException("Shelf Rows and Columns cannot be negative");
            }
            customShelfPoints = parser.getIntArray("shelfPoints");
            if (customShelfPoints.length < 1) {
                throw new BadJSONFormatException("Not enough shelf points defined");
            }
            if (Arrays.stream(customShelfPoints).anyMatch(x -> x < 0)) {
                throw new BadJSONFormatException("ShelfPoints can't be negative");
            }
        } catch (IOException | ParseException | BadJSONFormatException  e) {
            System.err.println("Custom config loading failed while loading shelf config:");
            System.out.println(e.getMessage());
            defaultInitialise(numberOfPlayers);
            return;
        }
        //Parses dashBoard size and pattern
        try {
            Parser parser = new Parser("./dashboardConfig.json");
            dashboardRows = parser.getInt("rows");
            dashboardColumns = parser.getInt("columns");
            customDashboardPattern = parser.getIntArray(numberOfPlayers + "PlayersPattern");
            if (dashboardRows <= 0 || dashboardColumns <= 0) {
                throw new BadJSONFormatException("Dashboard Rows and Columns cannot be negative");
            }
            if (customDashboardPattern.length != dashboardRows * dashboardColumns) {
                throw new BadJSONFormatException("Dashboard pattern length and pattern mismatch");
            }
        } catch (IOException | ParseException | BadJSONFormatException  e) {
            System.err.println("Custom config loading failed while loading dashboard config:");
            System.out.println(e.getMessage());
            defaultInitialise(numberOfPlayers);
            return;
        }
        //Parses number of cards for each type of cards
        try {
            Parser parser = new Parser("./bagConfig.json");
            numberOfCardsOfEachType = parser.getInt("numberOfCardsOfEachType");
            if (numberOfCardsOfEachType <= 0) {
                throw new BadJSONFormatException("Number of Cards of each type cannot be negative");
            } //Total number of cards must full every player shelves except one tile
            if (numberOfCardsOfEachType * Card.Type.values().length <= ((shelfRows * shelfColumns - 1) * numberOfPlayers) + 1) {
                throw new BadJSONFormatException("Number of cards doesn't secure the mathematical end of the game");
            }
        } catch (IOException | ParseException | BadJSONFormatException  e) {
            System.err.println("Custom config loading failed while loading bag config:");
            System.out.println(e.getMessage());
            defaultInitialise(numberOfPlayers);
            return;
        }
        //Parses common objectives points
        try {
            Parser parser = new Parser("./commonObjectiveConfig.json");
            customCommonPoints = parser.getIntArray(numberOfPlayers + "PlayerPoints");
            if (customCommonPoints.length != numberOfPlayers) {
                throw new BadJSONFormatException("Players number and points values mismatch");
            }
        } catch (IOException | ParseException | BadJSONFormatException  e) {
            System.err.println("Custom config loading failed while loading common objective config:");
            System.out.println(e.getMessage());
            defaultInitialise(numberOfPlayers);
            return;
        }
        //Parses Private objective patterns
        try {
            Parser parser = new Parser("./privateObjectiveConfig.json");
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
        } catch (IOException | ParseException | BadJSONFormatException  e) {
            System.err.println("Custom config loading failed while loading private Objective config:");
            System.out.println(e.getMessage());
            defaultInitialise(numberOfPlayers);
            return;
        }
        //Parses number of objectives
        try {
            Parser parser = new Parser("./gameConfig.json");
            numberOfCommonObjectives = parser.getInt("commonObjectives");
            numberOfPrivateObjectives = parser.getInt("privateObjectives");
            if (numberOfCommonObjectives < 0 || numberOfPrivateObjectives < 0) {
                throw new BadJSONFormatException("Invalid number of objectives");
            }
            if (numberOfPrivateObjectives * numberOfPlayers > availablePrivateObjectives) {
                throw new BadJSONFormatException("Not enough private objectives for everyone");
            }
        } catch (IOException | ParseException | BadJSONFormatException  e) {
            System.err.println("Custom config loading failed while loading game objective config:");
            System.out.println(e.getMessage());
            defaultInitialise(numberOfPlayers);
        }
    }

    /**
     * If there are problems with the file initialization this method sets the default settings
     *
     * @param playerNumber is the number of player selected for the default configuration
     */
    public static void defaultInitialise(int playerNumber) {
        System.out.println("Using default config for " + playerNumber + " players");
        if (playerNumber <= 4 && playerNumber >= 2) {
            shelfRows = 6;
            shelfColumns = 5;
            customShelfPoints = shelfPoints;
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
            System.err.println("No default settings available for " + playerNumber + " players:\n" +
                                "Ending process");
            System.exit(-1);
        }
    }

    /**
     * @return the number of cards of each type set for the current match
     */
    public static int getNumberOfCardsOfEachType() {
        return numberOfCardsOfEachType;
    }

    /**
     * @return the number of shelf rows set for the current match
     */
    public static int getShelfRows() {
        return shelfRows;
    }

    /**
     * @return the number of shelf columns set for the current match
     */
    public static int getShelfColumns() {
        return shelfColumns;
    }

    /**
     * @return the value of custom points set for the current match if
     */
    public static int[] getCustomShelfPoints() {
        return customShelfPoints;
    }

    /**
     * @return the number of dashboard columns set for the current match
     */
    public static int getDashboardColumns() {
        return dashboardColumns;
    }

    /**
     * @return the number of dashboard rows set for the current match
     */
    public static int getDashboardRows() {
        return dashboardRows;
    }

    /**
     * @return the dashboard pattern set for the current match
     */
    public static boolean[][] getDashboardPattern() {
        boolean[][] result = new boolean[dashboardRows][dashboardColumns];
        for (int i = 0; i < dashboardRows; i++) {
            for (int j = 0; j < dashboardColumns; j++) {
                result[i][j] = 1 == customDashboardPattern[j + i * dashboardColumns];
            }
        }
        return result;
    }

    /**
     * @return the points given by the common objectives
     */
    public static int[] getCommonPoints() {
        return customCommonPoints.clone();
    }

    /**
     * @return the points given by the private objectives
     */
    public static int[] getPrivatePoints() {
        return privateObjectivePoints.clone();
    }

    /**
     * @param i is the index for the private objective in the array
     *
     * @return the pattern at the index selected if it is not null
     */
    public static PrivateObjectivePattern getPrivateObjectivePattern(int i) {
        return privateObjectivePatterns[i];
    }

    /**
     * @return the available private objectives
     */
    public static int getAvailablePrivateObjectives() {
        return availablePrivateObjectives;
    }

    /**
     * @return the number of common objectives
     */
    public static int getNumberOfCommonObjectives() {
        return numberOfCommonObjectives;
    }

    /**
     * @return the number of private objectives
     */
    public static int getNumberOfPrivateObjectives() {
        return numberOfPrivateObjectives;
    }
}