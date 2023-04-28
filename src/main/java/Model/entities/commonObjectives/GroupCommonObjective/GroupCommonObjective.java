package Model.entities.commonObjectives.GroupCommonObjective;

import Exceptions.InvalidArgumentException;
import Model.entities.Card;
import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective;
import util.Checker;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

abstract public class GroupCommonObjective extends CommonObjective {

    public GroupCommonObjective(int ID) {
        super(ID);
    }

    //Returns an int matrix the same size as shelfMatrix in witch the value of the cell is the number of adjacent
    //cells that contains the same type of card of the cell


}