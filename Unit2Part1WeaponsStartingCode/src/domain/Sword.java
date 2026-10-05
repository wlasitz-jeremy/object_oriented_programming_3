package domain;

/**
 * Represents a sword weapon with an additional damage modifier.
 * <p>
 * A sword's total damage is calculated by adding its base damage to its damage
 * modifier.
 * </p>
 */
public class Sword extends Weapon {
	/** Additional damage applied to the sword. */
	private int damageModifier;

	/**
	 * Constructs a sword with the specified name and base damage.
	 *
	 * @param name       the name of the sword
	 * @param baseDamage the base damage dealt by the sword
	 */
	public Sword(String name, int baseDamage) {
		super(name, baseDamage);
	}

	/**
	 * Returns the damage modifier applied to the sword.
	 *
	 * @return the damage modifier
	 */
	public int getDamageModifier() {
		return damageModifier;
	}

	/**
	 * Sets the damage modifier for the sword.
	 *
	 * @param damageModifier the damage modifier to apply
	 */
	public void setDamageModifier(int damageModifier) {
		this.damageModifier = damageModifier;
	}

	/**
	 * Calculates the total damage dealt by the sword.
	 * <p>
	 * The total damage is the sum of the base damage and the damage modifier.
	 * </p>
	 *
	 * @return the total damage value
	 */
	@Override
	public int getTotalDamage() {
		return getBaseDamage() + this.damageModifier;
	}

	@Override
	public int compareTo(Weapon that) {
		if (this.getTotalDamage() > that.getTotalDamage()) {
			return 1;
		} else if (this.getTotalDamage() < that.getTotalDamage()) {
			return -1;
		} else {
			return 0;
		}
	}
}