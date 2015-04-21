
public class User {

	private int pin;
	private String name;
	private String pn;
	
	public User (int pin, String name, String pn) {
		this.pin = pin;
		this.name = name;
		this.pn = pn;		
	}
	
	public int getPin () {
		return pin;
	}
	
	public String getName () {
		return name;
	}
	
	public String getPn () {
		return pn;
	}

}
