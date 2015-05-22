import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BicycleGarageManager {

	private BarcodePrinter printer;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal terminal;
	private HashMap<String, Bike> bikes;
	private HashMap<String, User> users;
	private ArrayList<Character> pinArray;
	private String pin;
	private Timer timer;
	private RemindTask remind = new RemindTask();
	private ArrayList<InOutLog> log;

	public BicycleGarageManager() {
		bikes = (HashMap<String, Bike>) loadHashMapFromFile("bikes.txt");
		users = (HashMap<String, User>) loadHashMapFromFile("users.txt");
		log = loadListFromFile("log.txt");
		
		pinArray = new ArrayList<Character>();
		timer = new Timer();
	}

	/**
	 * Register all the hardware
	 * 
	 * @param printer
	 * @param entryLock
	 * @param exitLock
	 * @param terminal
	 */
	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal terminal) {
		this.printer = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.terminal = terminal;
	}

	/**
	 * Opens the entry lock and lights the green light if the barcode is in the
	 * system. Also logs the scanning.
	 * 
	 * @param code
	 *            the barcode
	 */
	public void entryBarcode(String code) {
		if (bikes.containsKey(code)) {
			bikes.get(code).bikeIn();
			log.add(new InOutLog("In", new Date(), code));
			entryLock.open(15);
			terminal.lightLED(1, 15);
		} else {
			terminal.lightLED(0, 5);
		}
	}

	/**
	 * Opens the exit lock if the barcode is in the system. Also logs the
	 * scanning.
	 * 
	 * @param code
	 *            the barcode
	 */
	public void exitBarcode(String code) {
		if (bikes.containsKey(code)) {
			bikes.get(code).bikeOut();
			log.add(new InOutLog("Out", new Date(), code));
			exitLock.open(15);
		}
	}

	/**
	 * Checks if the right pincode is entered. If so, opens the entry lock and
	 * lights the green light.
	 * 
	 * @param c
	 *            the entry character
	 */
	public void entryCharacter(char c) {
		
		// Add the entered char to the ones entered within the valid time frame.
		pinArray.add(c);
		
		// Reset the timeout timer.
		remind.cancel();
		timer.schedule(remind = new RemindTask(), 3000);
		
		// Check if it's time to validate the pin.
		if (pinArray.size() >= 5) {

			// Get the pin as a string.			
			StringBuilder sb = new StringBuilder();
			for(char s : pinArray) {
			    sb.append(s);
			}
			pin = sb.toString();
			
			// Find the pin among users
			if (users.containsKey(pin)) {
				
				// Reset the pin
				pin = "";
				
				// Reset the list of entered chars
				pinArray.clear();
				
				// Open the lock
				entryLock.open(10);
				
				// Light the green LED for 10 seconds.
				terminal.lightLED(1, 10);

			} else {
				
				// Reset the pin
				pin = "";
				
				// Reset the list of entered chars
				pinArray.clear();
				
				// Light the red LED for 3 seconds.
				terminal.lightLED(0, 3);
			}
			
			// Reset the pin
			pin = "";
			
			// Reset the list of entered chars
			pinArray.clear();
		}
	}

	/**
	 * Adds a new user to the system
	 * 
	 * @param name
	 * @param ssn
	 *            the social security number
	 * @return a message that the user was added and the pincode the user
	 *         receives.
	 * @throws Exception
	 */
	public String newUser(String name, String ssn) throws Exception {
		String pincodeString;
		do {
			int pincode = (int) Math.floor((Math.random() * 100000));
			pincodeString = String.format("%05d", pincode);
		} while (users.containsKey(pincodeString));

		User user = new User(pincodeString, name, ssn);
		
		// Check if user already exists by searching for ssn
		for (Map.Entry<String, User> e : users.entrySet()) {
			User u = e.getValue();
			if (u.equals(user)) {
				throw new Exception("The user with social security number "
						+ ssn + " already exists.");
			}
		}
		users.put(pincodeString, user);
		return pincodeString;
	}

	/**
	 * Removes a user from the system
	 * 
	 * @param ssn
	 *            the social security number
	 * @return true if the user was removed, false otherwise.
	 * @throws Exception
	 */
	public boolean removeUser(String ssn) throws Exception {
		for (Map.Entry<String, User> e : users.entrySet()) {
			User u = e.getValue();
			if (u.getSsn().equals(ssn)) {
				for(Map.Entry<String, Bike> bike : bikes.entrySet()){
					if(bike.getValue().getUser().equals(u)){
						bikes.remove(bike.getKey());
					}
				}
				users.remove(u.getPin());
				return true;
			}
		}
		throw new Exception("No user with social security number " + ssn
				+ " found.");
	}

	/**
	 * Adds a new bike to the system
	 * 
	 * @param user
	 */
	public Bike newBike(User user) {
		String barcodeString;
		do {
			int barcode = (int) Math.floor((Math.random() * 100000));
			barcodeString = String.format("%05d", barcode);
		} while (bikes.containsKey(barcodeString));

		Bike bike = new Bike(barcodeString, user);
		bikes.put(barcodeString, bike);
		printer.printBarcode(barcodeString);
		return bike;
	}

	/**
	 * Removes a bike from the system
	 * 
	 * @param bikeID
	 *            the bikes barcode
	 * @return true if the bike was removed, false otherwise
	 */
	public boolean removeBike(String bikeID) {
		if (bikes.containsKey(bikeID)) {
			bikes.remove(bikeID);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Searches the system for users.
	 * 
	 * @param s
	 *            can be pincode, social security number, or name.
	 * @return a list with the users who fits the search
	 * @throws Exception 
	 */
	public ArrayList<User> searchUser(String s) throws Exception {
		ArrayList<User> userlist = new ArrayList<User>();

		if (s.matches("^[0-9]{5}$")) {
			userlist.add(users.get(s));
		} else if (s.matches("^[\\pL\\s]+$")) {
			for (Map.Entry<String, User> e : users.entrySet()) {
				User user = e.getValue();
				
				//Checks if string s is part of username
				if (user.getName().toLowerCase().contains(s.toLowerCase())) {
					userlist.add(user);
				}
			}
		} else if (s.matches("^([0-9]{6}[-+]{1}[0-9]{4})$")) {
			for (Map.Entry<String, User> e : users.entrySet()) {
				User user = e.getValue();
				if (user.getSsn().equals(s)) {
					userlist.add(user);
				}
			}
		}
		
		if (userlist.size() > 0) {
			return userlist;
		} else {
			throw new Exception("Not found.");
		}
	}

	/**
	 * Get all the bikes a user has in the system.
	 * 
	 * @param user
	 * @return the list of bikes the user has in the system
	 */
	public ArrayList<Bike> getUserBikes(User user) {
		ArrayList<Bike> bikelist = new ArrayList<Bike>();
		for (Map.Entry<String, Bike> e : bikes.entrySet()) {
			if (e.getValue().getUser().equals(user)) {
				bikelist.add(e.getValue());
			}
		}
		return bikelist;
	}
	
	/**
	 * Get a bike
	 * @param bikeId the barcode of the bike
	 * @return a Bike object or null
	 */
	public Bike getBike(String bikeId) {
		return bikes.get(bikeId);
	}

	/**
	 * Returns all the bikes in the system
	 * 
	 * @return a list with all the bikes in the system
	 */
	public ArrayList<Bike> getAllBikes() {
		ArrayList<Bike> bikeList = new ArrayList<Bike>();
		for (Map.Entry<String, Bike> e : bikes.entrySet()) {
			bikeList.add(e.getValue());
		}
		return bikeList;
	}

	/**
	 * Return all the users in the system
	 * 
	 * @return a list with all the users in the system
	 */
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		for (Map.Entry<String, User> e : users.entrySet()) {
			userList.add(e.getValue());
		}
		return userList;
	}
	
	/**
	 * Return all logged items
	 * 
	 * @return the log
	 */
	public ArrayList<InOutLog> getLog() {
		return log;
	}
	
	/**
	 * Writes the hashmaps and the log array to files
	 */
	public void quit() {
		writeHashMapToFile(bikes, "bikes.txt");
		writeHashMapToFile(users, "users.txt");
		writeListToFile(log, "log.txt");
		
		System.exit(0);
	}
	
	/**
	 * Get HashMap from file
	 * 
	 * @param filename
	 *            The file
	 * @return The HashMap from the file or a new HashMap.
	 */
	public HashMap<?, ?> loadHashMapFromFile(String filename) {

		HashMap<?, ?> temp;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					filename));
			temp = (HashMap<?, ?>) in.readObject();
			in.close();
		} catch (Exception e) {
			return temp = new HashMap();
		}

		return temp;
	}
	
	/**
	 * Write a HashMap to file
	 * 
	 * @param o
	 *            The HashMap
	 * @param filename
	 *            The file
	 * @return true if HashMap was written, otherwise false.
	 */
	public boolean writeHashMapToFile(HashMap<?, ?> o, String filename) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(o);
			out.close();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Get list from file
	 * 
	 * @param filename
	 *            The file
	 * @return The list from the file or a new list.
	 */
	public ArrayList<InOutLog> loadListFromFile(String filename) {

		ArrayList<InOutLog> temp;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					filename));
			temp = (ArrayList<InOutLog>) in.readObject();
			in.close();
		} catch (Exception e) {
			return temp = new ArrayList<InOutLog>();
		}

		return temp;
	}

	/**
	 * Write a list to file
	 * 
	 * @param o
	 *            The list
	 * @param filename
	 *            The file
	 * @return true if list was written, otherwise false.
	 */
	public boolean writeListToFile(ArrayList<InOutLog> o, String filename) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(filename));
			out.writeObject(o);
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Creates a remindtask for the timer
	 * 
	 * @author antonscholin
	 * 
	 */
	class RemindTask extends TimerTask {
		/**
		 * Runs the timertask
		 */
		@Override
		public void run() {
			pinArray.clear();
			terminal.lightLED(0, 1);
		}
	}
}
