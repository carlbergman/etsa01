/**
 * Class handling bikes.
 * @author carlbergman
 *
 */
public class Bike {

	private String barcode;
	private User user;

	/**
	 * Bike constructor
	 * @param barcode The barcode
	 * @param user The user (owner)
	 */
	public Bike (String id, User user) {
		this.barcode = id;
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
	 * Get barcode
	 * @return String the barcode
	 */
	public String getId () {
		return barcode;
	}

}
