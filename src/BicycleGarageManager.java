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
<<<<<<< HEAD
	private HashMap<Integer, User> users;
	private HashMap<String, User> users;
	private ArrayList<Character> pincode;
	private StringBuilder sb;
	
	public BicycleGarageManager(HashMap<Integer, User> users,HashMap<String, Bike> bikes){
	public BicycleGarageManager(HashMap<String, User> users,HashMap<String, Bike> bikes){
		this.users=users;
		this.bikes=bikes;
		pincode=new ArrayList<Character>();
		sb=new StringBuilder();
=======
	private HashMap<String, User> users;
	
	public BicycleGarageManager(HashMap<String, User> users,HashMap<String, Bike> bikes){
		this.users = users;
		this.bikes = bikes;
>>>>>>> origin/master
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
		pincode.add(c);
		Timer timer = new Timer();
		timer.schedule(new RemindTask(), 3000);
		if(pincode.size()>=5){
			for(char pinc:pincode){
				sb.append(pinc);
			}
			if(users.containsKey(sb.toString())){
				entryLock.open(15);
				terminal.lightLED(1, 15);
			}else{
				terminal.lightLED(0, 5);
			}
			sb.setLength(0);
			pincode.clear();
		}
	}

	public void newUser(String name) {
		// TODO Auto-generated method stub
		
	}
	
	class RemindTask extends TimerTask{
		@Override
		public void run() {
			pincode.clear();
		}
	}
}
