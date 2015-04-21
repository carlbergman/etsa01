import java.util.HashMap;

public class BicycleGarageManager {

	private BarcodePrinter printer;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal terminal;
	private HashMap<String, Bike> bikes;
	private HashMap<Integer, User> users;

	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal terminal, HashMap<String, Bike> bikes,
			HashMap<Integer, User> users) {

		this.printer = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.terminal = terminal;
		this.bikes = bikes;
		this.users = users;

	}

	public void entryBarcode(String code) {
		// TODO Auto-generated method stub

	}

	public void entryCharacter(char c) {
		// TODO Auto-generated method stub

	}
}
