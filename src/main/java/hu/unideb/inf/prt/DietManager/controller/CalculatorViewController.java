package hu.unideb.inf.prt.DietManager.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import hu.unideb.inf.prt.DietManager.Main;
import hu.unideb.inf.prt.DietManager.calculation.Calculation;
import hu.unideb.inf.prt.DietManager.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

/**
 * @author Kriskó Szabolcs
 *
 */
public class CalculatorViewController {

	private Main main;
	private Stage stage;
	private User user;
	private NumberFormat formatting = new DecimalFormat("#0");

	private static Boolean carbohydrateButton = false;
	private static Boolean proteinButton = false;
	private static Boolean fatButton = false;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
	private Label nameLabel;

	@FXML
	private ProgressIndicator carbohydratePI;

	@FXML
	private ProgressIndicator proteinPI;

	@FXML
	private ProgressIndicator fatPI;

	@FXML
	private ProgressBar sumPB;

	@FXML
	private Label carbohydrateLabel;

	@FXML
	private Label proteinLabel;

	@FXML
	private Label fatLabel;

	@FXML
	private Label sumLabel;

	public void setLabels() {
		nameLabel.setText(user.getFirstName());
		carbohydrateLabel.setText(formatting.format(user.getIntakeCarbohydrate()) + "/"
				+ formatting.format(user.getCarbohydrate()) + " g");
		proteinLabel.setText(
				formatting.format(user.getIntakeProtein()) + "/" + formatting.format(user.getProtein()) + " g");
		fatLabel.setText(formatting.format(user.getIntakeFat()) + "/" + formatting.format(user.getFat()) + " g");
		sumLabel.setText(formatting.format(user.getIntakeBMR()) + "/" + formatting.format(user.getBMR()) + " g");
		carbohydratePI.setProgress(user.getIntakeCarbohydrate() / user.getCarbohydrate());
		proteinPI.setProgress(user.getIntakeProtein() / user.getProtein());
		fatPI.setProgress(user.getIntakeFat() / user.getFat());
		sumPB.setProgress(user.getIntakeBMR() / user.getBMR());
	}

	public void setStageFocusListener(Stage stage) {
		Main.getLogger().info("A bevitt tápanyag mennyiség frissült.");
		stage.focusedProperty().addListener((o, oldValue, newValue) -> setLabels());

	}

	@FXML
	public void addCarbohydrateButton() {
		Main.getLogger().info("A felhasználó megnyomta a szénhidrát hozzáadásához tartozó hozzáad gombot.");
		carbohydrateButton = true;
		System.err.printf("Carbon: %b", carbohydrateButton);
		main.showAddNutrientView(user);
	}

	@FXML
	public void addProteinButton() {
		Main.getLogger().info("A felhasználó megnyomta a fehérje hozzáadásához tartozó hozzáad gombot.");
		CalculatorViewController.proteinButton = true;
		main.showAddNutrientView(user);
	}

	@FXML
	public void addFatButton() {
		Main.getLogger().info("A felhasználó megnyomta a zsír hozzáadásához tartozó hozzáad gombot.");
		CalculatorViewController.fatButton = true;
		main.showAddNutrientView(user);
	}

	public void setCarbohydrateButton(boolean carbohydrateButton) {
		CalculatorViewController.carbohydrateButton = carbohydrateButton;
	}

	public Boolean getCarbohydrateButton() {
		return CalculatorViewController.carbohydrateButton;
	}

	public void setProteinButton(boolean proteinButton) {
		CalculatorViewController.proteinButton = proteinButton;
	}

	public boolean getProteinButton() {
		return CalculatorViewController.proteinButton.booleanValue();
	}

	public void setFatButton(boolean fatButton) {
		CalculatorViewController.fatButton = fatButton;
	}

	public Boolean getFatButton() {
		return CalculatorViewController.fatButton;
	}

	@FXML
	public void showWeeklyStatistics() {
		Main.getLogger().info("A felhasználó megnyomta a Heti statisztika gombot.");
		main.showWeeklyStatistics(user);
	}

	@FXML
	public void pressedEditDataButton() {
		Main.getLogger().info("A felhasználó megnyomta a Személyes adatok gombot.");
		main.showEditUserDataView(user);
	}

	@FXML
	public void pressedLogoutButton() {
		Main.getLogger().info("A felhasználó megnyomta a kijelentkezés gombot.");
		stage.close();
		main.showLoginView();
	}

	@FXML
	public void pressedDeleteButton() {
		Main.getLogger().info("A felhasználó megnyomta a Felhasználó törlése gombot.");
		user.setDeletedUser(true);
		stage.close();
		main.showLoginView();
	}

	@FXML
	public void pressWithdraw() {
		Main.getLogger().info("A felhasználó megnyomta a Visszavonás gombot.");
		Calculation.withdraw(user);
		setLabels();
	}
}
