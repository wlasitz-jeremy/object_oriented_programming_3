package external;

import java.util.Comparator;

import domain.Weapon;

public class NameCompare implements Comparator<Weapon>{

	@Override
	public int compare(Weapon w1, Weapon w2) {
		// TODO Auto-generated method stub
		return w1.getName().compareTo(w2.getName());
	}
}
