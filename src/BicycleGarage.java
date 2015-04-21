import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * @author carlbergman
 * 
 */
public class BicycleGarage {

	/**
	 * Constructor
	 */
	public BicycleGarage() {
		HashMap<String, Bike> bikes = new HashMap<String, Bike>();
		HashMap<Integer, User> users = new HashMap<Integer, User>();
		
		BicycleGarageManager manager = new BicycleGarageManager(users,bikes);
		ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
		ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
		BarcodePrinter printer = new BarcodePrinterTestDriver();
		PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
		manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
		terminal.register(manager);
		BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
		BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
		readerEntry.register(manager);
		readerExit.register(manager);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BicycleGarage();
	}

	/**
	 * Load users
	 * @return users hashmap if file was readable, otherwise empty hashmap.
	 */
	public HashMap<Integer, User> loadUsers() {

		HashMap<Integer, User> temp;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.txt"));
			temp = (HashMap<Integer, User>) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return temp = new HashMap<Integer, User>();
		}

		return temp;
	}

	/**
	 * Write users
	 * @return false if unable to write to file, otherwise true
	 */
	public boolean writeUsers(HashMap<Integer, User> users) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.txt"));
			out.writeObject(users);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
	 * Load bikes
	 * @return bikes hashmap if file was readable, otherwise empty hashmap.
	 */
	public HashMap<String, Bike> loadBikes() {

		HashMap<String, Bike> temp;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("bikes.txt"));
			temp = (HashMap<String, Bike>) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return temp = new HashMap<String, Bike>();
		}

		return temp;
	}

	/**
	 * Write bikes 
	 * @return false if unable to write to file, otherwise true
	 */
	public boolean writeBikes(HashMap<String, Bike> bikes) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bikes.txt"));
			out.writeObject(bikes);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
