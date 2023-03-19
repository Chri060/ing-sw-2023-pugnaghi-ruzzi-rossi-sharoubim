package Model;

import java.awt.*;
import java.util.Arrays;

public class PrivateObjective {
	final int cards = 6;
	
	final int[] objectiveOneCoordinates = new int[] { 5 ,0, 5, 2, 4, 4, 3, 3, 2, 1, 0, 2 };
	final Cards[] objectiveOneItems = new Cards[] {Cards.PLAN, Cards.FRAM, Cards.CATS, Cards.BOOK, Cards.GAME, Cards.TROP };
	final int[] objectiveTwoCoordinates = new int[] { 4, 1, 3, 0, 3, 2, 2, 4, 1, 3, 0, 4 };
	final Cards[] objectiveTwoItems = new Cards[] {Cards.PLAN, Cards.CATS, Cards.GAME, Cards.BOOK, Cards.TROP, Cards.FRAM };
	final int[] objectiveThreeCoordinates = new int[] { 4, 0, 4, 3, 3, 2, 2, 1, 2, 4, 0, 0 };
	final Cards[] objectiveThreeItems = new Cards[] {Cards.FRAM, Cards.GAME, Cards.PLAN, Cards.CATS, Cards.TROP, Cards.BOOK };
	final int[] objectiveFourCoordinates = new int[] { 5, 4, 3, 0, 3, 2, 2, 3, 1, 1, 1, 2 };
	final Cards[] objectiveFourItems = new Cards[] {Cards.GAME, Cards.TROP, Cards.FRAM, Cards.PLAN, Cards.BOOK, Cards.CATS };
	final int[] objectiveFiveCoordinates = new int[] { 4, 1, 2, 1, 2, 2, 1, 4, 0, 0, 0, 3 };
	final Cards[] objectiveFiveItems = new Cards[] {Cards.TROP, Cards.FRAM, Cards.BOOK, Cards.PLAN, Cards.GAME, Cards.CATS };
	final int[] objectiveSixCoordinates = new int[] { 5, 2, 5, 4, 3, 3, 1, 1, 1, 3, 0, 0 };
	final Cards[] objectiveSixItems = new Cards[] {Cards.TROP, Cards.CATS, Cards.BOOK, Cards.GAME, Cards.FRAM, Cards.PLAN };
	final int[] objectiveSevenCoordinates = new int[] { 5, 0, 4, 3, 3, 1, 2, 0, 1, 4, 0, 2 };
	final Cards[] objectiveSevenItems = new Cards[] { Cards.CATS, Cards.FRAM, Cards.PLAN, Cards.TROP, Cards.GAME, Cards.BOOK };
	final int[] objectiveEightCoordinates = new int[] { 5, 4, 4, 1, 3, 2, 2, 0, 1, 3, 0, 3 };
	final Cards[] objectiveEightItems = new Cards[] { Cards.FRAM, Cards.CATS, Cards.TROP, Cards.PLAN, Cards.BOOK, Cards.GAME };
	final int[] objectiveNineCoordinates = new int[] { 5, 2, 3, 2, 2, 4, 1, 1, 1, 4, 0, 0 };
	final Cards[] objectiveNineItems = new Cards[] { Cards.GAME, Cards.CATS, Cards.BOOK, Cards.TROP, Cards.PLAN, Cards.FRAM };
	final int[] objectiveTenCoordinates = new int[] { 5, 4, 4, 1, 3, 0, 2, 3, 1, 1, 0, 3 };
	final Cards[] objectiveTenItems = new Cards[] { Cards.TROP, Cards.GAME, Cards.BOOK, Cards.CATS, Cards.FRAM, Cards.PLAN};
	final int[] objectiveElevenCoordinates = new int[] { 5, 2, 4, 1, 3, 0, 2, 2, 1 ,4, 0, 3 };
	final Cards[] objectiveElevenItems = new Cards[] { Cards.PLAN, Cards.BOOK, Cards.GAME,Cards.FRAM, Cards.CATS, Cards.TROP };
	final int[] objectiveTwelveCoordinates = new int[] { 5, 2, 4, 1, 3, 2, 2, 3, 1, 4, 0, 0 };
	final Cards[] objectiveTwelveItems = new Cards[] { Cards.BOOK, Cards.PLAN, Cards.FRAM, Cards.TROP, Cards.GAME, Cards.CATS };

	private int corrispondence;
	private int[] coordinates;
	private Cards[] object;

	public PrivateObjective(int obiettivo) {
		corrispondence = 0;
		switch (obiettivo) {
			case 1 -> {
				coordinates = objectiveOneCoordinates;
				object = objectiveOneItems;
			}
			case 2 -> {
				coordinates = objectiveTwoCoordinates;
				object = objectiveTwoItems;
			}
			case 3 -> {
				coordinates = objectiveThreeCoordinates;
				object = objectiveThreeItems;
			}
			case 4 -> {
				coordinates = objectiveFourCoordinates;
				object = objectiveFourItems;
			}
			case 5 -> {
				coordinates = objectiveFiveCoordinates;
				object = objectiveFiveItems;
			}
			case 6 -> {
				coordinates = objectiveSixCoordinates;
				object = objectiveSixItems;
			}
			case 7 -> {
				coordinates = objectiveSevenCoordinates;
				object = objectiveSevenItems;
			}
			case 8 -> {
				coordinates = objectiveEightCoordinates;
				object = objectiveEightItems;
			}
			case 9 -> {
				coordinates = objectiveNineCoordinates;
				object = objectiveNineItems;
			}
			case 10 -> {
				coordinates = objectiveTenCoordinates;
				object = objectiveTenItems;
			}
			case 11 -> {
				coordinates = objectiveElevenCoordinates;
				object = objectiveElevenItems;
			}
			case 12 -> {
				coordinates = objectiveTwelveCoordinates;
				object = objectiveTwelveItems;
			}
		}
	}

	public int verify(Library library) {
		int row;
		int col;
		Cards[][] libraryCopy = library.getAsMatrix();
		for (int i = 0; i < 2*cards; i += 2) {
			row = coordinates[i];
			col = coordinates[i + 1];
			if (libraryCopy[row][col] == (object[i / 2])) {
				corrispondence++;
			}
		}
		return corrispondence;
	}

	public Cards[][] getPattern() {
		System.out.println(Arrays.toString(this.coordinates));
		System.out.println(Arrays.toString(this.object));
		return null;
	}
}
