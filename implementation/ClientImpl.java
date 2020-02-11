package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
			String var=mn.getHashMap(eventType);
			mn.UDPConnect(6001,var);
			mn.UDPConnect(6002,var);
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
			String var=mn.getHashMap(eventType);
			mn.UDPConnect(6000,var);
			mn.UDPConnect(6002,var);
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
			String var=mn.getHashMap(eventType);
			mn.UDPConnect(6000,var);
			mn.UDPConnect(6001,var);
		}
		
		//cm.getevents(var);
		
		
	}

	@Override
	public void bookEvent(String customerID, String eventID, String eventType,String serv) {
		if(serv.equalsIgnoreCase("MTL")){
			Montreal mn=new Montreal();
			String city1="QUE";
			String city2="SHE";
		}
		else if(serv.equalsIgnoreCase("QUE")){
			Quebec mn=new Quebec();
			String city1="MTL";
			String city2="SHE";
		}
		else if (serv.equalsIgnoreCase("SHE")){
			Sherbrooke mn=new Sherbrooke();
			String city1="MTL";
			String city2="QUE";
		}
		
		//remove them
		String city1="QUE";
		String city2="SHE";
		
		String var=mn.getHashMap(eventType);
		//mn.setHashMap(var, eventID, bookingCapacity);
		char[] ch1 = eventID.toCharArray();
		char[] ch2 = { ch1[0], ch1[1], ch1[2] };
		String bookingServ = new String(ch2);
		String status="book";
	
		if(bookingServ.equalsIgnoreCase(serv)){
			mn.changeHashMap(var, eventID, customerID, status);
		}
		else if(bookingServ.equalsIgnoreCase(city1)) {
			String combinedId=var+eventID+customerID+status;
			int counter=0;
			 ArrayList<String> total=(ArrayList<String>) cm.getusers(customerID);
			 for(int i=0;i<total.size();i++) {
				String temp= total.get(i);
			
				String new1=temp.substring(0, 3);
				if(new1.equals("QUE") || new1.equals("SHE") ) {
					counter++;
				}
				if (counter==3) {
					System.out.println("Sorry you already have 3 appointments");
					System.exit(0);
				}
			 }
			 mn.UDPConnect(6001,combinedId);
			 
			
		}
		else if(bookingServ.equalsIgnoreCase(city2)) {
			String combinedId=var+eventID+customerID+status;
			int counter=0;
			 ArrayList<String> total=(ArrayList<String>) cm.getusers(customerID);
			 for(int i=0;i<total.size();i++) {
				String temp= total.get(i);
			
				String new1=temp.substring(0, 3);
				if(new1.equals("QUE") || new1.equals("SHE") ) {
					counter++;
				}
				if (counter==3) {
					System.out.println("Sorry you already have 3 appointments");
					System.exit(0);
				}
			 }
			mn.UDPConnect(6002,combinedId);
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
		//remove them
				String city1="QUE";
				String city2="SHE";
				
		char[] ch1 = eventID.toCharArray();
		char[] ch2 = { ch1[0], ch1[1], ch1[2] };
		String bookingServ = new String(ch2);
		String status="cancel";
		
		if(bookingServ.equalsIgnoreCase(serv)){
			mn.changeHashMap(var, eventID, customerID, status);
			
		}
		else if(bookingServ.equalsIgnoreCase(city1)) {
			String combinedId=var+eventID+customerID+status;
			 mn.UDPConnect(6001,combinedId);
	
	}	else if(bookingServ.equalsIgnoreCase(city2)) {
		String combinedId=var+eventID+customerID+status;
		 mn.UDPConnect(6002,combinedId);
	}

}

}