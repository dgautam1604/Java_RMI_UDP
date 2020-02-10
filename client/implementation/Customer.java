package implementation;

import java.rmi.Remote;

public interface Customer extends Remote{
	public void bookEvent (String customerID,String eventID,String eventType);
	public void getBookingSchedule (String customerID);
	public void cancelEvent (String customerID,String eventID,String eventType);
	
}
