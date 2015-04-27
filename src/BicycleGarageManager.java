import java.util.HashMap;
import java.util.ArrayList;
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
	private StringBuilder pinSB;
	
	public BicycleGarageManager(HashMap<String, User> users,HashMap<String, Bike> bikes){
		this.users=users;
		this.bikes=bikes;
		pinArray=new ArrayList<Character>();
		pinSB=new StringBuilder();
	}

	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal terminal) {
		this.printer = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.terminal = terminal;
	}

	public void entryBarcode(String code) {
		if(bikes.containsKey(code)){
			entryLock.open(15);
			terminal.lightLED(1, 15);
		}else{
			terminal.lightLED(0,5);
		}
	}

	public void entryCharacter(char c) {
		pinArray.add(c);
		Timer timer = new Timer();
		timer.schedule(new RemindTask(), 3000);
		if(pinArray.size()>=5){
			for(char pinc:pinArray){
				pinSB.append(pinc);
			}
			if(users.containsKey(pinSB.toString())){
				pinSB.setLength(0);
				pinArray.clear();
				entryLock.open(10);
				terminal.lightLED(1, 10);
			}else{
				pinSB.setLength(0);
				pinArray.clear();
				terminal.lightLED(0, 3);
			}
			pinSB.setLength(0);
			pinArray.clear();
		}
	}

	public String newUser(String name, String pn) {
		String pincodeString;
		do{
			int pincode = (int)Math.floor((Math.random()*100000));
			pincodeString=String.format("%04d",pincode);
		}while(users.containsKey(pincodeString));
		
		User user = new User(pincodeString,name,pn);
		users.put(pincodeString, user);
		return pincodeString;
	}
	
	public void newBike(User user){
		String barcodeString;
		do{
			int barcode = (int)Math.floor((Math.random()*100000));
			barcodeString=String.format("%04d",barcode);
		}while(bikes.containsKey(barcodeString));
		
		Bike bike = new Bike(barcodeString,user);
		bikes.put(barcodeString, bike);
	}
	
	class RemindTask extends TimerTask{
		@Override
		public void run() {
			pinArray.clear();
		}
	}
}
