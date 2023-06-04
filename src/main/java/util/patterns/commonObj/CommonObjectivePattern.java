package util.patterns.commonObj;

import Model.entities.Card;
import util.Iterators.Iterable;
import util.Iterators.Iterator;
import util.Iterators.PatternIterator;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;
/**
 * Abstract Class to implement a common objective pattern
 * Includes many methods that can be overridden if needed*/
public abstract class CommonObjectivePattern implements Iterable {

    protected boolean[][] pattern;

    protected int patternLength;

    /**
     * Returns a PatternIterator to iterate on the pattern*/
    public Iterator getIterator() {
        return new PatternIterator(pattern);
    }
    /**
     * Returns the row size of the pattern*/
    protected int getRowLength() {
        return pattern.length;
    }
    /**
     * Returns the column size of the pattern*/
    protected int getColumnLength() {
        return pattern[0].length;
    }

    /**
     * Returns how many times the given card matrix satisfies the pattern with at most maxDifferentTypes different types of cards
     * NOTE: each time the pattern can be completed by different types of cards but everytime they need to be less than
     * maxDifferentTypes different types of cards
     * NOTE: if the pattern is bigger than the shelf it will always return 0.
     * NOTE: if the pattern is not complete (in at least one of the pattern's cell there's no card).
     * the pattern won't be satisfied*/
    public int verifyWithSameType(Card[][] shelfMatrix, int maxDifferentTypes) {
        List<Card.Type> typeList = new ArrayList<>();
        Card.Type type;
        int hits = 0;
        Iterator patternIterator = this.getIterator();
        for (int i = 0; i + this.getRowLength() <= shelfMatrix.length; i++) {
            for (int j = 0; j + this.getColumnLength() <= shelfMatrix[0].length; j++) {
                patternIterator.reset();
                typeList.clear();
                while (!patternIterator.iterationCompleted()) {
                    int rowOffset = patternIterator.getActual().getRow();
                    int columnOffset = patternIterator.getActual().getColumn();
                    Card card = shelfMatrix[i + rowOffset][j + columnOffset];
                    if (card == null) {
                        break;
                    }
                    type = card.getType();
                    if (!verifyListMaxTypes(typeList, type, maxDifferentTypes)) {
                        break;
                    }
                    patternIterator.next();
                }
                if (patternIterator.iterationCompleted()) {
                    hits++;
                    removeFoundPattern(shelfMatrix, new PlanarCoordinate(i, j));
                }
            }
        }
        return hits;
    }

    /**
     * Checks that the given type and the types in the list are at most maxDifferentTypes different types.
     * If the type is not found it will be added to the list.
     * NOTE the list can't contain the same type twice*/
    private boolean verifyListMaxTypes(List<Card.Type> typeList, Card.Type type, int maxDifferentTypes) {
        if (!typeList.contains(type)) {
            typeList.add(type);
        }
        return typeList.size() <= maxDifferentTypes;
    }
    /**
     * Removes completely the tiles from shelfMatrix following the pattern starting fromm offset coordinates
     */
    private void removeFoundPattern(Card[][] shelfMatrix, PlanarCoordinate offset) {
        int rowOffset = offset.getRow();
        int columnOffset = offset.getColumn();
        Iterator patternIterator = this.getIterator();
        while (!patternIterator.iterationCompleted()) {
            int row = rowOffset + patternIterator.getActual().getRow();
            int column = columnOffset + patternIterator.getActual().getColumn();
            shelfMatrix[row][column] = null;
            patternIterator.next();
        }
    }
    /**
    * Does verifyWithSameType but with the pattern mirrored vertically
    */
    public int verifyWithSameTypeMirrored(Card[][] shelfMatrix, int maxDifferentTypes) {
        this.mirrorPatternVertically();
        int result = this.verifyWithSameType(shelfMatrix, maxDifferentTypes);
        this.mirrorPatternVertically();
        return result;
    }
    /**
     * Mirrors the pattern vertically
     * NOTE: in order to not mess up the pattern remember to use this function always an even number times in your methods
     * */
    private void mirrorPatternVertically() {
        boolean temp;
        int rows = this.getRowLength();
        int columns = this.getColumnLength();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns / 2; j++) {
                temp = this.pattern[i][j];
                this.pattern[i][j] = this.pattern[i][columns - 1 - j];
                this.pattern[i][columns - 1 - j] = temp;
            }
        }
    }
    //Checks that the given card matrix satisfies this.pattern with at least minDifferentTypes different types of cards
    //NOTE each time the pattern can ce completed with a different set of types, as long as there's al least minDifferentTypes of cards
    //In any case the pattern found will be removed from shelfMatrix (sets cards to null).
    //NOTE if the pattern is bigger than the shelf it will always return 0
    //NOTE if the pattern is not complete (in at least one of the pattern's cell there's no card)
    //the pattern won't be satisfied
    public int verifyWithDifferentType(Card[][] shelfMatrix, int minDifferentTypes) {
        List<Card.Type> typeList = new ArrayList<>();
        Card.Type type;
        int hits = 0;
        Iterator patternIterator = this.getIterator();
        for (int i = 0; i + this.getRowLength() <= shelfMatrix.length; i++) {
            for (int j = 0; j + this.getColumnLength() <= shelfMatrix[0].length; j++) {
                patternIterator.reset();
                typeList.clear();
                while (!patternIterator.iterationCompleted()) {
                    int rowOffset = patternIterator.getActual().getRow();
                    int columnOffset = patternIterator.getActual().getColumn();
                    Card card = shelfMatrix[i + rowOffset][j + columnOffset];
                    if (card == null) {
                        break;
                    }
                    type = card.getType();
                    verifyListMinTypes(typeList, type, minDifferentTypes);
                    patternIterator.next();
                }
                if (patternIterator.iterationCompleted() && verifyListMinTypes(typeList, null, minDifferentTypes)) {
                    hits++;
                    removeFoundPattern(shelfMatrix, new PlanarCoordinate(i, j));
                }
            }
        }
        return hits;
    }
    //Checks that the given type and the types in the list are at least minDifferentTypes different types
    //If the type is not found it will be added to the list
    //NOTE the list can't contain the same type twice
    protected boolean verifyListMinTypes(List<Card.Type> typeList, Card.Type type, int minDifferentTypes) {
        if (type != null && !typeList.contains(type)) {
            typeList.add(type);
        }
        return typeList.size() >= minDifferentTypes;
    }

    protected void invertPattern() {
        for (int i = 0; i < this.getRowLength(); i++) {
            for (int j = 0; j < this.getColumnLength(); j++) {
                this.pattern[i][j] = !this.pattern[i][j];
            }
        }
        this.patternLength = this.getRowLength() * this.getColumnLength() - this.patternLength;
    }

    /**
     * Returns true if the given matrix contains cards of any type only in the pattern
     * For example if the pattern is a stair pattern checks that cards form the stair and all other spaces are empty
     * NOTE this method will also check for the pattern mirrored vertically. If you don't want that you can use the Core version
     */
    public boolean verifyPatternAndAntiPattern(Card[][] shelfMatrix) {
        boolean result;
        result = verifyPatternAndAntiPatternCore(shelfMatrix);
        mirrorPatternVertically();
        result = result || verifyPatternAndAntiPatternCore(shelfMatrix);
        mirrorPatternVertically();
        return result;
    }

    /**
     * Returns true if the given matrix contains cards of any type only in the pattern
     * For example if the pattern is a stair pattern checks that cards form the stair and all other spaces are empty
     * NOTE this method won't check for the pattern mirrored vertically. If you do want that you can use the not Core version
     */
    public boolean verifyPatternAndAntiPatternCore(Card[][] shelfMatrix) {
        Iterator patternIterator = this.getIterator();
        invertPattern();
        Iterator antiPatternIterator = this.getIterator();
        invertPattern();

        for (int i = 0; i + this.getRowLength() <= shelfMatrix.length; i++) {
            for (int j = 0; j + this.getColumnLength() <= shelfMatrix[0].length; j++) {
                patternIterator.reset();
                antiPatternIterator.reset();
                while (!patternIterator.iterationCompleted()) {
                    int rowOffset = patternIterator.getActual().getRow();
                    int columnOffset = patternIterator.getActual().getColumn();
                    if (shelfMatrix[i + rowOffset][j + columnOffset] == null) {
                        break;
                    }
                    patternIterator.next();
                }
                while (!antiPatternIterator.iterationCompleted()) {
                    int rowOffset = antiPatternIterator.getActual().getRow();
                    int columnOffset = antiPatternIterator.getActual().getColumn();
                    if (shelfMatrix[i + rowOffset][j + columnOffset] != null) {
                        break;
                    }
                    antiPatternIterator.next();
                }
                if (patternIterator.iterationCompleted() && antiPatternIterator.iterationCompleted()) {
                    //As the pattern does not define an empty row on its top we need to do this check manually
                    //If the pattern doesn't start from the first row we check that the above row is empty
                    if (i == 0) {
                        return true;
                    }
                    else {
                        int k;
                        for (k = 0; k < this.getRowLength(); k++) {
                            if (shelfMatrix[i -1][j + k] != null) {
                                break;
                            }
                        }
                        if (k == this.getRowLength()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the maximum amount of times a pattern can be completed with the same type of card
     * Only use this method for patterns that can't be rotated such as columns and rows.
     * For symmetric patterns such as squares or Xes use the not Core version as this method might not find the maximum appearance value
     * */
    public int verifyPatternWithOneCardCore(Card[][] shelfMatrix) {
        int hits;
        int maxHits = 0;
        Iterator patternIterator = getIterator();
        for (Card.Type type : Card.Type.values()) {
            hits = 0;
            for (int i = 0; i + this.getRowLength() <= shelfMatrix.length; i++) {
                for (int j = 0; j + this.getColumnLength() <= shelfMatrix[0].length; j++) {
                    patternIterator.reset();
                    while (!patternIterator.iterationCompleted()) {
                        int rowOffset = patternIterator.getActual().getRow();
                        int columnOffset = patternIterator.getActual().getColumn();
                        Card card = shelfMatrix[i + rowOffset][j + columnOffset];
                        if (card == null || !card.equalsType(type)) {
                            break;
                        }
                        patternIterator.next();
                    }
                    if (patternIterator.iterationCompleted()) {
                        hits++;
                        removeFoundPattern(shelfMatrix, new PlanarCoordinate(i, j));
                    }
                }
            }
            maxHits = Math.max(maxHits, hits);
        }
        return maxHits;
    }
    /**
     * Does verifyPatternWithOneCardCore but As some pattern might be overlapped and exploration from the left up corner
     * might not find the max amount of appearance of that pattern.
     * To fix this method rotate the matrix 4 times to explore all the possible combinations
     * NOTE for patterns that are not symmetric by 90° rotations such as columns and rows do not use this method
     * */
    public int verifyPatternWithOneCard(Card[][] shelfMatrix) {
        //Will be rotated
        Card[][] temp;
        int max = 0;

        for (int i = 0; i < 4; i++) {
            temp = cloneMatrix(shelfMatrix);
            max = Math.max(max, verifyPatternWithOneCardCore(temp));
            shelfMatrix = rotateMatrix(shelfMatrix);
        }
        return max;
    }
    private Card[][] rotateMatrix(Card[][] matrix) {
        //rows and columns are the number of columns and rows of matrix (notice they're swapped)
        int rows = matrix[0].length;
        int columns = matrix.length;

        Card[][] result = new Card[rows][columns];

        //Transposed matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        //Swaps columns

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns / 2; j++) {
                Card temp = result[i][j];
                result[i][j] = result[i][columns - 1 - j];
                result[i][columns - 1 - j] = temp;
            }
        }

        return result;
    }
    private Card[][] cloneMatrix(Card[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        Card[][] result = new Card[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j];
            }
        }
        return result;
    }
}