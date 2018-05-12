package hu.unideb.inf.prt.DietManager.model;

/**
 * Ez az osztály az egyes felhasználók által bevitt tápanyag típusokat és annak
 * mennyiségét tárolja, annak érdekében, hogy a visszavonás opcióval törölni
 * lehessen a napi mennyiséghez hozzáadott értéket.
 * 
 * @author Kriskó Szabolcs
 *
 */
public class Intake {

	/**
	 * A felhasználó által hozzáadott tápanyag típus.
	 */
	private Nutrients nutrient;
	/**
	 * A felhasználó által hozzáadott mennyiség.
	 */
	private Double amount;

	/**
	 * Az osztály konstruktora. Beállítja a {@code amount} és {@link nutrient}
	 * értékeit.
	 * 
	 * @param nutrient
	 *            A hozzáadott tápanyag típusa.
	 * @param amount
	 *            A hozzáadott tápanyag mennyisége.
	 */
	public Intake(Nutrients nutrient, Double amount) {
		this.nutrient = nutrient;
		this.amount = amount;
	}

	/**
	 * Visszaadja a hozzáadott tápanyag típusát.
	 * 
	 * @return A tápanyag típusát.
	 */
	public Nutrients getNutrient() {
		return nutrient;
	}

	/**
	 * Visszaadja a hozzáadott tápanyag mennyiségét.
	 * 
	 * @return A tápanyag mennyiségét.
	 */
	public Double getAmount() {
		return amount;
	}
}
