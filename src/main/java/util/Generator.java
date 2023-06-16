package util;

import Model.entities.commonObjectives.CommonObjective;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class used to generate random elements like integer
 */
public class Generator {
    private static Random random = new Random();
    private static List<Integer> commonList = new ArrayList<>();

    /**
     * Creates a list of random number all unique
     *
     * @param size is the size of the list
     * @param bound is the maximum value in the list
     *
     * @return a list of numbers
     */
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

    /**
     * Clears the list created by the method getRandomUniqueIntList
     */
    public static void clearCommonList() {
        commonList.clear();
    }

    /**
     * Creates a list of random number
     *
     * @param size is the size of the list
     * @param bound is the maximum value in the list
     *
     * @return a list of numbers
     */
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

    /**
     * Creates a className object with only an int parameter (ID)
     *
     * @param size is the size of the list
     * @param bound is the maximum value in the list
     * @param className is a className object
     *
     * @return a list of className objects
     */
    public static <T extends Object> List<T> getUniqueInstances(int size, int bound, Class<T> className) {
        List<T> result = new ArrayList<>();
        List<Integer> IDList = getRandomUniqueIntList(size, bound);
        for (int i : IDList) {
            result.add(getInstance(className, i));
        }
        return result;
    }

    /**
     * Construct a className object
     *
     * @param className is a className object
     * @param ID is the D of the className object
     *
     * @return an instance of className object
     */
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

    /**
     * @return a list of CommonObjective
     */
    public static List<CommonObjective> getCommonObjectives(){
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
        } catch (ClassNotFoundException e) {
            /*do nothing*/
        }
        clearCommonList();
        return result;
    }
}