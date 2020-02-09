package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Customer, Manager {

	public ClientImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void addEvent(String eventID, String eventType, int bookingCapacity)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEvent(String eventID, String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listEventAvailability(String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bookEvent(String customerID, String eventID, String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getBookingSchedule(String customerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelEvent(String customerID, String eventID, String eventType) {
		// TODO Auto-generated method stub
		
	}

}
