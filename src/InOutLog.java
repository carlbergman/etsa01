import java.util.Date;


public class InOutLog {
	private Date date;
	private String type;
	private String barCode;
	
	public InOutLog(String type,Date date,String barCode){
		this.type=type;
		this.date=date;
		this.barCode=barCode;
	}
	
	public Date getDate(){
		return date;
	}
	
	//Type is In/Out
	public String getType(){
		return type;
	}
	
	public String getBarCode(){
		return barCode;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Date: " + date.toString());
		sb.append("\t");
		sb.append("Type: " + type);
		sb.append("\t");
		sb.append("Barcode: " + barCode);
		return sb.toString();
	}
}
