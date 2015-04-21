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
		BicycleGarageManager manager = new BicycleGarageManager();
		ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
		ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
		BarcodePrinter printer = new BarcodePrinterTestDriver();
		PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
		HashMap<String, Bike> bikes = new HashMap<String, Bike>();
		HashMap<Integer, User> users = new HashMap<Integer, User>();
		manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal, bikes, users);
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

}
