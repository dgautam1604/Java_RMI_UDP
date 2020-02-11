package servers;

import implementation.ClientImpl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

import common.Common;


public class Sherbrooke {
	public static HashMap<String, String> eventList= new HashMap<String, String>();
	HashMap<String, Integer> a = new HashMap<String, Integer>();
	HashMap<String, Integer> b = new HashMap<String, Integer>();
	HashMap<String, Integer> c = new HashMap<String, Integer>();
	Common cm=new Common();
	
	public static void main(String args[]) throws Exception
	{
		ClientImpl stub = new ClientImpl();
		Registry registry = LocateRegistry.createRegistry(5555);
		registry.bind("Function", stub);
		System.out.println("Montreal Server is Up & Running");
		
		
		
		eventList.put("Conference", "a");
		eventList.put("Trade Show", "b");
		eventList.put("Seminar", "c");
		
	  	DatagramSocket SSocket = null;
			try{
		    	SSocket = new DatagramSocket(6789);
						// create socket at agreed port
				byte[] buffer = new byte[1000];
				
				System.out.println("Montreal Server started");
	 			while(true){
	 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
	 				SSocket.receive(request);     
	    			DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
	    				request.getAddress(), request.getPort());
	    			SSocket.send(reply);
	    		}
			}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
			}catch (IOException e) {System.out.println("IO: " + e.getMessage());
			}finally {if(SSocket != null) SSocket.close();}
		
		
	}
	 public String getHashMap(String keyNameField) {

	      String value = eventList.get(keyNameField);

	      return value;

	   }
	 
	 public void addHashMap(String var,String key,int Value) {
		if(var=="a"){
			if(a.get(key) != null){
			 
				int val=a.get(key);
				a.replace(key, val++);
				System.out.print("Value updated for "+key +"to "+val );
			}
			else{
				a.put(key,Value); 
				System.out.print("Added Successfully");
			}
		 }
		 else if(var=="b"){
			 if(b.get(key) != null){
				 
					int val=b.get(key);
					b.replace(key, val++);
					System.out.print("Value updated for "+key +"to "+val );
				}
				else{
					b.put(key,Value); 
					System.out.print("Added Successfully");
				} 
		 }
		 else if(var=="c"){
			 if(c.get(key) != null){
				 
					int val=c.get(key);
					c.replace(key, val++);
					System.out.print("Value updated for "+key +"to "+val );
				}
				else{
					c.put(key,Value); 
					System.out.print("Added Successfully");
				}
		 }
		
	   

	   }
	public void removeHashMap(String var, String key) {
		if(var=="a"){
			if(a.get(key) != null){
				a.remove(key);
				System.out.print("Removed "+key);
			}
			else{
				 
				System.out.print("No record");
			}
		 }
		 else if(var=="b"){
				if(b.get(key) != null){
					b.remove(key);
					System.out.print("Removed "+key);
				}
				else{
					 
					System.out.print("No record");
				}
			 }
		 else if(var=="c"){
				if(c.get(key) != null){
					c.remove(key);
					System.out.print("Removed "+key);
				}
				else{
					 
					System.out.print("No record");
				}
			 }
		
	}
	
	public void reduceHashMap(String var,String key, String customerId) {
		if(var=="a"){
			if(a.get(key) != null){
			 
				int val=a.get(key);
				if(val>0){
					a.replace(key, val--);
					System.out.print("Booked Event "+key );
					cm.setusers(key,customerId);
				}
				else
					System.out.print("No space available for event" );
				
			}
			else{
				
				System.out.print("Event is not available");
			}
		 }
		 else if(var=="b"){
				if(b.get(key) != null){
					 
					int val=b.get(key);
					if(val>0){
						b.replace(key, val--);
						System.out.print("Booked Event "+key );
						cm.setusers(key,customerId);
					}
					else
						System.out.print("No space available for event" );
					
				}
				else{
					
					System.out.print("Event is not available");
				}
			 }
		 else if(var=="c"){
				if(c.get(key) != null){
					 
					int val=c.get(key);
					if(val>0){
						c.replace(key, val--);
						System.out.print("Booked Event "+key );
						cm.setusers(key,customerId);
						
					}
					else
						System.out.print("No space available for event" );
					
				}
				else{
					
					System.out.print("Event is not available");
				}
			 }
		
	   

	   }
}