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
		HashMap<String, Bike> bikes = (HashMap<String, Bike>) loadObjectFromFile("bikes.txt");
		HashMap<Integer, User> users = (HashMap<Integer, User>) loadObjectFromFile("users.txt");

		BicycleGarageManager manager = new BicycleGarageManager(users, bikes);
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
	 * Get object from file
	 * 
	 * @param filename
	 *            The file
	 * @return The object from the file or a new object.
	 */
	public Object loadObjectFromFile(String filename) {

		Object temp;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					filename));
			temp = in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return temp = new Object();
		}

		return temp;
	}

	/**
	 * Write an object to file
	 * 
	 * @param o
	 *            The object
	 * @param filename
	 *            The file
	 * @return true if object was written, otherwise false.
	 */
	public boolean writeObjectToFile(Object o, String filename) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(filename));
			out.writeObject(o);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
