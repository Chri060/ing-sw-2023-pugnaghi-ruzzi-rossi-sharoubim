package util;

import Model.entities.commonObjectives.CommonObjective;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private static Random random = new Random();
    private static List<Integer> commonList = new ArrayList<>();

    public static List<Integer> getRandomUniqueIntList(int size, int bound) {
        List<Integer> IDList = new ArrayList<>();
        while (IDList.size() < size) {
            int ID = random.nextInt(bound);
            if (!commonList.contains(ID)) {
                commonList.add(ID);
                IDList.add(ID);
            }
        }
        return IDList;
    }
    public static void clearCommonList() {
        commonList.clear();
    }

    public static List<Integer> getRandomIntList(int size, int bound) {
        List<Integer> intList = new ArrayList<>();
        while (intList.size() < size) {
            int ID = random.nextInt(bound);
            if (!intList.contains(ID)) {
                intList.add(ID);
            }
        }
        return intList;
    }


    //Returns a list of className objects, those objects need to implement a constructor with only an int parameter (ID)
    //size and bound specify how many object are in the list and the ranges of the IDs (0 to #IDs - 1)
    public static <T extends Object> List<T> getUniqueInstances(int size, int bound, Class<T> className) {
        List<T> result = new ArrayList<>();
        List<Integer> IDList = getRandomUniqueIntList(size, bound);
        for (int i : IDList) {
            result.add(getInstance(className, i));
        }
        return result;
    }

    //Returns an Instance of className objects, that object needs to implement a constructor wiht only and int parameter (ID)
    public static <T extends Object> T getInstance(Class<T> className, int ID) {
        T result = null;
        try {
            Constructor<T> constructor = className.getConstructor(int.class);
            result = constructor.newInstance(ID);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Class or Constructor with int parameter not found. If they exist the ID might be invalid");
        }
        return result;
    }

    public static List<CommonObjective> getCommonObjectives(int size, int bound){
        List<CommonObjective> result = new ArrayList<>();
        List<Integer> IDList =
                getRandomUniqueIntList(Config.getNumberOfCommonObjectives(), CommonObjective.getNumberOfAvailableObjectives());
        try {
            for (Integer i : IDList) {
                String classString = "Model.entities.commonObjectives.CommonObjective" + (i + 1);
                Class<?> className = Class.forName(classString);
                CommonObjective obj = (CommonObjective) getInstance(className, i + 1);
                result.add(obj);
            }
        } catch (ClassNotFoundException e) {}
        clearCommonList();
        return result;
    }

}
