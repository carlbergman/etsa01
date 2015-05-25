import java.io.Serializable;

/**
 * Class handling bikes.
 * 
 * @author carlbergman
 *
 */
public class Bike implements Serializable {

	/**
	 * For serializing and de-serializing. Do not change.
	 */
	private static final long serialVersionUID = -3497924117832596736L;

	/**
	 * Other attributes
	 */
	private String barcode; // Bike identifier
	private User user; // Bike owner
	private boolean status; // Whether bike is registered as in garage

	/**
	 * Bike constructor
	 * 
	 * @param barcode The barcode
	 * @param user The owner
	 */
	public Bike(String id, User user) {
		this.barcode = id;
		this.user = user;
		status = false;
	}

	/**
	 * Get the user
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Get barcode
	 * 
	 * @return the barcode
	 */
	public String getId() {
		return barcode;
	}

	/**
	 * Get check in status (whether the bike should be regarded as placed in the garage
	 * by the system.)
	 * 
	 * @return true if the bike is in the garage, otherwise false
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Check in the bike.
	 */
	public void bikeIn() {
		status = true;
	}

	/**
	 * Check out the bike.
	 */
	public void bikeOut() {
		status = false;
	}

	/**
	 * Checks if this bike is equal to another bike
	 * @param obj The object to compare to
	 * @return true if equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Bike) {
			if (((Bike) obj).getId().equals(barcode)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get a string representation of the class.
	 * 
	 * @return a string.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bar code: " + barcode);
		sb.append("   ");
		sb.append("Owner: " + user.getName() + " (" + user.getSsn() + ")");
		sb.append("   ");
		sb.append("Parked: ");
		if (status) {
			sb.append("Yes");
		} else {
			sb.append("No");
		}
		return sb.toString();
	}

}
