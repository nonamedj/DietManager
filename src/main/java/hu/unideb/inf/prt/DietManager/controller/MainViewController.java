package hu.unideb.inf.prt.DietManager.controller;

import hu.unideb.inf.prt.DietManager.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author Kriskó Szabolcs
 *
 */
public class MainViewController {

	private Main main;
	private Stage stage;

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedLoginButton() {
		Main.getLogger().info("A felhasználó megnyomta a Belépés gombot.");
		main.showLoginView();
		stage.close();
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedRegistrationButton() {
		Main.getLogger().info("A felhasználó megnyomta a Regisztráció gombot.");
		main.showRegView();
		stage.close();

	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void pressedExitButton() {
		Main.getLogger().info("A felhasználó megnyomta a Kilépés gombot.");
		stage.close();

	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
	}
}
