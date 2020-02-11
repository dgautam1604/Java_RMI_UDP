package implementation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Customer extends Remote{
	public void bookEvent (String customerID,String eventID,String eventType,String serv) throws RemoteException;
	public void getBookingSchedule (String customerID,String serv) throws RemoteException;
	public void cancelEvent (String customerID,String eventID,String eventType,String serv)  throws RemoteException;
	
}
