package external;

import java.util.Comparator;

import domain.Weapon;

public class DamageCompare implements Comparator<Weapon>{

	@Override
	public int compare(Weapon w1, Weapon w2) {
		// TODO Auto-generated method stub
		if( w1.getTotalDamage() > w2.getTotalDamage() )
		{
			return 1; 
		}
		else if( w1.getTotalDamage() < w2.getTotalDamage() )
		{
			return -1; 
		}
		else
		{
			return 0;
		}
	}
}
