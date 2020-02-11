package implementation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Manager extends Remote {

	//public int add(int x, int y) throws RemoteException;
	public void addEvent (String eventID,String eventType,int bookingCapacity,String serv) throws RemoteException;
	public void removeEvent (String eventID,String eventType,String serv) throws RemoteException;
	public void listEventAvailability (String eventType,String serv) throws RemoteException;
	
}
