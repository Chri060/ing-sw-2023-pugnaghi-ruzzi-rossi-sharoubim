package Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private int points;
	private List<PrivateObjective> privateObj = new ArrayList<>();
	private Library library;
	private Player(String name, PrivateObjective obj) {
		this.name = name;
		privateObj.add(obj);
		library = new Library();
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public Library getLibrary() {
		return new Library(this.library);
	}

	public int personalObjPoints() {
		int res = 0;
		for(int i = 0; i < privateObj.size(); i++) {
			res += privateObj.get(i).verify(library);
		}
		return res;
	}

	public List <Cards[][]> getObjPattern() {
		List <Cards[][]> l = new ArrayList<>();
		for(int i = 0; i < privateObj.size(); i++) {
			l.add(privateObj.get(i).getPattern());
		}
		return l;

	}

}
