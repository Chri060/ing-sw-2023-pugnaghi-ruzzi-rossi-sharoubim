package Model.viewEntities;

import Model.entities.Card;
import util.Checker;
import util.PlanarCoordinate;

import java.util.List;

public class DashBoardView {

    public Card[][] dashboard;

    public DashBoardView(Card[][] dashboard) {
        this.dashboard = dashboard;
    }
    public boolean canWithdraw(List<PlanarCoordinate> coordinateList) {

        if (coordinateList == null || coordinateList.size() == 0) {
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
        if (coordinate.getRow() == this.dashboard.length - 1 || coordinate.getColumn() == this.dashboard[0].length - 1
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

}
