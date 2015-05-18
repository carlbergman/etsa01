import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * The Bicycle Garage
 * 
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
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new BicycleGarage();
	}

}
