package hu.unideb.inf.prt.DietManager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.prt.DietManager.calculation.Calculation;

/**
 * Ez az osztály tárolja az egyes felhasználók adatait.
 * 
 * @author Kriskó Szabolcs
 *
 */
public class User {

	/**
	 * A felhasználó által megadott felhasználónév.
	 */
	private String userName;

	/**
	 * A felhasználó keresztneve.
	 */
	private String firstName;

	/**
	 * A felhasználó vezetékneve.
	 */
	private String lastName;

	/**
	 * A felhasználó testmagassága.
	 */
	private Double bodyHeight;

	/**
	 * A felhasználó testsúlya.
	 */
	private Double bodyWeight;

	/**
	 * A felhasználó életkora.
	 */
	private Integer age;

	/**
	 * A felhasználó neme.
	 */
	private Gender gender;

	/**
	 * A felhasználó célja, mely lehet súlycsökkentés vagy súlytartás.
	 */
	private Goal goal;

	/**
	 * A felhasználó szükséges szénhidrát bevitele egy nap.
	 */
	private Double carbohydrate;

	/**
	 * A felhasználó szükséges fehérje bevitele egy nap.
	 */
	private Double protein;

	/**
	 * A felhasználó szükséges zsír bevitele egy nap.
	 */
	private Double fat;

	/**
	 * A felhasználó szükséges energiaszükséglete egy nap.
	 */
	private Double BMR;

	/**
	 * A felhasználó a nap folyamán már bevitt szénhidrát mennyisége.
	 */
	private Double intakeCarbohydrate;

	/**
	 * A felhasználó a nap folyamán már bevitt fehérje mennyisége.
	 */
	private Double intakeProtein;

	/**
	 * A felhasználó a nap folyamán már bevitt zsíre mennyisége.
	 */
	private Double intakeFat;

	/**
	 * A felhasználó a nap folyamán már bevitt szükséges tápanyag mennyisége.
	 */
	private Double intakeBMR;

	/**
	 * A program indulása óta a felhasználó által bevitt tápanyagok típusait és
	 * mennyiségét tartalmazó lista.
	 */
	private List<Intake> intakes = new ArrayList<Intake>();

	/**
	 * A felhasználó példányosításának dátuma.
	 */
	private LocalDate day;

	/**
	 * A felhasználó heti statisztikáját tartalmazó lista.
	 */
	private List<DailyIntakeOfNutrients> week = new ArrayList<DailyIntakeOfNutrients>();

	/**
	 * A felhasználó törlésénél használatos. Ha igaz, a felhasználó törlésre
	 * kerül.
	 */
	private boolean deleted;

	/**
	 * Az osztály üres konstruktora a tesztekhez.
	 */
	public User() {

	}

	/**
	 * Az osztály konstruktora, ahol beállítjuk a felhasználó adatait.
	 * 
	 * @param userName
	 *            A felhasználó által megadott felhasználónév.
	 * @param firstName
	 *            A felhasználó keresztneve.
	 * @param lastName
	 *            A felhasználó vezetékneve.
	 * @param bodyHeight
	 *            A felhasználó testmagassága.
	 * @param bodyWeight
	 *            A felhasználó testsúlya.
	 * @param age
	 *            A felhasználó életkora.
	 * @param gender
	 *            A felhasználó neme.
	 * @param goal
	 *            A felhasználó célja.
	 */
	public User(String userName, String firstName, String lastName, Double bodyHeight, Double bodyWeight, Integer age,
			Gender gender, Goal goal) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bodyHeight = bodyHeight;
		this.bodyWeight = bodyWeight;
		this.age = age;
		this.gender = gender;
		this.goal = goal;
		intakeCarbohydrate = 0.0;
		intakeProtein = 0.0;
		intakeFat = 0.0;
		intakeBMR = 0.0;
		day = LocalDate.now();
		deleted = false;
		Calculation.setDailyNutrients(this);
	}

	/**
	 * Visszaadja a felhasználó felhasználónevét.
	 * 
	 * @return A felhasználó felhasználónevét.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Beállítja a felhasználó felhasználónevét.
	 * 
	 * @param userName
	 *            A felhasználó felhasználóneve.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Visszaadja a felhasználó keresztnevét.
	 * 
	 * @return A felhasználó keresztnevét.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Beállítja a felhasználó keresztnevét.
	 * 
	 * @param firstName
	 *            A felhasználó keresztneve.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Visszaadja a felhasználó vezetéknevét.
	 * 
	 * @return A felhasználó vezetéknevét.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Beállítja a felhasználó vezetéknevét.
	 * 
	 * @param lastName
	 *            A felhasználó vezetékneve.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Visszaadja a felhasználó testmagasságát.
	 * 
	 * @return A felhasználó testmagasságát.
	 */
	public Double getBodyHeight() {
		return bodyHeight;
	}

	/**
	 * Beállítja a felhasználó testmagasságát.
	 * 
	 * @param bodyHeight
	 *            A felhasználó testmagassága.
	 */
	public void setBodyHeight(Double bodyHeight) {
		this.bodyHeight = bodyHeight;
	}

	/**
	 * Visszaadja a felhasználó testsúlyát.
	 * 
	 * @return A felhasználó testsúlyát.
	 */
	public Double getBodyWeight() {
		return bodyWeight;
	}

	/**
	 * Beállítja a felhasználó testsúlyát.
	 * 
	 * @param bodyWeight
	 *            A felhasználó testsúlya.
	 */
	public void setBodyWeight(Double bodyWeight) {
		this.bodyWeight = bodyWeight;
	}

	/**
	 * Visszaadja a felhasználó életkorát.
	 * 
	 * @return A felhasználó életkorát.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Beállítja a felhasználó életkorát.
	 * 
	 * @param age
	 *            A felhasználó életkora.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Visszaadja a felhasználó nemét.
	 * 
	 * @return A felhasználó nemét.
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Beállítja a felhasználó nemét.
	 * 
	 * @param gender
	 *            A felhasználó neme.
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Visszaadja a felhasználó célját.
	 * 
	 * @return A felhasználó célját.
	 */
	public Goal getGoal() {
		return goal;
	}

	/**
	 * Beállítja a felhasználó célját.
	 * 
	 * @param goal
	 *            A felhasználó célja.
	 */
	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	/**
	 * Visszaadja a felhasználó napi szükséges szénhidrát bevitelét.
	 * 
	 * @return A napi szükséges szénhidrát bevitelét.
	 */
	public Double getCarbohydrate() {
		return carbohydrate;
	}

	/**
	 * Beállítja a felhasználó napi szükséges szénhidrát bevitelét.
	 * 
	 * @param carbohydrate
	 *            A napi szükséges szénhidrát bevitel.
	 */
	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	/**
	 * Visszaadja a felhasználó napi szükséges fehérje bevitelét.
	 * 
	 * @return A napi szükséges fehérje bevitelét.
	 */
	public Double getProtein() {
		return protein;
	}

	/**
	 * Beállítja a felhasználó napi szükséges fehérje bevitelét.
	 * 
	 * @param protein
	 *            A napi szükséges fehérje bevitel.
	 */
	public void setProtein(Double protein) {
		this.protein = protein;
	}

	/**
	 * Visszaadja a felhasználó napi szükséges zsír bevitelét.
	 * 
	 * @return A napi szükséges zsír bevitelét.
	 */
	public Double getFat() {
		return fat;
	}

	/**
	 * Beállítja a felhasználó napi szükséges zsír bevitelét.
	 * 
	 * @param fat
	 *            A napi szükséges zsír bevitel.
	 */
	public void setFat(Double fat) {
		this.fat = fat;
	}

	/**
	 * Visszaadja a felhasználó napi energiaszükségletét.
	 * 
	 * @return A felhasználó napi energiaszükségletét.
	 */
	public Double getBMR() {
		return BMR;
	}

	/**
	 * Beállítja a felhasználó napi energiaszükségletét.
	 * 
	 * @param BMR
	 *            A felhasználó napi energiaszükséglete.
	 */
	public void setBMR(Double BMR) {
		this.BMR = BMR;
	}

	/**
	 * Visszaadja a felhasználó által a nap folyamán bevitt szénhidrát
	 * mennyiségét.
	 * 
	 * @return A nap folyamán bevitt szénhidrát mennyiségét.
	 */
	public Double getIntakeCarbohydrate() {
		return intakeCarbohydrate;
	}

	/**
	 * Beállítja a felhasználó által a nap folyamán bevitt szénhidrát
	 * mennyiségét.
	 * 
	 * @param intakeCarbohydrate
	 *            A nap folyamán bevitt szénhidrát mennyisége.
	 */
	public void setIntakeCarbohydrate(Double intakeCarbohydrate) {
		this.intakeCarbohydrate = intakeCarbohydrate;
	}

	/**
	 * Visszaadja a felhasználó által a nap folyamán bevitt fehérje mennyiségét.
	 * 
	 * @return A nap folyamán bevitt fehérje mennyiségét.
	 */
	public Double getIntakeProtein() {
		return intakeProtein;
	}

	/**
	 * Beállítja a felhasználó által a nap folyamán bevitt fehérje mennyiségét.
	 * 
	 * @param intakeProtein
	 *            A nap folyamán bevitt fehérje mennyisége.
	 */
	public void setIntakeProtein(Double intakeProtein) {
		this.intakeProtein = intakeProtein;
	}

	/**
	 * Visszaadja a felhasználó által a nap folyamán bevitt zsír mennyiségét.
	 * 
	 * @return A nap folyamán bevitt zsír mennyiségét.
	 */
	public Double getIntakeFat() {
		return intakeFat;
	}

	/**
	 * Beállítja a felhasználó által a nap folyamán bevitt zsír mennyiségét.
	 * 
	 * @param intakeFat
	 *            A nap folyamán bevitt zsír mennyisége.
	 */
	public void setIntakeFat(Double intakeFat) {
		this.intakeFat = intakeFat;
	}

	/**
	 * Visszaadja a felhasználó által a nap folyamán bevitt tápanyag
	 * szükségletet.
	 * 
	 * @return A nap folyamán bevitt tápanyag szükségletet.
	 */
	public Double getIntakeBMR() {
		return intakeBMR;
	}

	/**
	 * Beállítja a felhasználó által a nap folyamán bevitt tápanyag
	 * szükségletet.
	 * 
	 * @param intakeBMR
	 *            A nap folyamán bevitt tápanyag szükséglet.
	 */
	public void setIntakeBMR(Double intakeBMR) {
		this.intakeBMR = intakeBMR;
	}

	/**
	 * Visszaadja a program indítása óta bevitt tápanyag mennyiséget listáját.
	 * 
	 * @return A bevitt tápanyag mennyiséget listáját
	 */
	public List<Intake> getIntakes() {
		return intakes;
	}

	/**
	 * Visszaadja, hogy utoljára melyik napon töltöttük be a programba a
	 * felhasználót.
	 * 
	 * @return A programba töltés utolsó dátumát.
	 */
	public LocalDate getDay() {
		return day;
	}

	/**
	 * Beállítja, hogy utoljára melyik napon töltöttük be a programba a
	 * felhasználót.
	 * 
	 * @param day
	 *            Az utolsó programba töltés dátuma.
	 */
	public void setDay(LocalDate day) {
		this.day = day;
	}

	/**
	 * Visszaadja a felhasználó által az elmúlt héten bevitt tápanyagmennyiségek
	 * listáját.
	 * 
	 * @return Az elmúlt héten bevitt kalóriamennyiségek listáját.
	 */
	public List<DailyIntakeOfNutrients> getWeek() {
		return week;
	}

	/**
	 * Beállítja a felhasználó által az elmúlt héten bevitt tápanyagmennyiségek
	 * listáját.
	 * 
	 * @param week
	 *            Az elmúlt héten bevitt kalóriamennyiségek listája.
	 */
	public void setWeek(List<DailyIntakeOfNutrients> week) {
		this.week = week;
	}

	/**
	 * Visszaadja, hogy törölve van-e a felhasználó. Ha igaz értéket ad vissza,
	 * akkor a {@link hu.unideb.inf.prt.DietManager.DAO.UserDAOImpl} nem fogja
	 * elmenteni a felhasználót és a már meglévő mentést törli.
	 * 
	 * @return Törölve van-e a felhasználó.
	 */
	public boolean isDeletedUser() {
		return deleted;
	}

	/**
	 * Beállítja, hogy a felhasználó törölve legyen.
	 * 
	 * @param deleted
	 *            A törlést jelző érték.
	 */
	public void setDeletedUser(boolean deleted) {
		this.deleted = deleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", bodyHeight="
				+ bodyHeight + ", bodyWeight=" + bodyWeight + ", age=" + age + ", gender=" + gender + ", goal=" + goal
				+ ", carbohydrate=" + carbohydrate + ", protein=" + protein + ", fat=" + fat + ", BMR=" + BMR
				+ ", intakeCarbohydrate=" + intakeCarbohydrate + ", intakeProtein=" + intakeProtein + ", intakeFat="
				+ intakeFat + ", intakeBMR=" + intakeBMR + ", intakes=" + intakes + ", day=" + day + ", week=" + week
				+ ", deleted=" + deleted + "]";
	}

}
