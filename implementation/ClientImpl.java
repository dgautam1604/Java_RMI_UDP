package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Common;
import servers.Montreal;
import servers.Quebec;
import servers.Sherbrooke;

public class ClientImpl extends UnicastRemoteObject implements Customer, Manager {

	public ClientImpl() throws RemoteException {
		super();
	}

	
	Montreal mn=new Montreal();
	Common cm=new Common();
	
	@Override
	public void addEvent(String eventID, String eventType, int bookingCapacity,String serv)
			throws RemoteException {
		if(serv.equalsIgnoreCase("MTL")){
			Montreal mn=new Montreal();
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
		}
		String var=mn.getHashMap(eventType);
		mn.addHashMap(var, eventID, bookingCapacity);
		
	}

	@Override
	public void removeEvent(String eventID, String eventType,String serv) {
		if(serv.equalsIgnoreCase("MTL")){
			Montreal mn=new Montreal();
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
		}
		String var=mn.getHashMap(eventType);
		mn.removeHashMap(var, eventID);
		
	}

	@Override
	public void listEventAvailability(String eventType,String serv) {
		if(serv.equalsIgnoreCase("MTL")){
			Montreal mn=new Montreal();
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
		}
		String var=mn.getHashMap(eventType);
		cm.getevents(var);
		
	}

	@Override
	public void bookEvent(String customerID, String eventID, String eventType,String serv) {
		if(serv.equalsIgnoreCase("MTL")){
			Montreal mn=new Montreal();
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
		}
		String var=mn.getHashMap(eventType);
		//mn.setHashMap(var, eventID, bookingCapacity);
		char[] ch1 = eventID.toCharArray();
		char[] ch2 = { ch1[0], ch1[1], ch1[2] };
		String bookingServ = new String(ch2);
		
		if(bookingServ.equalsIgnoreCase(serv)){
			mn.reduceHashMap(var, eventID, customerID);
		}
		
		
		
	}

	@Override
	public void getBookingSchedule(String customerID,String serv) {
		if(serv.equalsIgnoreCase("MTL")){
			Montreal mn=new Montreal();
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
		}
		System.out.println("The booked events for this user are: "+cm.getusers(customerID));
		
	}

	@Override
	public void cancelEvent(String customerID, String eventID, String eventType,String serv) {
		if(serv.equalsIgnoreCase("MTL")){
			Montreal mn=new Montreal();
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
		}
		String var=mn.getHashMap(eventType);
		
		char[] ch1 = eventID.toCharArray();
		char[] ch2 = { ch1[0], ch1[1], ch1[2] };
		String bookingServ = new String(ch2);
		
		if(bookingServ.equalsIgnoreCase(serv)){
			mn.reduceHashMap(var, eventID, customerID);
		}
		
	}

}
