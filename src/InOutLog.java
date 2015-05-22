import java.io.Serializable;
import java.util.Date;


public class InOutLog implements Serializable {
	/**
	 * For serializing and de-serializing. Do not change.
	 */
	private static final long serialVersionUID = -1867489459400496034L;
	
	/**
	 * Attributes
	 */
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
