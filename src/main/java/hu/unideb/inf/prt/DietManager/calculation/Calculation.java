package hu.unideb.inf.prt.DietManager.calculation;

import java.time.LocalDate;
import java.util.List;

import hu.unideb.inf.prt.DietManager.Main;
import hu.unideb.inf.prt.DietManager.model.DailyIntakeOfNutrients;
import hu.unideb.inf.prt.DietManager.model.Gender;
import hu.unideb.inf.prt.DietManager.model.Goal;
import hu.unideb.inf.prt.DietManager.model.Intake;
import hu.unideb.inf.prt.DietManager.model.Nutrients;
import hu.unideb.inf.prt.DietManager.model.User;

/**
 * Ez az osztály a program számolásait végzi. A felhasználó által bevitt adatok
 * alapján kiszámolja a szükséges napi kalória bevitelt súlycsökkentéshez és
 * súlytartáshoz.
 * 
 * @author Kriskó Szabolcs
 * 
 */
public class Calculation {

	/**
	 * Egy gramm szénhidrát tápértéke(Kcal).
	 */
	private static final Double KcalPerOneGrammCh = 4.1;

	/**
	 * Egy gramm fehérje tápértéke(Kcal).
	 */
	private static final Double KcalPerOneGrammP = 4.1;

	/**
	 * Egy gramm zsír tápértéke(Kcal).
	 */
	private static final Double KcalPerOneGrammF = 9.3;

	/**
	 * A kalóriaszükségletben a szénhidrát ennyi százalékban szerepel.
	 */
	private static final Double CarbohydratePercentage = 0.57;

	/**
	 * A kalóriaszükségletben a fehérjeennyi százalékban szerepel.
	 */
	private static final Double ProteinPercentage = 0.28;

	/**
	 * A kalóriaszükségletben a zsír ennyi százalékban szerepel.
	 */
	private static final Double FatPercentage = 0.15;

	/**
	 * Kiszámolja és visszaadja a felhasználó ideális testsúlyát.
	 * 
	 * @param user
	 *            Az adott felhasználó akinek az ideális testsúlyát számoljuk.
	 * @return A {@code user} ideális testsúlyát.
	 */
	public static double getIdealWeight(User user) {
		return (user.getBodyHeight() - 100) * 0.9;
	}

	/**
	 * Kiszámolja és visszaadja a felhasználó testtömeg indexét(BMI).
	 * 
	 * @param user
	 *            Az adott felhasználó akinek a testtömeg indexét számoljuk.
	 * @return A {@code user} testtömeg indexét.
	 */
	public static double getBMI(User user) {
		double bodyheight = user.getBodyHeight() / 100;
		return user.getBodyWeight() / Math.pow(bodyheight, 2);
	}

	/**
	 * Kiszámolja és visszaadja a felhasználó testzsír százalékát.
	 * 
	 * @param user
	 *            Az adott felhasználó akinek a testzsír százalékát számoljuk.
	 * @return A {@code user} testzsír százalékát.
	 */
	public static double getFatPercentage(User user) {
		Gender gender = user.getGender();
		if(gender == Gender.FEMALE){
			return (1.2 * getBMI(user)) + (0.23 * user.getAge()) - 5.4;
		}else if(gender == Gender.MALE){
			return (1.2 * getBMI(user)) + (0.23 * user.getAge()) - 10.8 - 5.4;
		}
		return 0;
	}

	/**
	 * Kiszámolja és visszaadja a felhasználó napi energiaszükségletét, a
	 * Mifflin-St Jeor képlet alapján. A képlet itt található :
	 * https://www.freedieting.com/calorie-needs
	 * 
	 * @param user
	 *            Az adott felhasználó akinek a napi energiaszükségletét
	 *            számoljuk.
	 * @param bodyweight
	 *            A felhasználó testsúlya. Ami lehet az aktuális testsúlya vagy
	 *            az ideális testsúlya, melyet a {@link #getIdealWeight(User)}
	 *            metódus számol ki.
	 * @param bodyheight
	 *            A felhasználó magassága.
	 * @param age
	 *            A felhasználó életkora.
	 * @return A {@code user} felhasználó energiaszükségletét.
	 */
	public static double getBMR(User user, double bodyweight, double bodyheight, int age) {

		if (user.getGender() == Gender.MALE) {
			return (10 * bodyweight) + (6.25 * bodyheight) - (5 * age) + 5;
		} else if (user.getGender() == Gender.FEMALE) {
			return 10 * bodyweight + 6.25 * bodyheight - 5 * age - 161;
		}
		return 0;
	}

	/**
	 * Kiszámolja és visszaadja a felhasználó napi szükséges kalóriamennyiségét,
	 * amennyiben tömegel.
	 * 
	 * @param user
	 *            Az adott felhasználó akinek a napi kalóriaszükségletét
	 *            számoljuk súlytartáshoz.
	 * @param bodyweight
	 *            A felhasználó testsúlya. Ami lehet az aktuális testsúlya vagy
	 *            az ideális testsúlya, melyet a {@link #getIdealWeight(User)}
	 *            metódus számol ki.
	 * @param bodyheight
	 *            A felhasználó magassága.
	 * @param age
	 *            A felhasználó életkora.
	 * @return A {@code user} felhasználó napi kalóriaszükségletét, ha tartani
	 *         akarja a súlyát.
	 */
	public static double getKcalPerDayToKeepBodyWeight(User user, double bodyweight, double bodyheight, int age) {
		return getBMR(user, bodyweight, bodyheight, age) * 1.6;
	}

	/**
	 * Kiszámolja és visszaadja a felhasználó napi szükséges kalóriamennyiségét,
	 * amennyiben csökkenteni akarja a súlyát.
	 * 
	 * @param user
	 *            Az adott felhasználó akinek a napi kalóriaszükségletét
	 *            számoljuk súlycsökkentéshez.
	 * @param bodyweight
	 *            A felhasználó testsúlya. Ami lehet az aktuális testsúlya vagy
	 *            az ideális testsúlya, melyet a {@link #getIdealWeight(User)}
	 *            metódus számol ki.
	 * @param bodyheight
	 *            A felhasználó magassága.
	 * @param age
	 *            A felhasználó életkora.
	 * @return A {@code user} felhasználó napi kalóriaszükségletét, ha
	 *         csökkenteni akarja a súlyát.
	 */
	public static double getKcalPerDayToLoseBodyWeight(User user, double bodyweight, double bodyheight, int age) {
		return getBMR(user, bodyweight, bodyheight, age) * 1.1;
	}

	/**
	 * Beállítja a felhasználó szükséges napi kalória mennyiségét, külön
	 * szénhidrátra, fehérjére és zsírra bontva.
	 * 
	 * @param u
	 *            Az adott felhasználó akinek a napi kalóriaszükségletét
	 *            állítjuk be.
	 */
	public static void setDailyNutrients(User u) {
		double localBMR;
		if (u.getGoal() == Goal.KEEP_BODYWEIGHT) {
			localBMR = (getFatPercentage(u) > 30)
					? getKcalPerDayToKeepBodyWeight(u, getIdealWeight(u), u.getBodyHeight(), u.getAge())
					: getKcalPerDayToKeepBodyWeight(u, u.getBodyWeight(), u.getBodyHeight(), u.getAge());
		} else {
			localBMR = (getFatPercentage(u) > 30)
					? getKcalPerDayToLoseBodyWeight(u, getIdealWeight(u), u.getBodyHeight(), u.getAge())
					: getKcalPerDayToLoseBodyWeight(u, u.getBodyWeight(), u.getBodyHeight(), u.getAge());
		}
		u.setCarbohydrate(localBMR * CarbohydratePercentage / KcalPerOneGrammCh);
		u.setProtein(localBMR * ProteinPercentage / KcalPerOneGrammP);
		u.setFat(localBMR * FatPercentage / KcalPerOneGrammF);
		u.setBMR(u.getCarbohydrate() + u.getProtein() + u.getFat());
	}

	/**
	 * Frissíti a felhasználó által az adott napon bevitt tápanyagszükségletet.
	 * Automatikusan meghívódik, ha a
	 * {@link #increaseIntakeCarbohydrate(Double, User)},
	 * {@link #increaseIntakeProtein(Double, User)},
	 * {@link #increaseIntakeFat(Double, User)} vagy {@link #withdraw(User)}
	 * metódus lefut.
	 * 
	 * @param user
	 *            Az adott felhasználó akinek az adott napon bevitt
	 *            tápanyagszükségletét frissítjük.
	 */
	public static void refreshIntakeBMR(User user) {
		user.setIntakeBMR(user.getIntakeCarbohydrate() + user.getIntakeProtein() + user.getIntakeFat());
	}

	/**
	 * Növeli a felhasználó által az adott napon bevitt szénhidrát szükségletet.
	 * 
	 * @param intakeCarbohydrate
	 *            A {@code user} felhasználó által bevitt szénhidrát mennyiség,
	 *            melyet hozzáadunk az adott napon már bevitt szénhidrát
	 *            szükséglethez.
	 * @param user
	 *            Az adott felhasználó, aki {@code intakeCarbohydrate}
	 *            mennyiségű szénhidrátot vitt be.
	 */
	public static void increaseIntakeCarbohydrate(Double intakeCarbohydrate, User user) {
		user.setIntakeCarbohydrate(user.getIntakeCarbohydrate() + intakeCarbohydrate);
		Intake intake = new Intake(Nutrients.CARBOHYDRATE, intakeCarbohydrate);
		user.getIntakes().add(intake);
		refreshIntakeBMR(user);

	}

	/**
	 * Növeli a felhasználó által az adott napon bevitt fehérje szükségletet.
	 * 
	 * @param intakeProtein
	 *            A {@code user} felhasználó által bevitt fehérje mennyiség,
	 *            melyet hozzáadunk az adott napon már bevitt fehérje
	 *            szükséglethez.
	 * @param user
	 *            Az adott felhasználó, aki {@code intakeProtein} mennyiségű
	 *            fehérjét vitt be.
	 */
	public static void increaseIntakeProtein(Double intakeProtein, User user) {
		user.setIntakeProtein(user.getIntakeProtein() + intakeProtein);
		Intake intake = new Intake(Nutrients.PROTEIN, intakeProtein);
		user.getIntakes().add(intake);
		refreshIntakeBMR(user);

	}

	/**
	 * Növeli a felhasználó által az adott napon bevitt zsír szükségletet.
	 * 
	 * @param intakeFat
	 *            A {@code user} felhasználó által bevitt zsír mennyiség, melyet
	 *            hozzáadunk az adott napon már bevitt zsír szükséglethez.
	 * @param user
	 *            Az adott felhasználó, aki {@code intakeFat} mennyiségű zsírt
	 *            vitt be.
	 */
	public static void increaseIntakeFat(Double intakeFat, User user) {
		user.setIntakeFat(user.getIntakeFat() + intakeFat);
		Intake intake = new Intake(Nutrients.FAT, intakeFat);
		user.getIntakes().add(intake);
		refreshIntakeBMR(user);

	}

	/**
	 * Nullázza a felhasználók által az adott napon bevitt tápanyagmennyiséget,
	 * ha a legutoljára bevitt tápanyagmennyiség napja elmúlt. Abban az esetben
	 * is helyesen mutatja az értékeket, amennyiben vissza lett állítva a dátum.
	 * 
	 * @param users
	 *            Az összes felhasználót tartalmazó lista, amellyel a metódus
	 *            számol.
	 */
	public static void resetIntakeNutrients(List<User> users) {
		for (User user : users) {
			if (LocalDate.now().isBefore(user.getDay())) {
				user.setDay(LocalDate.now());
				user.setIntakeCarbohydrate(0.0);
				user.setIntakeProtein(0.0);
				user.setIntakeFat(0.0);
				user.setIntakeBMR(0.0);
			}else
			if (LocalDate.now().minusDays(1).isAfter(user.getDay())
					|| LocalDate.now().minusDays(1).equals(user.getDay())) {
				if (user.getWeek().size() >= 6)
					user.getWeek().remove(0);
				user.getWeek().add(new DailyIntakeOfNutrients(user.getIntakeCarbohydrate(), user.getIntakeProtein(),
						user.getIntakeFat(), user.getDay()));
				user.setDay(LocalDate.now());
				user.setIntakeCarbohydrate(0.0);
				user.setIntakeProtein(0.0);
				user.setIntakeFat(0.0);
				user.setIntakeBMR(0.0);
			}
		}
	}

	/**
	 * Amennyiben egy felhasználó több napja nem használta a programot, a
	 * kihagyott napokat a statisztika táblázatban feltölti üres napokkal és az
	 * időátállításra is ügyel.
	 * 
	 * @param week
	 *            Egy adott felhasználó heti statisztikájának listája.
	 */
	public static void fillSkippedDays(List<DailyIntakeOfNutrients> week) {
		int difference;
		
		if (week.size() >= 1) {
			Main.getLogger().info("Utolsó belépés óta eltelt napok: "
					+ week.get(week.size() - 1).getDate().until(LocalDate.now()).getDays());
			
			if ((difference = week.get(week.size() - 1).getDate().until(LocalDate.now()).getDays()) > 0) {
				
				for (int i = 0; i < difference - 1; i++) {
					DailyIntakeOfNutrients filler = new DailyIntakeOfNutrients(0.0, 0.0, 0.0,
							week.get(week.size() - 1).getDate().plusDays(1));
					week.add(filler);
				}
				
				int weekSize = week.size();
				if (weekSize > 7) {
					weekSize -= 7;
					for (int i = 0; i < weekSize; i++) {
						week.remove(0);
					}
				}
				
			} else if (difference < 0) {
				difference = Math.abs(difference);
				if (difference < week.size())
					for (int i = 0; i <= difference; i++) {
						Main.getLogger().info("A week mérete: " + week.size() + "A difference mérete: " + difference);
						week.remove(week.size() - 1);
					}
				else {
					week.clear();
				}
			}
		}
	}

	/**
	 * A program bezárásakor hívódik meg, és törli a felhasználók tápanyag
	 * hozzáadásuk után alkalmazható visszavonásaikat.
	 * 
	 * @param users
	 *            A felhasználók listája.
	 */
	public static void clearWithdraws(List<User> users) {
		for (User user : users) {
			user.getIntakes().clear();
		}
	}

	/**
	 * Visszavonja a felhasználó által előzőleg megadott tápanyagmennyiséget.
	 * Annyiszor lehet visszavonni, amennyi hozzáadás történt a program indítása
	 * óta.
	 * 
	 * @param user
	 *            Az adott felhasználó akinek a tápanyag hozzáadásait
	 *            visszavonjuk.
	 */
	public static void withdraw(User user) {
		Intake intake;
		int size;
		if ((size = user.getIntakes().size()) > 0) {
			intake = user.getIntakes().get(size - 1);
			switch (intake.getNutrient()) {
			case PROTEIN:
				user.setIntakeProtein(user.getIntakeProtein() - intake.getAmount());
				break;
			case CARBOHYDRATE:
				user.setIntakeCarbohydrate(user.getIntakeCarbohydrate() - intake.getAmount());
				break;
			case FAT:
				user.setIntakeFat(user.getIntakeFat() - intake.getAmount());
				break;
			default:
				break;
			}
			user.getIntakes().remove(size - 1);
			refreshIntakeBMR(user);
		}
	}

}
