package util;

import Exceptions.parser.BadJSONFormatException;
import Model.entities.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Parser {
    JSONObject file;
    String path;

    public Parser(String path) throws IOException, ParseException {
        this.path = path;
        Object file = new JSONParser().parse(new FileReader(path));
        this.file = (JSONObject) file;
    }

    public String getPath() {
        return path;
    }

    public static JSONObject getObject(String objectName, JSONObject file) throws BadJSONFormatException {
        JSONObject value = (JSONObject) file.get(objectName);
        if (value == null) {
            throw new BadJSONFormatException(objectName + " couldn't be found");
        } else {
            return value;
        }
    }
    public JSONObject getObject(String objectName) throws BadJSONFormatException {
        return getObject(objectName, this.file);
    }

    public static JSONArray getArray(String arrayName, JSONObject file) throws BadJSONFormatException {
        JSONArray value = (JSONArray) file.get(arrayName);
        if (value == null) {
            throw new BadJSONFormatException(arrayName + " couldn't be found");
        } else {
            return value;
        }
    }
    public JSONArray getArray(String arrayName) throws BadJSONFormatException {
        return Parser.getArray(arrayName, this.file);
    }

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
    public int[] getIntArray(String intArrayName) throws BadJSONFormatException {
        return getIntArray(intArrayName, this.file);
    }

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
    public int getInt(String intName) throws BadJSONFormatException {
        return getInt(intName, this.file);
    }

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

    public Card.Type[] getTypesArray(String arrayName) throws BadJSONFormatException {
        return getTypesArray(arrayName, this.file);
    }
}


