package Model.entities;

import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;
import util.Checker;
import util.Config;
import util.Iterators.*;
import util.Iterators.Iterable;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent the dashboard
 */
public class Dashboard implements Iterable {

    private int rows;
    private int columns;
    private Card[][] dashboard;
    private boolean[][] taps;

    /**
     * Construct an empty dashboard and the taps matrix
     * The dimension and structure are set before the game
     */
    public Dashboard() {
        this.rows = Config.getDashboardRows();
        this.columns = Config.getDashboardColumns();
        dashboard = new Card[rows][columns];
        taps = Config.getDashboardPattern();
    }

    /**
     * @return the number of rows of the dashboard
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return the number of columns of the dashboard
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Given a list of coordinates return true only if all coordinates have a card
     * and at least a side of the card is free, so if each card in the list is withdrawable
     *
     * @param coordinateList is a list that contains coordinates
     *
     * @return true if the selected cards are withdrawable, false otherwise
     */
    public boolean canWithdraw(List<PlanarCoordinate> coordinateList) {
        if (coordinateList == null || coordinateList.size() == 0) {
            return false;
        }
        if (!Checker.dashboardCoordinatesAreValid(coordinateList)) {
            return false;
        }
        if (!areAlignedAndNear(coordinateList)) {
            return false;
        }
        for (PlanarCoordinate coordinate : coordinateList) {
            if (dashboard[coordinate.getRow()][coordinate.getColumn()] == null || !isDrawable(coordinate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given a list of coordinates it removes the cards from the dashboard
     * If you want to withdraw is better to check if all coordinates are correct using canWithdraw
     *
     * @param coordinateList is a list that contains coordinates
     *
     * @return the list of card withdrawn
     */
    public List<Card> withdraw(List<PlanarCoordinate> coordinateList) {
        List<Card> result = new ArrayList<>();
        for (PlanarCoordinate coordinate : coordinateList) {
            int row = coordinate.getRow();
            int column = coordinate.getColumn();
            result.add(dashboard[row][column]);
            dashboard[row][column] = null;
        }
        return result;
    }

    /**
     * Given a list of coordinates it checks if they are aligned and connected
     * It is used by the method canWithdraw
     *
     * @param coordinateList is a list that contains coordinates
     *
     * @return true if the coordinates are aligned and connected, false otherwise
     */
    private boolean areAlignedAndNear(List<PlanarCoordinate> coordinateList)  {
        if (coordinateList.size() == 1) {
            return true;
        }
        boolean horizontal = coordinateList.stream().map(x -> x.getColumn()).distinct().count() > 1;
        boolean vertical = coordinateList.stream().map(x -> x.getRow()).distinct().count() > 1;
        if ((horizontal && vertical) || (!horizontal && !vertical)) {
            return false;
        }
        List<Integer> coordinateIntList;
        if (vertical) {
            coordinateIntList = coordinateList.stream().map(x -> x.getRow()).toList();
        }
        else {
            coordinateIntList = coordinateList.stream().map(x -> x.getColumn()).toList();
        }
        return areNear(coordinateIntList);
    }

    /**
     * Given a list of coordinates it checks if they connected
     * It is used by the method areAlignedAndNear
     *
     * @param coordinateList is a list that contains integer that represent the coordinates
     *
     * @return true if the coordinates are connected, false otherwise
     */
    private boolean areNear(List<Integer> coordinateList) {
        boolean areNear = coordinateList.stream().sorted().
                reduce((a, b) -> (a - b == - 1) ? b : - 2).get() >= 0;
        return areNear;
    }

    /**
     * Given a coordinate it checks if you can withdraw from the dashboard in the selected location
     * It is used by the method canWithdraw
     *
     * @param coordinate is a single coordinate
     *
     * @return true if it is possible to withdraw from the selected coordinate, false otherwise
     */
    private boolean isDrawable(PlanarCoordinate coordinate) {
        if (coordinate.getRow() == this.getRows() - 1 || coordinate.getColumn() == this.getColumns() - 1
            || coordinate.getRow() == 0 || coordinate.getColumn() == 0) {
            return true;
        }
        if (dashboard[coordinate.getRow()][coordinate.getColumn() + 1] == null) {
            return true;
        }
        if (dashboard[coordinate.getRow()][coordinate.getColumn() - 1] == null) {
            return true;
        }
        if (dashboard[coordinate.getRow() + 1][coordinate.getColumn()] == null) {
            return true;
        }
        if (dashboard[coordinate.getRow() - 1][coordinate.getColumn()] == null) {
            return true;
        }
        return false;
    }

    /**
     * It checks if the dashboard needs to be filled or not
     * The dashboard needs refill when the player can withdraw only one card
     *
     * @return true if the refill is needed, false otherwise
     */
    public boolean needsRefill() {
        try {
            Iterator matrixIterator = this.getIterator();
            while (!matrixIterator.iterationCompleted()) {
                PlanarCoordinate actual = matrixIterator.getActual();
                if (this.checkCell(actual) != null) {
                    Iterator crossIterator = new CrossIterator(actual);
                    while (!crossIterator.iterationCompleted()) {
                        PlanarCoordinate next = crossIterator.getActual();
                        if (Checker.dashboardCoordinatesAreValid(next) && this.checkCell(next) != null) {
                            return false;
                        }
                        crossIterator.next();
                    }
                }
                matrixIterator.next();
            }
        } catch (InvalidArgumentException e) {/*Never thrown*/}
        return true;
    }

    /**
     * Draws cards from the bag and then put them in the dashboard until the bag is empty or the dashboard is filled
     *
     * @param bag is the bag from where to draw the cards
     *
     * @throws InvalidActionException on invalid action
     */
    public void refill(Bag bag) throws InvalidActionException {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (taps[i][j] && dashboard[i][j] == null) {
                    dashboard[i][j] = bag.getCard();
                }
            }
        }
    }

    /**
     * Checks if the coordinate given is inside the dashboard and if the cell is usable
     *
     * @param planarCoordinate is a single coordinate
     *
     * @return true if the coordinate is inside the dashboard and usable, false otherwise
     */
    public boolean isCellAvailable(PlanarCoordinate planarCoordinate) {
        if (!Checker.dashboardCoordinatesAreValid(planarCoordinate)) {
            return false;
        }
        return taps[planarCoordinate.getRow()][planarCoordinate.getColumn()];
    }

    /**
     * Checks if the coordinate given is inside the dashboard and what card is inside it
     *
     * @param planarCoordinate is a single coordinate
     *
     * @return the card that is in the selected dashboard coordinate, null if there is none
     *
     * @throws InvalidArgumentException on invalid argument
     */
    public Card checkCell(PlanarCoordinate planarCoordinate) throws InvalidArgumentException {
        if (planarCoordinate == null) {
            throw new InvalidArgumentException("Coordinates cannot be null");
        }
        if (!Checker.dashboardCoordinatesAreValid(planarCoordinate)) {
            throw new InvalidArgumentException("Coordinates are out of bounds");
        }
        return dashboard[planarCoordinate.getRow()][planarCoordinate.getColumn()];
    }

    /**
     * Return the dashboard actual situation as a matrix
     *
     * @return array of cards with the actual state of the dashboard
     */
    public Card[][] asMatrix() {
        Card[][] result = new Card[this.getRows()][this.getColumns()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (dashboard[i][j] != null) {
                    result[i][j] = new Card(dashboard[i][j].getType(), dashboard[i][j].getId());
                }
            }
        }
        return result;
    }

    /**
     * @return the iterator of the taps matrix
     */
    @Override
    public Iterator getIterator() {
        return new PatternIterator(taps);
    }
}