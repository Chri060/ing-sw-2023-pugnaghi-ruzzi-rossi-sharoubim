package Model;

import Exceptions.NotEnoughSpaceInColumnException;
import Exceptions.InvalidPickException;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private int points;
	private List<PrivateObjective> privateObj = new ArrayList<>();
	private Library library;


	public Player(String name, List<Integer> objID) {
		this.name = name;
		for (Integer integer : objID) {
			privateObj.add(new PrivateObjective(integer));
		}
		library = new Library();
		points = 0;
	}

	public int getPoints() {
		return points;
	}

	public String getName() {
		return name;
	}

	public Library getLibrary() {
		return new Library(this.library);
	}

	public int personalObjPoints() {
		int points = 0;
		for (PrivateObjective privateObjective : privateObj) {
			points += privateObjective.verify(library);
		}
		return points;
	}

	public void addPoints(int adder) {
		this.points =+ adder;
	}

	public List <Cards[][]> getObjPattern() {
		List <Cards[][]> l = new ArrayList<>();
		for(int i = 0; i < privateObj.size(); i++) {
			l.add(privateObj.get(i).getPattern());
		}
		return l;
	}

	public void insert(List<Cards> cardsList, int col) throws NotEnoughSpaceInColumnException, InvalidPickException {
		library.insert(cardsList, col);
	}
}
