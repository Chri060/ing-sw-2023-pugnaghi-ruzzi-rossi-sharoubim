package Model.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private Shelf shelf;
    private List<PrivateObjective> privateObjectives;
    private List<Point> points;

    public Player(String name, List<PrivateObjective> privateObjective) {
        this.name = name;
        this.shelf = new Shelf();
        this.privateObjectives = new ArrayList<>();
        this.privateObjectives.addAll(privateObjective);
        this.points = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Point getTotalPoints() {
        return new Point(this.getPoints().stream().mapToInt(x -> x.getValue()).sum(), name + "'s points");
    }

    public List<Integer> getPrivateObjectivesID() {
        return privateObjectives.stream().map(x -> x.getID()).toList();
    }

    public Shelf getShelf() {
        return shelf;
    }

    public List<Point> getPoints() {
        return points;
    }
    public void givePoint(Point point) {
        points.add(point);
    }
    public boolean hasAlreadyGotCommonPoints(int ID) {
        return points.stream().anyMatch(x -> x.getOrigin().equals("Common Objective "  + ID));
    }
    public List<Point> getPrivatePoint() {
        return this.privateObjectives.stream().map(x -> x.getMaxPoints(this.shelf)).toList();
    }
    public Point getTotalPrivatePoints() {
        return new Point(this.getPrivatePoint().stream().mapToInt(x -> x.getValue()).sum(), name + "'s private objectives points");
    }

    public boolean equals(Player player) {
        return player.getName().equals(this.name);
    }
    public boolean equals(String playerName) {
        return playerName.equals(this.name);
    }
}
