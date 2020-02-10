package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Common;

import servers.Montreal;

public class ClientImpl extends UnicastRemoteObject implements Customer, Manager {

	public ClientImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	Montreal mn=new Montreal();
	Common cm=new Common();
	
	@Override
	public void addEvent(String eventID, String eventType, int bookingCapacity)
			throws RemoteException {
		String var=mn.getHashMap(eventType);
		mn.setHashMap(var, eventID, bookingCapacity);
		
	}

	@Override
	public void removeEvent(String eventID, String eventType) {
		String var=mn.getHashMap(eventType);
		mn.removeHashMap(var, eventID);
		
	}

	@Override
	public void listEventAvailability(String eventType) {
		String var=mn.getHashMap(eventType);
		cm.getevents(var);
		
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
