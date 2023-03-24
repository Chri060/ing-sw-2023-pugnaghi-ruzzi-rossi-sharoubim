package Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonObjectiveFactory {

    public CommonObjectiveFactory () {

    }

    public List<CommonObjective> chosenObjective() {
        int numOfObj = 0;
        int objToDraw = 0;
        try {
            Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/CommonObjective.json"));
            JSONObject jsonObject = (JSONObject) file;
            numOfObj = ((Long) jsonObject.get("numOfObj")).intValue();
            objToDraw = ((Long) jsonObject.get("objToDraw")).intValue();
        }
        catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Integer> objID = new ArrayList<>();
        ArrayList<Integer> num = new ArrayList<Integer>();
        for (int i = 1; i <= numOfObj; i++) {
            num.add(i);
            Collections.shuffle(num);
        }
        for (int i = 0; i < objToDraw; i++) {
            objID.add(num.get(i));
        }
        List<CommonObjective> objectives = new ArrayList<>();
        for (int i = 0; i < objID.size(); i++) {
            switch (objID.get(i)) {
                case 1 -> objectives.add(new CommonObjOne());
                case 2 -> objectives.add(new CommonObjTwo());
                case 3 -> objectives.add(new CommonObjThree());
                case 4 -> objectives.add(new CommonObjFour());
                case 5 -> objectives.add(new CommonObjFive());
                case 6 -> objectives.add(new CommonObjSix());
                case 7 -> objectives.add(new CommonObjSeven());
                case 8 -> objectives.add(new CommonObjEight());
                case 9 -> objectives.add(new CommonObjNine());
                case 10 -> objectives.add(new CommonObjTen());
                case 11 -> objectives.add(new CommonObjEleven());
                case 12 -> objectives.add(new CommonObjTwelve());
                default -> { /*TODO: exception*/ }
            }
        }
        return objectives;
    }


}
