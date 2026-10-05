package domain;

/**
 * Represents a generic weapon with a name and base damage value.
 * <p>
 * This abstract class serves as the parent class for all weapon types.
 * Subclasses must implement the {@link #getTotalDamage()} method to define how
 * total damage is calculated.
 * </p>
 */
public abstract class Weapon implements Comparable<Weapon> {

	/** The name of the weapon. */
	private String name;

	/** The base damage dealt by the weapon. */
	private int baseDamage;

	/**
	 * Constructs a weapon with the specified name and base damage.
	 *
	 * @param name       the name of the weapon
	 * @param baseDamage the base damage value of the weapon
	 */
	public Weapon(String name, int baseDamage) {
		this.name = name;
		this.baseDamage = baseDamage;
	}

	/**
	 * Returns the name of the weapon.
	 *
	 * @return the weapon name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the base damage of the weapon.
	 *
	 * @return the base damage value
	 */
	public int getBaseDamage() {
		return this.baseDamage;
	}

	/**
	 * Calculates and returns the total damage dealt by the weapon.
	 * <p>
	 * The implementation is defined by subclasses and may include modifiers,
	 * bonuses, or special effects.
	 * </p>
	 *
	 * @return the total damage dealt by the weapon
	 */
	public abstract int getTotalDamage();

	@Override
	public int compareTo(Weapon that) {
		return this.getName().compareTo(that.getName());
	}
}