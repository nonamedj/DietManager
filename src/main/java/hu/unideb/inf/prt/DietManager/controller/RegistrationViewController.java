package hu.unideb.inf.prt.DietManager.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.DietManager.Main;
import hu.unideb.inf.prt.DietManager.model.Gender;
import hu.unideb.inf.prt.DietManager.model.Goal;
import hu.unideb.inf.prt.DietManager.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Kriskó Szabolcs
 *
 */
public class RegistrationViewController {

	private Main main;
	private Stage stage;
	private User user;
	private ObservableList<String> genders = FXCollections.observableArrayList();
	private ObservableList<String> goals = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		genders.add("Nő");
		genders.add("Férfi");
		genderComboBox.setItems(genders);

		goals.add("Szálkásítás");
		goals.add("Tömegelés");
		goalComboBox.setItems(goals);
	}

	@FXML
	private TextField userNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField firstNameField;

	@FXML
	private ComboBox<String> genderComboBox;

	@FXML
	private TextField ageField;

	@FXML
	private TextField bodyheightField;

	@FXML
	private TextField bodyweightField;

	@FXML
	private ComboBox<String> goalComboBox;

	@FXML
	private Label userExistErrorMessage;
	
	@FXML
	private Label userNameErrorMessage;
	
	@FXML
	private Label lastNameErrorMessage;
	
	@FXML
	private Label firstNameErrorMessage;

	@FXML
	private Label genderErrorMessage;

	@FXML
	private Label ageErrorMessage;

	@FXML
	private Label bodyheightErrorMessage;

	@FXML
	private Label bodyweightErrorMessage;

	@FXML
	private Label goalErrorMessage;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void pressCancel() {
		Main.getLogger().info("A felhasználó megnyomta a Mégsem gombot.");
		stage.close();
		main.showMainView();
	}

	private boolean verifyFields() {
		boolean correct = true;

		if (main.getUserByUserName(userNameField.getText()) != null) {
			userExistErrorMessage.setVisible(true);
			correct = false;
		} else {
			userExistErrorMessage.setVisible(false);
		}

		if (userNameField.getText().isEmpty()) {
			userNameErrorMessage.setVisible(true);
			correct = false;
		} else {
			userNameErrorMessage.setVisible(false);
		}

		if (lastNameField.getText().isEmpty()) {
			lastNameErrorMessage.setVisible(true);
			correct = false;
		} else {
			lastNameErrorMessage.setVisible(false);
		}
		
		if (firstNameField.getText().isEmpty()) {
			firstNameErrorMessage.setVisible(true);
			correct = false;
		} else {
			firstNameErrorMessage.setVisible(false);
		}
		
		if (genderComboBox.getSelectionModel().selectedItemProperty().getValue() == null) {
			genderErrorMessage.setVisible(true);
			correct = false;
		} else {
			genderErrorMessage.setVisible(false);
		}

		if (!Pattern.matches("[1-9][0-9]*", ageField.getText())) {
			ageErrorMessage.setVisible(true);
			correct = false;
		} else {
			ageErrorMessage.setVisible(false);
		}

		if (!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", bodyheightField.getText())) {
			bodyheightErrorMessage.setVisible(true);
			correct = false;
		} else {
			bodyheightErrorMessage.setVisible(false);
		}

		if (!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", bodyweightField.getText())) {
			bodyweightErrorMessage.setVisible(true);
			correct = false;
		} else {
			bodyweightErrorMessage.setVisible(false);
		}

		if (goalComboBox.getSelectionModel().selectedItemProperty().getValue() == null) {
			goalErrorMessage.setVisible(true);
			correct = false;
		} else {
			goalErrorMessage.setVisible(false);
		}

		return correct;
	}

	@FXML
	public void pressRegistrationButton() {
		Main.getLogger().info("A felhasználó megnyomta a Regisztráció gombot.");
		if (verifyFields()) {
			Gender gender = (genderComboBox.getSelectionModel().selectedIndexProperty().getValue() == 1)
					? (gender = Gender.MALE) : (gender = Gender.FEMALE);

			Goal goal = (goalComboBox.getSelectionModel().selectedIndexProperty().getValue() == 1)
					? (goal = Goal.KEEP_BODYWEIGHT) : (goal = Goal.LOSE_BODYWEIGHT);

			user = new User(userNameField.getText(), firstNameField.getText(), lastNameField.getText(),
					Double.valueOf(bodyheightField.getText()), Double.valueOf(bodyweightField.getText()),
					Integer.parseInt(ageField.getText()), gender, goal);
			Main.getUsers().add(user);
			stage.close();
			main.showMainView();
		}
	}

}
