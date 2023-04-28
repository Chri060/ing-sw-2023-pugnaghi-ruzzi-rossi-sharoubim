package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.GroupCommonObjective.GroupCommonObjective;

public class CommonObjective1 extends GroupCommonObjective {

    public CommonObjective1(int ID) {
        super(ID);
    }

    //TODO implementare
    @Override
    public boolean verify(Shelf shelf) {
        return false;
    }
}
