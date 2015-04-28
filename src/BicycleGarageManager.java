import java.util.HashMap;
import java.util.ArrayList;
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
	
	public boolean removeUser(String pin){
		if(users.containsKey(pin)){
			users.remove(pin);
			return true;
		}else{
			return false;
		}
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
	
	public boolean removeBike(String bikeID){
		if(bikes.containsKey(bikeID)){
			bikes.remove(bikeID);
			return true;
		}else{
			return false;
		}
	}
	
	public void printBarcode(String bikeID){
		printer.printBarcode(bikeID);
	}
	
	public ArrayList<User> searchUser(String s){
		ArrayList<User> userlist = new ArrayList<User>();
		if(s.matches("[0-9]+")&&s.length()==5){
			userlist.add(users.get(s));
			return userlist;
		}else if(!s.matches("[0-9]+")){
			User user = new User("",s,"");
			for(Map.Entry<String, User> e:users.entrySet()){
				if(e.getValue().equals(user)){
					userlist.add(user);
				}
			}
			return userlist;
		}else if(s.matches("[0-9]{6,10}")){
			User user = new User("","",s);
			for(Map.Entry<String, User> e:users.entrySet()){
				if(e.getValue().equals(user)){
					userlist.add(user);
				}
			}
			return userlist;
		}else{
			return null;
		}
	}
	
//	public ArrayList<Bike> getUserBikes(User user){
//		ArrayList<Bike> bikelist = new ArrayList<Bike>();
//		for(Map.Entry<String, Bike> e:bikes.entrySet()){
//			if(user.equals())
//		}
//	}
	
	class RemindTask extends TimerTask{
		@Override
		public void run() {
			pinArray.clear();
		}
	}
}
