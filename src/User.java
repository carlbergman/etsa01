import java.io.Serializable;

/**
 * Class handling users.
 * 
 * @author carlbergman
 * 
 */
public class User implements Serializable{

	/**
	 * For serializing and de-serializing. Do not change.
	 */
	private static final long serialVersionUID = -8462305766075620662L;
	
	/**
	 * Other attributes
	 */
	private String pin; // Person pin
	private String name; // Person name
	private String ssn; // Personal id number

	/**
	 * User constructor
	 * 
	 * @param pin User pin
	 * @param name User name
	 * @param ssn Social security number
	 */
	public User(String pin, String name, String ssn) {
		this.pin = pin;
		this.name = name;
		this.ssn = ssn;
	}

	/**
	 * Get pin code
	 * 
	 * @return the pin code
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Get name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get ssn
	 * 
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * Is this User equal to another object.
	 * 
	 * @param obj The object to compare to
	 * 
	 * @return boolean true if equal, otherwise false.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			if (((User) obj).getSsn().equals(ssn)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * String representation of the class.
	 * 
	 * @return a string.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name);
		sb.append("   ");
		sb.append("Social security number: " + ssn);
		sb.append("   ");
		sb.append("Pin: " + pin);
		return sb.toString();
	}

}