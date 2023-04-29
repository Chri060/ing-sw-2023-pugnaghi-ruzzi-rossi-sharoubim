package util.patterns.commonObj;

import Model.entities.Card;
import Model.entities.Shelf;
import util.Checker;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonObjectivePattern {

    protected boolean[][] pattern;
    protected int iteratorStatus;
   protected int patternLength;
    protected PlanarCoordinate iteratorCellStatus;

    protected int getRowLength() {
        return pattern.length;
    }
    protected int getColumnLength() {
        return pattern[0].length;
    }
    protected void restart() {
        for (int i = 0; i < this.getRowLength(); i++) {
            for (int j = 0; j < this.getColumnLength(); j++) {
                if (pattern[i][j]) {
                    iteratorCellStatus = new PlanarCoordinate(i, j);
                    iteratorStatus = 1;
                    return;
                }
            }
        }

    }
    protected boolean hasNext() {
        return this.iteratorStatus < this.patternLength;
    }
    protected PlanarCoordinate get() {
        return iteratorCellStatus;
    }
    protected void next() {
        if (!hasNext()) {
            return;
        }
        //Starts from last pattern cell visited
        int j = iteratorCellStatus.getColumn() + 1;

        //Checks for the next true value in the matrix -> updates iterator status
        for (int i = iteratorCellStatus.getRow(); i < this.getRowLength(); i++) {
            while (j < this.getColumnLength()) {
                if (pattern[i][j]) {
                    iteratorCellStatus = new PlanarCoordinate(i, j);
                    iteratorStatus++;
                    return;
                }
                j++;
            }
            j = 0;
        }
    }


    //Checks that the given card matrix satisfies this.pattern with at most maxDifferentTypes different types of cards
    //NOTE each time the pattern can be completed by different types of cards but everytime they need to be less than
    //maxDifferentTypes different types of cards
    //In any case the pattern found will be removed from shelfMatrix (sets cards to null).
    //NOTE if the pattern is bigger than the shelf it will always return 0
    //NOTE if the pattern is not complete (in at least one of the pattern's cell there's no card)
    //the pattern won't be satisfied
    public int verifyWithSameType(Card[][] shelfMatrix, int maxDifferentTypes) {
        List<Card.Type> typeList = new ArrayList<>();
        PlanarCoordinate helper;
        Card.Type type;
        int hits = 0;
        //Iterates in the shelf while there's enough space for the pattern
        for (int i = 0; i + this.getRowLength() <= shelfMatrix.length; i++) {
            for (int j = 0; j + this.getColumnLength() <= shelfMatrix[0].length; j++) {
                //Restarts internal pattern Iterator
                this.restart();
                //Clears types found in the previous exploration
                typeList.clear();
                //Iterates the pattern while it's not ended
                int k; //k will be used to determine if the pattern exploration was completed or not
                for (k = 0; k < this.patternLength; k++) {
                    //Gets offset of the actual cell of the pattern
                    helper = this.get();
                    int rowOffset = helper.getRow();
                    int columnOffset = helper.getColumn();
                    //Gets the type of the actual cell
                    Card card = shelfMatrix[i + rowOffset][j + columnOffset];
                    //If the cell is empty pattern cannot be satisfied
                    if (card == null) {
                        break;
                    }
                    //Gets type and checks
                    type = card.getType();
                    //If the typeList contains (including type) more than maxDifferentTypes the pattern can't be satisfied
                    if (!verifyListMaxTypes(typeList, type, maxDifferentTypes)) {
                        break;
                    }
                    //Otherwise keeps the exploration
                    else {
                        this.next();
                    }
                }
                //Once the exploration is done check if the pattern was completed or not
                if (k == this.patternLength) {
                    //if the exploration ended due to the pattern being fully explored
                    hits++;
                    //clears the cells that formed the pattern
                    removeFoundPattern(shelfMatrix, new PlanarCoordinate(i, j));
                }
            }
        }
        return hits;
    }
    //Checks that the given type and the types in the list are at most maxDifferentTypes different types
    //If the type is not found it will be added to the list
    //NOTE the list can't contain the same type twice
    private boolean verifyListMaxTypes(List<Card.Type> typeList, Card.Type type, int maxDifferentTypes) {
        if (!typeList.contains(type)) {
            typeList.add(type);
        }
        return typeList.size() <= maxDifferentTypes;
    }
    //Removes the tiles from shelfMatrix following this.pattern completely starting fromm offset coordinates
    private void removeFoundPattern(Card[][] shelfMatrix, PlanarCoordinate offset) {
        PlanarCoordinate planarCoordinate;
        int rowOffset = offset.getRow();
        int columnOffset = offset.getColumn();

        this.restart();
        for (int k = 0; k < this.patternLength; k++) {
            planarCoordinate = this.get();
            int row = rowOffset + planarCoordinate.getRow();
            int column = columnOffset + planarCoordinate.getColumn();
            shelfMatrix[row][column] = null;
            this.next();
            ;
        }
    }
    //Does verifyWithSameType but with the pattern mirrored vertically
    public int verifyWithSameTypeMirrored(Card[][] shelfMatrix, int maxDifferentTypes) {
        this.mirrorPatternVertically();
        int result = this.verifyWithSameType(shelfMatrix, maxDifferentTypes);
        this.mirrorPatternVertically();
        return result;
    }
    //Mirrors the pattern vertically
    //NOTE: in order to not mess up the pattern remember to use this function always an even number times in your methods
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
        PlanarCoordinate helper;
        Card.Type type;
        int hits = 0;

        //Iterates in the shelf while there's enough space for the pattern
        for (int i = 0; i + this.getRowLength() <= shelfMatrix.length; i++) {
            for (int j = 0; j + this.getColumnLength() <= shelfMatrix[0].length; j++) {
                //Restarts internal pattern Iterator
                this.restart();
                //Clears types found in the previous exploration
                typeList.clear();
                //iterates on the pattern
                int k;
                for (k = 0; k < this.patternLength; k++) {
                    //Gets offset of the actual cell of the pattern
                    helper = this.get();
                    int rowOffset = helper.getRow();
                    int columnOffset = helper.getColumn();
                    //gets the card to check
                    Card card = shelfMatrix[i + rowOffset][j + columnOffset];
                    //If the cell is empty pattern cannot be satisfied
                    if (card == null) {
                        break;
                    }
                    //Gets type and checks
                    type = card.getType();
                    //Checks if in the current exploration of the pattern minDifferentTypes types of cards were found already
                    if (verifyListMinTypes(typeList, type, minDifferentTypes)) {
                        //pattern satisfied
                        hits++;
                        //clears the cells that formed the pattern
                        removeFoundPattern(shelfMatrix, new PlanarCoordinate(i, j));
                        break;
                    }
                    else {
                        this.next();
                    }
                }
            }
        }
        return hits;
    }
    //Checks that the given type and the types in the list are at least minDifferentTypes different types
    //If the type is not found it will be added to the list
    //NOTE the list can't contain the same type twice
    private boolean verifyListMinTypes(List<Card.Type> typeList, Card.Type type, int minDifferentTypes) {
        if (!typeList.contains(type)) {
            typeList.add(type);
        }
        return typeList.size() >= minDifferentTypes;
    }

    //Returns true only if the pattern is satisfied with empty cells
    public boolean verifyEmptyPattern(Card[][] shelfMatrix) {
        this.invertPattern();
        this.restart();

        for (int k = 0; k < this.patternLength; k++) {
            PlanarCoordinate actual = this.get();
            int actualRow = actual.getRow();
            int actualColumn = actual.getColumn();
            if (shelfMatrix[actualRow][actualColumn] != null) {
                return false;
            }
            this.next();
        }
        this.invertPattern();

        return true;

    }

    //Returns true only if the mirrored pattern is satisfied with empty cells
    public boolean verifyEmptyPatternMirrored(Card[][] shelfMatrix) {
        this.mirrorPatternVertically();
        boolean result = verifyEmptyPattern(shelfMatrix);
        this.mirrorPatternVertically();
        return result;
    }
    private void invertPattern() {
        for (int i = 0; i < this.getRowLength(); i++) {
            for (int j = 0; j < this.getColumnLength(); j++) {
                this.pattern[i][j] = !this.pattern[i][j];
            }
        }
        this.patternLength = this.getRowLength() * this.getColumnLength() - this.patternLength;
    }

    //Returns the maximum amount of times a pattern can be completed with the same type of card
    public int patternWithOneCard(Card[][] shelfMatrix) {
        int hits;
        int maxHits = 0;
        //For each type of card
        for (Card.Type type : Card.Type.values()) {
            //for every cell of the shelf
            hits = 0;
            for (int i = 0; i + this.getRowLength() <= shelfMatrix.length; i++) {
                for (int j = 0; j + this.getColumnLength() <= shelfMatrix[0].length; j++) {
                    this.restart();
                    int k;
                    //checks if the pattern is satisfied
                    for (k = 0; k < this.patternLength; k++) {
                        PlanarCoordinate coordinate = this.get();
                        if (Checker.shelfCoordinatesAreValid(coordinate)) {
                            int row = i + coordinate.getRow();
                            int column = j + coordinate.getColumn();
                            Card card = shelfMatrix[row][column];
                            if (card == null || !card.equalsType(type)) {
                                break;
                            }
                        }
                        this.next();
                    }
                    if (k == this.patternLength) {
                        hits++;
                        removeFoundPattern(shelfMatrix, new PlanarCoordinate(i, j));
                    }
                }
            }
            maxHits = Math.max(maxHits, hits);
        }
        return maxHits;
    }
    //As some pattern might be overlapped and exploration from the left up corner might not find the max amount of appearance
    //of that pattern. To fix this method flips the matrix 4 times to explore all the possible combinations
    //NOTE for patterns that are not symmetric by 90° rotations such as columns and rows do not use this method
    public int verifyPatternWithOneCard(Card[][] shelfMatrix) {
        //Will be rotated
        Card[][] temp;
        int max = 0;

        for (int i = 0; i < 4; i++) {
            temp = cloneMatrix(shelfMatrix);
            max = Math.max(max, patternWithOneCard(temp));
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