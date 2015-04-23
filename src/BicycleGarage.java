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
		HashMap<String, User> users = (HashMap<String, User>) loadObjectFromFile("users.txt");

		BicycleGarageManager manager = new BicycleGarageManager(users, bikes);
		ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
		ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
		BarcodePrinter printer = new BarcodePrinterTestDriver();

		PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
		terminal.register(manager);

		BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
		readerEntry.register(manager);

		BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
		readerExit.register(manager);

		OperatorGUI gui = new OperatorGUI();
		gui.register(manager);

		manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
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
	public HashMap<?, ?> loadObjectFromFile(String filename) {

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
	 * Write an object to file
	 * 
	 * @param o
	 *            The object
	 * @param filename
	 *            The file
	 * @return true if object was written, otherwise false.
	 */
	public boolean writeObjectToFile(HashMap<?, ?> o, String filename) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(filename));
			out.writeObject(o);
			out.close();
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}

		return true;
	}

}
