import java.io.Serializable;
import java.util.Date;

/**
 * Log item class
 * @author carlbergman
 *
 */
public class InOutLog implements Serializable {
	/**
	 * For serializing and de-serializing. Do not change.
	 */
	private static final long serialVersionUID = -1867489459400496034L;
	
	/**
	 * Other attributes
	 */
	private Date date;
	private String type;	// In or Out (= whether a bike was inserted or removed from the garage)
	private String barCode;	// The bike identifier
	
	/**
	 * Constructor
	 * @param type insertion or removal of the bike
	 * @param date the time
	 * @param barCode the bike identifier
	 */
	public InOutLog(String type,Date date,String barCode){
		this.type = type;
		this.date = date;
		this.barCode = barCode;
	}
	
	/**
	 * Get the date of this log item
	 * @return the date
	 */
	public Date getDate(){
		return date;
	}
	
	/**
	 * Get the type of log item (= In or Out)
	 * @return "In" if the log item was an insertion, "Out" if it was a removal
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Get the bike barcode
	 * @return the bike barcode
	 */
	public String getBarCode(){
		return barCode;
	}
	
	/**
	 * String representation of the log item
	 */
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
