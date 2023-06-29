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
     * Creates a list of random number all unique.
     * Calling this method more than once will always give lists with no elements in common
     * until the clearCommonList methos is called
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
     * Creates a List of objects that must have a constructor that has only an integer as parameter such as classes created by giving
     * the constructor an ID
     *
     * @param size is the size of the list
     * @param bound is the maximum value in the list
     * @param className is a className object that will be contained in the result
     *
     * @return a list of className objects
     */
    public static <T extends Object> List<T> getUniqueInstances(int size, int bound, Class<T> className) {
        List<T> result = new ArrayList<>();
        if (size > 0) {
            List<Integer> IDList = getRandomUniqueIntList(size, bound);
            for (int i : IDList) {
                result.add(getInstance(className, i));
            }
        }
        return result;
    }

    /**
     * Construct an object given its className. This object must implement a constructor with only an integer as parameter
     * such as in ID
     *
     * @param className is a className object
     * @param ID is the ID given to the constructor
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
     * @return a list of CommonObjective of size according to the Config class
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