package hu.unideb.inf.prt.DietManager.model;

import java.time.LocalDate;

/**
 * Ez az osztály a felhasználó által napi bevitt tápanyagokat tartalmazza. A
 * heti statisztikához szükséges.
 * 
 * @author Kriskó Szabolcs
 *
 */
public class DailyIntakeOfNutrients {

	/**
	 * A nap folyamán bevitt szénhidrát.
	 */
	private Double intakeCarbohydrate;
	/**
	 * A nap folyamán bevitt fehérje.
	 */
	private Double intakeProtein;
	/**
	 * A nap folyamán bevitt zsír.
	 */
	private Double intakeFat;
	/**
	 * Az aktuális dátum.
	 */
	private LocalDate date;

	/**
	 * Az osztály kontruktora, mely beállítja az adott napon bevitt szénhidrát,
	 * fehérje, zsír mennyiséget és a dátumot.
	 * 
	 * @param intakeCarbohydrate
	 *            A bevitt szénhidrát mennyisége.
	 * @param intakeProtein
	 *            A bevitt fehérje mennyisége.
	 * @param intakeFat
	 *            A bevitt zsír mennyisége.
	 * @param date
	 *            A tápanyagok bevitelének napja.
	 */
	public DailyIntakeOfNutrients(Double intakeCarbohydrate, Double intakeProtein, Double intakeFat, LocalDate date) {
		super();
		this.intakeCarbohydrate = intakeCarbohydrate;
		this.intakeProtein = intakeProtein;
		this.intakeFat = intakeFat;
		this.date = date;
	}

	/**
	 * Visszaadja a nap folyamán bevitt szénhidrát mennyiségét.
	 * 
	 * @return A nap folyamán bevitt szénhidrát mennyiségét
	 */
	public Double getIntakeCarbohydrate() {
		return intakeCarbohydrate;
	}

	/**
	 * Beállítja a nap folyamán bevitt szénhidrát mennyiségét.
	 * 
	 * @param intakeCarbohydrate
	 *            A nap folyamán bevitt szénhidrát mennyisége.
	 */
	public void setIntakeCarbohydrate(Double intakeCarbohydrate) {
		this.intakeCarbohydrate = intakeCarbohydrate;
	}

	/**
	 * Visszaadja a nap folyamán bevitt fehérje mennyiségét.
	 * 
	 * @return A nap folyamán bevitt fehérje mennyiségét.
	 */
	public Double getIntakeProtein() {
		return intakeProtein;
	}

	/**
	 * Beállítja a nap folyamán bevitt fehérje mennyiségét.
	 * 
	 * @param intakeProtein
	 *            A nap folyamán bevitt fehérje mennyisége.
	 */
	public void setIntakeProtein(Double intakeProtein) {
		this.intakeProtein = intakeProtein;
	}

	/**
	 * Visszaadja a nap folyamán bevitt zsír mennyiségét.
	 * 
	 * @return A nap folyamán bevitt zsír mennyiségét.
	 */
	public Double getIntakeFat() {
		return intakeFat;
	}

	/**
	 * Beállítja a nap folyamán bevitt zsír mennyiségét.
	 * 
	 * @param intakeFat
	 *            A nap folyamán bevitt zsír mennyisége.
	 */
	public void setIntakeFat(Double intakeFat) {
		this.intakeFat = intakeFat;
	}

	/**
	 * Visszaadja az adott nap dátumát.
	 * 
	 * @return Az adott nap dátumát.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Beállítja az adott nap dátumát.
	 * 
	 * @param date
	 *            Az adott nap dátuma.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Az osztály üres konstruktora teszteléshez.
	 */
	public DailyIntakeOfNutrients() {
	}

}
