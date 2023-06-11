package Model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent a player with all items connected to him
 */
public class Player {

    private String name;
    private Shelf shelf;
    private List<PrivateObjective> privateObjectives;
    private List<Point> points;
    private boolean isConnected;

    /**
     * Construct a player with some initial parameters
     *
     * @param name is the name of the player that is going to be created
     * @param privateObjective is the list of private objectives given randomly to the player
     */
    public Player(String name, List<PrivateObjective> privateObjective) {
        this.name = name;
        this.shelf = new Shelf();
        this.privateObjectives = new ArrayList<>();
        this.privateObjectives.addAll(privateObjective);
        this.points = new ArrayList<>();
        isConnected = true;
    }

    /**
     * Set to true the value of variable isConnected
     */
    public void setOnline() {
        isConnected = true;
    }

    /**
     * Set to false the value of variable isConnected
     */
    public void setOffline() {
        isConnected = false;
    }

    /**
     * @return true if the player is connected, false otherwise
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the sum of the points that belongs to the player
     */
    public Point getTotalPoints() {
        return new Point(this.getPoints().stream().mapToInt(x -> x.getValue()).sum(), name + "'s points");
    }

    /**
     * @return a list of integer that represent the IDs of the private objective linked to the player
     */
    public List<Integer> getPrivateObjectivesID() {
        return privateObjectives.stream().map(x -> x.getID()).toList();
    }

    /**
     * @return the object Shelf associated to the player
     */
    public Shelf getShelf() {
        return shelf;
    }

    /**
     * @return the points of the player as a list, with the corresponding origin
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Add a point to the list
     *
     * @param point is the point object that will be added to the point's list of the player
     */
    public void givePoint(Point point) {
        points.add(point);
    }

    /**
     * Checks if the player have already completed the common objective chosen
     *
     * @param ID is the ID of the common objective to check
     *
     * @return true if the player has already completed the common objective, false otherwise
     */
    public boolean hasAlreadyGotCommonPoints(int ID) {
        return points.stream().anyMatch(x -> x.getOrigin().equals("Common Objective "  + ID));
    }

    /**
     * @return a list of points given by the private objectives
     */
    public List<Point> getPrivatePoint() {
        return this.privateObjectives.stream().map(x -> x.getMaxPoints(this.shelf)).toList();
    }

    /**
     * @return the sum of points given by the private objectives
     */
    public Point getTotalPrivatePoints() {
        return new Point(this.getPrivatePoint().stream().mapToInt(x -> x.getValue()).sum(), name + "'s private objectives points");
    }

    /**
     * Checks all the private objectives pattern of the player and creates a list with them
     *
     * @return the patterns of the private objectives as a list
     */
    public List<Card.Type[][]> getPrivateObjectivePattern() {
        List<Card.Type[][]> result = new ArrayList<>();
        privateObjectives.forEach(x -> result.add(x.getPattern()));
        return result;
    }

    /**
     * @return a list of integers representing the various groups of cards of the same type
     */
    public List<Integer> getShelfGroups() {
        return shelf.getAdjacentGroupsSizes();
    }

    /**
     * Checks if the object given is equal to this
     *
     * @param player is the player to compare to this
     *
     * @return true if the player checked is equal to this, false otherwise
     */
    public boolean equals(Player player) {
        return player.getName().equals(this.name);
    }

    /**
     * Check if the name is equal to the string given
     *
     * @param playerName is the player name to compare to this
     *
     * @return true if the player checked is equal to this, false otherwise
     */
    public boolean equals(String playerName) {
        return playerName.equals(this.name);
    }
}