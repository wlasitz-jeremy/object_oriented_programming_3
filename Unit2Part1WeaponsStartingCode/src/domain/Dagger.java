package domain;

/**
 * Represents a dagger weapon with an adjustable damage modifier.
 * <p>
 * A dagger's total damage is calculated by adding its base damage
 * to its damage modifier.
 * </p>
 */
public class Dagger extends Weapon
{
	/** Additional damage applied to the dagger. */
	private int damageModifier;
	
	/**
	 * Constructs a dagger with the specified name and base damage.
	 *
	 * @param name the name of the dagger
	 * @param baseDamage the base damage dealt by the dagger
	 */
	public Dagger( String name, int baseDamage )
	{
		super( name, baseDamage );
	}

	/**
	 * Returns the damage modifier applied to the dagger.
	 *
	 * @return the damage modifier
	 */
	public int getDamageModifier()
	{
		return damageModifier;
	}

	/**
	 * Sets the damage modifier for the dagger.
	 *
	 * @param damageModifier the damage modifier to apply
	 */
	public void setDamageModifier( int damageModifier )
	{
		this.damageModifier = damageModifier;
	}
	
	/**
	 * Calculates the total damage dealt by the dagger.
	 * <p>
	 * The total damage is the sum of the base damage and
	 * the damage modifier.
	 * </p>
	 *
	 * @return the total damage value
	 */
	@Override
	public int getTotalDamage()
	{
		return getBaseDamage() + this.damageModifier;
	}

	@Override
	public int compareTo(Weapon that) {
		if( this.getTotalDamage() > that.getTotalDamage() )
		{
			return 1; 
		}
		else if( this.getTotalDamage() < that.getTotalDamage() )
		{
			return -1; 
		}
		else
		{
			return 0;
		}
	}
	
}