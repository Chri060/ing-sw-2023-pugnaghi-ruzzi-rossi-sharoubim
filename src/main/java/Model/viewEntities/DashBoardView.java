package Model.viewEntities;

import Model.entities.Card;
import util.PlanarCoordinate;

import java.util.List;

/**
 * Class that implements the view that represent the dashboard
 */
public class DashBoardView {

    public Card[][] dashboard;

    /**
     * Construct the view with the given dashboard
     *
     * @param dashboard is the array of Card representing the dashboard
     */
    public DashBoardView(Card[][] dashboard) {
        this.dashboard = dashboard;
    }

    /**
     * Checks if it is possible to withdraw from a given list of coordinate
     *
     * @param coordinateList is the list of PlanarCoordinates to check
     *
     * @return true if the coordinates given are correct, false otherwise
     */
    public boolean canWithdraw(List<PlanarCoordinate> coordinateList) {
        if (coordinateList == null || coordinateList.size() == 0) return false;
        if (!areAlignedAndNear(coordinateList)) return false;
        for (PlanarCoordinate coordinate : coordinateList) {
            if (dashboard[coordinate.getRow()][coordinate.getColumn()] == null || !isDrawable(coordinate)) return false;
        }
        return true;
    }

    /**
     * Checks if the coordinates given are aligned and near
     *
     * @param coordinateList is the list of coordinates from where to withdraw
     *
     * @return true if the coordinates are correct, false otherwise
     */
    private boolean areAlignedAndNear(List<PlanarCoordinate> coordinateList)  {
        if (coordinateList.size() == 1) return true;
        boolean horizontal = coordinateList.stream().map(x -> x.getColumn()).distinct().count() > 1;
        boolean vertical = coordinateList.stream().map(x -> x.getRow()).distinct().count() > 1;
        if ((horizontal && vertical) || (!horizontal && !vertical)) return false;

        List<Integer> coordinateIntList;
        if (vertical) coordinateIntList = coordinateList.stream().map(x -> x.getRow()).toList();
        else coordinateIntList = coordinateList.stream().map(x -> x.getColumn()).toList();
        return areNear(coordinateIntList);
    }

    /**
     * Used by the method areAlignedAndNear to check if the coordinates are connected
     *
     * @param coordinateList is the list of coordinates to check
     *
     * @return true if the coordinates are connected, false otherwise
     */
    private boolean areNear(List<Integer> coordinateList) {
        boolean areNear = coordinateList.stream().sorted().
                reduce((a, b) -> (a - b == - 1) ? b : - 2).get() >= 0;
        return areNear;
    }

    /**
     * Checks if the card from the given coordinate is withdrawable or not
     *
     * @param coordinate is the coordinate to check
     *
     * @return true if the card is withdrawable, false otherwise
     */
    private boolean isDrawable(PlanarCoordinate coordinate) {
        if (coordinate.getRow() == this.dashboard.length - 1 || coordinate.getColumn() == this.dashboard[0].length - 1
                || coordinate.getRow() == 0 || coordinate.getColumn() == 0) return true;
        if (dashboard[coordinate.getRow()][coordinate.getColumn() + 1] == null) return true;
        if (dashboard[coordinate.getRow()][coordinate.getColumn() - 1] == null) return true;
        if (dashboard[coordinate.getRow() + 1][coordinate.getColumn()] == null) return true;
        if (dashboard[coordinate.getRow() - 1][coordinate.getColumn()] == null) return true;
        return false;
    }
}