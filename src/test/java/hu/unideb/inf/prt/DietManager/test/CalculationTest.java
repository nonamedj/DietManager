package hu.unideb.inf.prt.DietManager.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import hu.unideb.inf.prt.DietManager.Main;
import hu.unideb.inf.prt.DietManager.DAO.UserDAOImpl;
import hu.unideb.inf.prt.DietManager.calculation.Calculation;
import hu.unideb.inf.prt.DietManager.model.DailyIntakeOfNutrients;
import hu.unideb.inf.prt.DietManager.model.Gender;
import hu.unideb.inf.prt.DietManager.model.Goal;
import hu.unideb.inf.prt.DietManager.model.Intake;
import hu.unideb.inf.prt.DietManager.model.Nutrients;
import hu.unideb.inf.prt.DietManager.model.User;

/**
 * @author Kriskó Szabolcs
 *
 */
public class CalculationTest {

    private User me;	
    private User father;
	private User dori;
	private User user;
	private List<User> users;

	@Before
	public void set() throws Exception {
        user = new User();
		me = new User("Szabi", "Szabolcs", "Kriskó", Double.valueOf(188), Double.valueOf(90), 22, Gender.MALE,
				Goal.KEEP_BODYWEIGHT);
		user = new User();
		father = new User("Miki", "Miklós", "Kriskó", Double.valueOf(180), Double.valueOf(80), 40, Gender.MALE,
				Goal.KEEP_BODYWEIGHT);
		dori = new User("Dóri", "Dóra", "Dalmi", Double.valueOf(170), Double.valueOf(60), 32,
				Gender.FEMALE, Goal.LOSE_BODYWEIGHT);
		users = new ArrayList<User>();
	}
        
     @Test
	public void idealWeightForMeTest() {
		assertEquals(79.2, Calculation.getIdealWeight(me), 0);
	}
        
	@Test
	public void idealWeightForMyFatherTest() {
		assertEquals(72, Calculation.getIdealWeight(father), 0);
	}

	@Test
	public void idealWeightForDoriTest() {
		assertEquals(63, Calculation.getIdealWeight(dori), 0);
	}

	@Test
	public void idealWeightForSomeoneTest() {
		user.setGender(Gender.MALE);
		user.setAge(21);
		user.setBodyHeight(Double.valueOf(165));
		assertEquals(58.5, Calculation.getIdealWeight(user), 0);
	}
        
        @Test
	public void idealWeightForSomeone2Test() {
		user.setGender(Gender.MALE);
		user.setAge(100);
		user.setBodyHeight(Double.valueOf(100));
		assertEquals(0, Calculation.getIdealWeight(user), 0);
	}

	@Test
	public void BMIForMeTest() {
		assertEquals(25.464, Calculation.getBMI(me), 0.001);
	}
	
	@Test
	public void BMIForMyFatherTest() {
		assertEquals(24.7, Calculation.getBMI(father), 0.01);
	}

	@Test
	public void BMIForDoriTest() {
		assertEquals(20.76, Calculation.getBMI(dori), 0.01);
	}

	@Test
	public void BMRForMeTest() {
		assertEquals(1970, Calculation.getBMR(me, me.getBodyWeight(), me.getBodyHeight(),
				me.getAge()), 0);
	}
	
	@Test
	public void BMRForMyFatherTest() {
		assertEquals(1730, Calculation.getBMR(father, father.getBodyWeight(), father.getBodyHeight(),
				father.getAge()), 0);
	}

	@Test
	public void BMRForDoriTest() {
		assertEquals(1341.5, Calculation.getBMR(dori, 60, 170, 32), 0);
	}

	@Test
	public void fatPercentageForMeTest() {
		assertEquals(19.41, Calculation.getFatPercentage(me), 0.01);
	}
	
	@Test
	public void fatPercentageForMyFatherTest() {
		assertEquals(22.629, Calculation.getFatPercentage(father), 0.01);
	}

	@Test
	public void fatPercentageForDoriTest() {
		assertEquals(26.872, Calculation.getFatPercentage(dori), 0.01);
	}

        @Test
	public void keepWeightForMeTest() {
		assertEquals(3152, Calculation.getKcalPerDayToKeepBodyWeight(me, me.getBodyWeight(),
				me.getBodyHeight(), me.getAge()), 0.01);
	}

	@Test
	public void loseWeightForMeTest() {
		assertEquals(2167, Calculation.getKcalPerDayToLoseBodyWeight(me, me.getBodyWeight(),
				me.getBodyHeight(), me.getAge()), 0.01);
	}
        
	@Test
	public void keepWeightForMyFatherTest() {
		assertEquals(2768, Calculation.getKcalPerDayToKeepBodyWeight(father, father.getBodyWeight(),
				father.getBodyHeight(), father.getAge()), 0.01);
	}

	@Test
	public void loseWeightForMyFatherTest() {
		assertEquals(1903, Calculation.getKcalPerDayToLoseBodyWeight(father, father.getBodyWeight(),
				father.getBodyHeight(), father.getAge()), 0.01);
	}

	@Test
	public void loseWeightForDoriTest() {
		assertEquals(1475.65, Calculation.getKcalPerDayToLoseBodyWeight(dori, dori.getBodyWeight(),
				dori.getBodyHeight(), dori.getAge()), 0.01);
	}

	@Test
	public void keepWeightForDoriTest() {
		assertEquals(2146.4, Calculation.getKcalPerDayToKeepBodyWeight(dori, dori.getBodyWeight(),
				dori.getBodyHeight(), dori.getAge()), 0.01);
	}

        @Test
	public void DailyNutrientsForMeLoseWeightLessThan30PercentFatTest() {
		Calculation.setDailyNutrients(me);
		assertEquals(438, me.getCarbohydrate(), 1);
		assertEquals(215, me.getProtein(), 1);
		assertEquals(50, me.getFat(), 1);
	}
        
	@Test
	public void DailyNutrientsForDoriLoseWeightLessThan30PercentFatTest() {
		Calculation.setDailyNutrients(dori);
		assertEquals(205.15, dori.getCarbohydrate(), 0.01);
		assertEquals(100.78, dori.getProtein(), 0.01);
		assertEquals(23.80, dori.getFat(), 0.01);
	}

	@Test
	public void DailyNutrientsForDoriKeepWeightLessThan30PercentFatTest() {
		user = dori;
		user.setGoal(Goal.KEEP_BODYWEIGHT);
		Calculation.setDailyNutrients(dori);
		assertEquals(298.40, dori.getCarbohydrate(), 0.01);
		assertEquals(146.58, dori.getProtein(), 0.01);
		assertEquals(34.62, dori.getFat(), 0.01);
	}

	@Test
	public void DailyNutrientsForDoriLoseWeightMoreThan30PercentFatTest() {
		user = dori;
		user.setBodyWeight(Double.valueOf(90));
		Calculation.setDailyNutrients(dori);
		assertEquals(209.74, dori.getCarbohydrate(), 0.01);
		assertEquals(103.03, dori.getProtein(), 0.01);
		assertEquals(24.33, dori.getFat(), 0.01);
	}

	@Test
	public void DailyNutrientsForDoriKeepWeightMoreThan30PercentFatTest() {
		user = dori;
		user.setGoal(Goal.KEEP_BODYWEIGHT);
		user.setBodyWeight(Double.valueOf(90));
		Calculation.setDailyNutrients(dori);
		assertEquals(305.08, dori.getCarbohydrate(), 0.01);
		assertEquals(149.86, dori.getProtein(), 0.01);
		assertEquals(35.39, dori.getFat(), 0.01);
	}

	@Test
	public void refreshIntakeBMRTest() {
		user = me;
		Calculation.refreshIntakeBMR(user);
		assertEquals(0, user.getIntakeBMR(), 0);
	}

	@Test
	public void setIntakeCarbohydrateTest() {
		user = me;
		Calculation.increaseIntakeCarbohydrate(11.0, user);
		assertEquals(11.0, user.getIntakeCarbohydrate(), 0);
	}
        
        @Test
	public void setIntakeCarbohydrateTest2() {
		user = me;
		Calculation.increaseIntakeCarbohydrate(-200.0, user);
		assertEquals(-200.0, user.getIntakeCarbohydrate(), 0);
	}

	@Test
	public void setIntakeProteinTest() {
		user = me;
		Calculation.increaseIntakeProtein(0.0, user);
		assertEquals(0.0, user.getIntakeProtein(), 0);
	}
        @Test
	public void setIntakeProteinTest2() {
		user = me;
		Calculation.increaseIntakeProtein(100.0, user);
		assertEquals(100.0, user.getIntakeProtein(), 0);
	}

	@Test
	public void setIntakeFatTest() {
		user = me;
		Calculation.increaseIntakeFat(20.0, user);
		assertEquals(20.0, user.getIntakeFat(), 0);
	}
        
        @Test
	public void setIntakeFatTest2() {
		user = me;
		Calculation.increaseIntakeFat(2000000.0, user);
		assertEquals(2000000.0, user.getIntakeFat(), 0);
	}

	@Test
	public void setIntakeCarbohydrateNotNullTest() {
		user = me;
		user.setIntakeCarbohydrate(20.0);
		Calculation.increaseIntakeCarbohydrate(12.0, user);
		assertEquals(32.0, user.getIntakeCarbohydrate(), 0);
	}
        
        @Test
	public void setIntakeCarbohydrateNotNullTest2() {
		user = me;
		user.setIntakeCarbohydrate(1.0);
		Calculation.increaseIntakeCarbohydrate(1.0, user);
		assertEquals(2, user.getIntakeCarbohydrate(), 0);
	}

	@Test
	public void setIntakeProteinNotNullTest() {
		user = me;
		user.setIntakeProtein(9.0);
		Calculation.increaseIntakeProtein(12.0, user);
		assertEquals(21.0, user.getIntakeProtein(), 0);
	}
        
        @Test
	public void setIntakeProteinNotNullTest2() {
		user = me;
		user.setIntakeProtein(-9.0);
		Calculation.increaseIntakeProtein(12.0, user);
		assertEquals(3.0, user.getIntakeProtein(), 0);
	}

	@Test
	public void setIntakeFatNotNullTest() {
		user = me;
		user.setIntakeFat(11.0);
		Calculation.increaseIntakeFat(12.0, user);
		assertEquals(23.0, user.getIntakeFat(), 0);
	}
        
        @Test
	public void setIntakeFatNotNullTest2() {
		user = me;
		user.setIntakeFat(11.0);
		Calculation.increaseIntakeFat(-12.0, user);
		assertEquals(-1.0, user.getIntakeFat(), 0);
	}

	@Test
	public void withdrawCarbohydrateTest() {
		user = me;
		user.getIntakes().add(new Intake(Nutrients.CARBOHYDRATE, 34.0));
		user.setIntakeCarbohydrate(34.0);
		Calculation.withdraw(user);
		assertEquals(0.0, user.getIntakeCarbohydrate(), 0);
	}

	@Test
	public void withdrawProteinTest() {
		user = me;
		user.getIntakes().add(new Intake(Nutrients.PROTEIN, 200.0));
		user.setIntakeProtein(200.0);
		Calculation.withdraw(user);
		assertEquals(0.0, user.getIntakeProtein(), 0);
	}

	@Test
	public void withdrawFatTest() {
		user = me;
		user.getIntakes().add(new Intake(Nutrients.FAT, 8.0));
		user.setIntakeFat(8.0);
		Calculation.withdraw(user);
		assertEquals(0.0, user.getIntakeFat(), 0);
	}

	@Test
	public void withdrawNoIntakeTest() {
		user = me;
		user.setIntakeCarbohydrate(30.0);
		Calculation.withdraw(user);
                assertEquals(30, user.getIntakeCarbohydrate(), 0);
	}

	@Test
	public void fillSkippedDaysTest() {
		user = me;
		user.getWeek().clear();
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(0, user.getWeek().size());
	}

	@Test
	public void fillSkippedDaysWeekSizeTest() {
		user = me;
		user.getWeek().clear();
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().minusDays(9)));
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().minusDays(8)));
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(7, user.getWeek().size());
	}

	@Test
	public void fillSkippedDaysDifferenceEqualsZeroTest() {
		user = me;
		user.getWeek().clear();
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now()));
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(1, user.getWeek().size());
	}
        
        @Test
	public void fillSkippedDaysDifferenceEqualsZeroTest2() {
		user = me;
		user.getWeek().clear();
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now()));
                user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now()));
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(2, user.getWeek().size());
	}

	@Test
	public void clearWithdrawsTest() {
		users.clear();
		me.getIntakes().add(new Intake(Nutrients.CARBOHYDRATE, 11.0));
		users.add(me);
		Calculation.clearWithdraws(users);
		for (User user : users) {
			assertEquals(0, user.getIntakes().size());
		}
	}

	@Test
	public void resetIntakeNutrientsDayTest() {
		users.clear();
		user.setDay(LocalDate.of(2018, 05, 9));
		users.add(user);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.of(2018, 05, 9), user.getWeek().get(0).getDate());
	}

	@Test
	public void resetIntakeNutrientsTodayTest() {
		users.clear();
		me.setDay(LocalDate.now().minusDays(1));
		users.add(me);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), me.getDay());
		assertEquals(0.0, me.getIntakeCarbohydrate(), 0);
		assertEquals(0.0, me.getIntakeProtein(), 0);
		assertEquals(0.0, me.getIntakeFat(), 0);
		assertEquals(0.0, me.getIntakeBMR(), 0);
	}

	@Test
	public void resetIntakeNutrientsFuture() {
		users.clear();
		me.setDay(LocalDate.now().plusDays(2));
		users.add(me);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), me.getDay());
		assertEquals(0.0, me.getIntakeCarbohydrate(), 0);
		assertEquals(0.0, me.getIntakeProtein(), 0);
		assertEquals(0.0, me.getIntakeFat(), 0);
		assertEquals(0.0, me.getIntakeBMR(), 0);
	}

	@Test
	public void resetIntakeNutrientsNowTest() {
		users.clear();
		me.setDay(LocalDate.now());
		me.setIntakeCarbohydrate(1.0);
		me.setIntakeFat(1.0);
		me.setIntakeProtein(1.0);
		Calculation.refreshIntakeBMR(me);
		users.add(me);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), me.getDay());
		assertEquals(1.0, me.getIntakeCarbohydrate(), 0);
		assertEquals(1.0, me.getIntakeProtein(), 0);
		assertEquals(1.0, me.getIntakeFat(), 0);
		assertEquals(3.0, me.getIntakeBMR(), 0);
	}

	@Test
	public void resetIntakeNutrientsPastTest() {
		users.clear();
		me.setDay(LocalDate.now().minusDays(2));
		users.add(me);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), me.getDay());
		assertEquals(0.0, me.getIntakeCarbohydrate(), 0);
		assertEquals(0.0, me.getIntakeProtein(), 0);
		assertEquals(0.0, me.getIntakeFat(), 0);
		assertEquals(0.0, me.getIntakeBMR(), 0);
	}

	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();

	public UserDAOImpl dao = new UserDAOImpl();

	@Test
	public void saveDeletedUsersTest() throws IOException {
		tmp.create();
		dao.loadUsers(tmp.getRoot().toPath());
		user = me;
		user.setDeletedUser(true);
		users.clear();
		users.add(user);
		Main.getUsers().clear();
		Gson gson = new GsonBuilder().create();
		FileWriter filewriter = new FileWriter(new File(tmp.getRoot().toString(), user.getUserName() + ".json"));
		gson.toJson(user, filewriter);
		filewriter.close();
		dao.saveUsers(users, Paths.get(tmp.getRoot().toString()));
		assertEquals(false, new File(tmp.getRoot().toString(), user.getUserName() + ".json").exists());
	}

}
