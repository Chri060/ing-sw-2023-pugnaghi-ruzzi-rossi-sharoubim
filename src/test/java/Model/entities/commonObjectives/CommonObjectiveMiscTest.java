package Model.entities.commonObjectives;

import Model.entities.Point;
import Model.entities.Card;
import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

public class CommonObjectiveMiscTest
{
    Shelf shelf;
    List<Card> carte;

    @BeforeEach
    void setup(){
        Config.initialise(2);
        shelf = new Shelf();
        carte = new ArrayList<>();
    }

    @Test
    void MiscTest() {
        CommonObjective1 obj1 = new CommonObjective1(4);
       assert ( obj1.getID()==4);
       Point points=obj1.checkMaxPoints();
       assert (obj1.getMaxPoints().equals(points));
       points=obj1.checkMaxPoints();
        assert (obj1.getMaxPoints().equals(points));
        points=obj1.checkMaxPoints();
        assert (obj1.getMaxPoints().equals(points));
        assert (obj1.getNumberOfAvailableObjectives()==12);
    }



}
