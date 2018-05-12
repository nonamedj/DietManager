package hu.unideb.inf.prt.DietManager.DAO;

import java.nio.file.Path;
import java.util.List;

import hu.unideb.inf.prt.DietManager.model.User;

/**
 * @author Kriskó Szabolcs
 *
 */
public interface UserDAO {

	/**
	 * Az argumentumként megadott listában szereplő {@code User} példányokat
	 * lementi Json állományokba. Ha {@code true}-ra állítottuk egy felhasználó
	 * {@code deleted} attribútumát, akkor törli a felhasználóhoz tartozó
	 * állományt.
	 * 
	 * @param users
	 *            A {@code User} osztály példányait tartalmazó lista.
	 * @param path
	 *            Tetszőleges elérési útvonal megadásához egy útvonal. Valamint
	 *            teszteléshez használatos.
	 */
	public void saveUsers(List<User> users, Path path);

	/**
	 * Betölti a felhasználókat a {@code Main} osztály {@code users} listájába.
	 * .json állományokat olvas.
	 * 
	 * @param path
	 *            Tetszőleges elérési útvonal megadásához egy útvonal. Valamint
	 *            teszteléshez használatos.
	 */
	public void loadUsers(Path path);
}
