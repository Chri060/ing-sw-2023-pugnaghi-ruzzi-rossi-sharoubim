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

public class Dashboard implements Iterable {

    private int rows;
    private int columns;
    private Card[][] dashboard;
    private boolean[][] taps;

    public Dashboard() {
        this.rows = Config.getDashboardRows();
        this.columns = Config.getDashboardColumns();

        dashboard = new Card[rows][columns];

        taps = Config.getDashboardPattern();
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
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
    private boolean areNear(List<Integer> coordinateList) {
        boolean areNear = coordinateList.stream().sorted().
                reduce((a, b) -> (a - b == - 1) ? b : - 2).get() >= 0;
        return areNear;
    }
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
        /*
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (dashboard[i][j] != null) {
                    Iterator iterator = new CrossIterator(new PlanarCoordinate(i, j));
                    while (!iterator.iterationCompleted()) {
                        PlanarCoordinate next = iterator.getActual();
                        if (Checker.dashboardCoordinatesAreValid(next)) {
                            if (dashboard[next.getRow()][next.getColumn()] != null) {
                                return false;
                            }
                        }
                        iterator.next();
                    }
                }
            }
        }
        return true;*/
    }
    public void refill(Bag bag) throws InvalidActionException {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (taps[i][j] && dashboard[i][j] == null) {
                    dashboard[i][j] = bag.getCard();
                }
            }
        }
    }
    public boolean isCellAvailable(PlanarCoordinate planarCoordinate) {
        if (!Checker.dashboardCoordinatesAreValid(planarCoordinate)) {
            return false;
        }
        return taps[planarCoordinate.getRow()][planarCoordinate.getColumn()];
    }
    public Card checkCell(PlanarCoordinate planarCoordinate) throws InvalidArgumentException {
        if (planarCoordinate == null) {
            throw new InvalidArgumentException("Coordinates cannot be null");
        }
        if (!Checker.dashboardCoordinatesAreValid(planarCoordinate)) {
            throw new InvalidArgumentException("Coordinates are out of bounds");
        }
        return dashboard[planarCoordinate.getRow()][planarCoordinate.getColumn()];
    }
    public Card[][] asMatrix() {
        Card[][] result = new Card[this.getRows()][this.getColumns()];

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                result[i][j] = this.dashboard[i][j];
            }
        }

        return result;
    }
    @Override
    public Iterator getIterator() {
        //return new MatrixIterator(this.rows, this.columns);
        return new PatternIterator(taps);
    }
}
