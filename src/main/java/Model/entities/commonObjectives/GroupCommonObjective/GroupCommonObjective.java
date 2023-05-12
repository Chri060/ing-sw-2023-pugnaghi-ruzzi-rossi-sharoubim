package Model.entities.commonObjectives.GroupCommonObjective;

import Exceptions.InvalidArgumentException;
import Model.entities.Card;
import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective;
import util.Checker;
import util.Observer;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

abstract public class GroupCommonObjective extends CommonObjective {

    private int groupSize;

    public GroupCommonObjective(int ID, int groupSize) {
        super(ID);
        this.groupSize = groupSize;
    }


}
