package hu.unideb.inf.prt.DietManager.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import hu.unideb.inf.prt.DietManager.Main;
import hu.unideb.inf.prt.DietManager.model.User;

/**
 * A felhasználók mentéséért és betöltéséért felelős osztály.
 * 
 * @author Kriskó Szabolcs
 *
 */
public class UserDAOImpl implements UserDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.unideb.inf.prt.DietManager.DAO.UserDAO#loadUsers(hu.unideb.inf.prt.
	 * DietManager.Main)
	 */
	@Override
	public void loadUsers(Path path) {
		Gson gson = new GsonBuilder().create();
		if (path == null)
			path = Paths.get(System.getProperty("user.home"), "save");
		Main.getLogger().error("A betöltendö felhasználók könyvtárának elérési útvonala: " + path.toString());
		if (path.toFile().exists()) {
			File dir = path.toFile();
			for (File f : dir.listFiles()) {
				try {
					if (f.toPath().toString().endsWith(".json")) {
						Main.getLogger().info("Az állomány útvonala: " + f.getPath());
						User user = gson.fromJson(new FileReader(f.getPath()), User.class);
						Main.getLogger().info("Felhasználó: " + user);
						if (user != null) {
							Main.getUsers().add(user);

						}
					}
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					Main.getLogger().error("A felhasználók betöltése sikertelen!", e);
				}
			}
		} else {
			path.toFile().mkdir();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.unideb.inf.prt.DietManager.DAO.UserDAO#saveUsers(java.util.List,
	 * hu.unideb.inf.prt.DietManager.Main)
	 */
	@Override
	public void saveUsers(List<User> users, Path path) {
		Gson gson = new GsonBuilder().create();
		if (path == null)
			path = Paths.get(System.getProperty("user.home"), "save");
		Main.getLogger().error("A mentett felhasználók könyvtárának elérési útvonala: " + path.toString());
		for (User user : users) {
			Main.getLogger().info("Felhasználó: " + user);
			Path userPath = Paths.get(path.toString(), user.getUserName() + ".json");
			try {
				if (user.isDeletedUser()) {
					Main.getLogger().info("Törölt állomány elérési útvonala:" + userPath.toString());
					userPath.toFile().delete();
				} else {

					Main.getLogger().info("Állomány útvonala: " + userPath.toString());
					FileWriter fileWriter = new FileWriter(userPath.toFile());
					gson.toJson(user, fileWriter);
					fileWriter.close();
				}
			} catch (JsonIOException | IOException e) {
				Main.getLogger().error("A felhasználók mentése sikertelen!", e);
			}
		}

	}

}
