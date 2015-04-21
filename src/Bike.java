/**
 * Class handling bikes.
 * @author carlbergman
 *
 */
public class Bike {

	private String id;
	private User user;

	/**
	 * Bike constructor
	 * @param id The barcode id
	 * @param user The user (owner)
	 */
	public Bike (String id, User user) {
		this.id = id;
		this.user = user;
	}
	
	/**
	 * Get the user
	 * @return User the user
	 */
	public User getUser () {
		return user;
	}
	
	/**
	 * Get barcode id
	 * @return String the barcode id
	 */
	public String getId () {
		return id;
	}

}
