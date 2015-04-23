/**
 * Class handling users. 
 * @author carlbergman
 *
 */
public class User {

	private String pin;		// Person pin code
	private String name;	// Person name
	private String pn;		// Personal id number
	
	/**
	 * User constructor
	 * @param pin User pin code
	 * @param name User name
	 * @param pn User personal id number
	 */
	public User (String pin, String name, String pn) {
		this.pin = pin;
		this.name = name;
		this.pn = pn;		
	}
	
	/**
	 * Get pin code
	 * @return int the pin code
	 */
	public String getPin () {
		return pin;
	}
	
	/**
	 * Get name
	 * @return String the name
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Get personal id number
	 * @return String the personal id
	 */
	public String getPn () {
		return pn;
	}

}