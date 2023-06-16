package util;

import Exceptions.parser.BadJSONFormatException;
import Model.entities.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Class that parse the program to the JSON files
 */
public class Parser {
    JSONObject file;
    String path;

    /**
     * Constructor for the parser
     *
     * @param path is where the method has to check for the files
     *
     * @throws IOException on input/output exception
     * @throws ParseException on parsing exception
     */
    public Parser(String path) throws IOException, ParseException {
        this.path = path;
        Object file = new JSONParser().parse(new FileReader(path));
        this.file = (JSONObject) file;
    }

    /**
     * @return the path used to reach the JSON files
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the JSONObject requested
     *
     * @param objectName is the name of the JSONObject requested
     * @param file is the location where the JSONObject is supposed to be
     *
     * @return the JSONObject requested
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public static JSONObject getObject(String objectName, JSONObject file) throws BadJSONFormatException {
        JSONObject value = (JSONObject) file.get(objectName);
        if (value == null) {
            throw new BadJSONFormatException(objectName + " couldn't be found");
        } else {
            return value;
        }
    }

    /**
     * Returns the JSONObject requested
     *
     * @param objectName is the name of the JSONObject requested
     *
     * @return the JSONObject requested
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public JSONObject getObject(String objectName) throws BadJSONFormatException {
        return getObject(objectName, this.file);
    }

    /**
     * Returns an array from the given JSONObject
     *
     * @param arrayName is the name that is needed from the JSONObject
     * @param file is the file where the array is supposed to be in
     *
     * @return a JSONArray
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public static JSONArray getArray(String arrayName, JSONObject file) throws BadJSONFormatException {
        JSONArray value = (JSONArray) file.get(arrayName);
        if (value == null) {
            throw new BadJSONFormatException(arrayName + " couldn't be found");
        } else {
            return value;
        }
    }

    /**
     * Returns an array from the given JSONObject
     *
     * @param arrayName is the name that is needed from the JSONObject
     *
     * @return a JSONArray
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public JSONArray getArray(String arrayName) throws BadJSONFormatException {
        return Parser.getArray(arrayName, this.file);
    }

    /**
     * Returns an array from the given JSONObject
     *
     * @param intArrayName is the name that is needed from the JSONObject
     * @param file is the file where the array is supposed to be in
     *
     * @return a integer array
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public static int[] getIntArray(String intArrayName, JSONObject file) throws BadJSONFormatException {
        JSONArray jsonArray = Parser.getArray(intArrayName, file);

        int[] result = new int[jsonArray.size()];

        int i = 0;
        for (Object o : jsonArray) {
            try {
                result[i] = ((Long) o).intValue();
                i++;
            } catch (ClassCastException e) {
                throw new BadJSONFormatException(intArrayName + " is not an int array");
            }
        }

        return result;
    }

    /**
     * Returns an array from the given JSONObject
     *
     * @param intArrayName is the name that is needed from the JSONObject
     *
     * @return a integer array
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public int[] getIntArray(String intArrayName) throws BadJSONFormatException {
        return getIntArray(intArrayName, this.file);
    }

    /**
     * Returns an integer from the given JSONObject
     *
     * @param intName is the name that is needed from the JSONObject
     * @param file is the file where the array is supposed to be in
     *
     * @return a integer
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public static int getInt(String intName, JSONObject file) throws BadJSONFormatException {
        try {
            Long value = (Long) file.get(intName);
            if (value == null) {
                throw new BadJSONFormatException(intName + " couldn't be found");
            } else {
                return value.intValue();
            }
        } catch (ClassCastException e) {
            throw new BadJSONFormatException(intName + " is not an int");
        }
    }

    /**
     * Returns an integer from the given JSONObject
     *
     * @param intName is the name that is needed from the JSONObject
     *
     * @return a integer
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public int getInt(String intName) throws BadJSONFormatException {
        return getInt(intName, this.file);
    }

    /**
     * Returns an array of Card.Type from the given JSONObject
     *
     * @param arrayName is the name that is needed from the JSONObject
     * @param file is the file where the array is supposed to be in
     *
     * @return an array of Card.Type
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public static Card.Type[] getTypesArray(String arrayName, JSONObject file) throws BadJSONFormatException {
        JSONArray jsonArray = Parser.getArray(arrayName, file);

        Card.Type[] result = new Card.Type[jsonArray.size()];

        for ( int i = 0; i < result.length; i++) {
            try {
                result[i] = Card.Type.valueOf((String) jsonArray.get(i));
            } catch (ClassCastException e) {
                throw new BadJSONFormatException("Invalid Type");
            }
        }

        return result;
    }

    /**
     * Returns an array of Card.Type from the given JSONObject
     *
     * @param arrayName is the name that is needed from the JSONObject
     *
     * @return an array of Card.Type
     *
     * @throws BadJSONFormatException on JSON format exception
     */
    public Card.Type[] getTypesArray(String arrayName) throws BadJSONFormatException {
        return getTypesArray(arrayName, this.file);
    }
}