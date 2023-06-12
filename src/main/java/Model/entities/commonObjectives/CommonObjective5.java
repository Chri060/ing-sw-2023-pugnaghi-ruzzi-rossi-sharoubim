package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.GroupCommonObjective.GroupCommonObjective;

import java.util.List;

/**
 * Class that implements the common objective five
 */
public class CommonObjective5 extends GroupCommonObjective {

    /**
     * Construct the common objective five
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective5(int ID) {
        super(ID, 4);
    }

    /**
     * Verifies if the common objective is achieved or not
     *
     * @param shelf is the shelf that is going to be checked
     *
     * @return true if the common objective is verified, false otherwise
     */
    @Override
    public boolean verify(Shelf shelf) {
        List<Integer> groups = shelf.getAdjacentGroupsSizes();
        return groups.stream().mapToInt(x -> x / 4).sum() >= 4;
    }
}