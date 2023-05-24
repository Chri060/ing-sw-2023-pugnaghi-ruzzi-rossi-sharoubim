package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.GroupCommonObjective.GroupCommonObjective;

import java.util.List;

public class CommonObjective5 extends GroupCommonObjective {
    public CommonObjective5(int ID) {
        super(ID, 4);
    }

    //TODO implementare
    @Override
    public boolean verify(Shelf shelf) {
        List<Integer> groups = shelf.getAdjacentGroupsSizes();
        return groups.stream().mapToInt(x -> x / 4).sum() >= 4;
    }
}
