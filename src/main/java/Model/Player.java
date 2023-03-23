package Model;

import Exceptions.ColumFullException;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;

	private int points;

	private List<PrivateObjective> privateObj = new ArrayList<>();
	private Library library;
	public Player(String name, List<Integer> objID) {
		this.name = name;
		for (int i = 0; i < objID.size(); i++) {
			privateObj.add(new PrivateObjective(objID.get(i)));
		}
		library = new Library();
		points = 0;
	}

	//TODO: gestire punti (aggiunta)

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
		for(int i = 0; i < privateObj.size(); i++) {
			points += privateObj.get(i).verify(library);
		}
		return points;
	}
	//TODO: fino a quando non fixiamo get pattern avremo una lista con dentro tutti null
	public List <Cards[][]> getObjPattern() {
		List <Cards[][]> l = new ArrayList<>();
		for(int i = 0; i < privateObj.size(); i++) {
			l.add(privateObj.get(i).getPattern());
		}
		return l;
	}
	public void insert(List<Cards> cardsList, int col) throws ColumFullException {
		library.insert(cardsList, col);

	}
}
