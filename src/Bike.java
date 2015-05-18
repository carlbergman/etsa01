/**
 * Class handling bikes.
 * @author carlbergman
 *
 */
public class Bike {

	private String barcode;		// Bike identifier
	private User user;			// Bike owner
	private boolean status;		// Whether bike is registered as in garage

	/**
	 * Bike constructor
	 * @param barcode The barcode
	 * @param user The user (owner)
	 */
	public Bike (String id, User user) {
		this.barcode = id;
		this.user = user;
		status = false;
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
	
	/**
	 * Get status (whether the system should be regarded as placed in the garage by the system.)
	 * @return true if the bike is in the garage, otherwise false
	 */
	public boolean getStatus () {
		return status;
	}
	
	/**
	 * Check in the bike.
	 */
	public void bikeIn(){
		status=true;
	}
	
	/**
	 * Check out the bike.
	 */
	public void bikeOut(){
		status=false;
	}
	
	/**
	 * Get a string representation of the class.
	 * 
	 * @return a string.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Bar code: " + barcode);
		sb.append(" ");
		sb.append("Owner: " + user.getName() + " (" + user.getSsn() + ")");
		sb.append(" ");
		sb.append("Parked: ");
		if (status) {
			sb.append("Yes");
		} else {
			sb.append("No");
		}
		return sb.toString();
	}

}
