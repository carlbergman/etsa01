
public class Bike {

	private String id;
	private User user;
	
	public Bike (String id, User user) {
		this.id = id;
		this.user = user;
	}
	
	public User getUser () {
		return user;
	}
	
	public String getId () {
		return id;
	}

}
