package driver;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Date;
import domain.Dagger;
import domain.Sword;
import domain.Weapon;
import external.DamageCompare;

/**
 * Demonstrates the creation, storage, and sorting of weapon objects.
 * <p>
 * This driver program creates an inventory containing swords and daggers,
 * displays the inventory before sorting, sorts the weapons alphabetically by
 * name, and finally sorts them by total damage using a custom comparator.
 * </p>
 */
public class Driver {

	/**
	 * The main entry point of the application.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {

		/** Collection storing all weapons in the inventory. */
		ArrayList<Weapon> inventory = new ArrayList<>();

		// Add swords to the inventory.
		inventory.add(new Sword("Anduril", 87));
		inventory.add(new Sword("Excalibur", 91));
		inventory.add(new Sword("Longclaw", 55));
		inventory.add(new Sword("Frostmourne", 99));
		inventory.add(new Sword("Master Sword", 80));

		// Add daggers to the inventory.
		inventory.add(new Dagger("Catspaw Dagger", 29));
		inventory.add(new Dagger("Arya's Dagger", 61));
		inventory.add(new Dagger("Dagger of Time", 87));
		inventory.add(new Dagger("Mehrunes Razor", 88));
		inventory.add(new Dagger("Blade of Woe", 52));

		Date myDate = new Date();

		System.out.println("Jeremy Wlasitz" + " " + myDate + "\n");

		System.out.println("Before sorting by name: ");
		for (Weapon w : inventory) {
			System.out.println("\t" + w.getName() + " " + w.getTotalDamage());
		}

		System.out.println("\nAfter sorting by name: ");
		Collections.sort(inventory);
		for (Weapon w : inventory) {
			System.out.println("\t" + w.getName() + " " + w.getTotalDamage());
		}

		System.out.println("\nAfter sorting by damage: ");
		DamageCompare dc = new DamageCompare();
		Collections.sort(inventory, dc);
		for (Weapon w : inventory) {
			System.out.println("\t" + w.getName() + " " + w.getTotalDamage());
		}
	}
}