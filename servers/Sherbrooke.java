package servers;

import implementation.ClientImpl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

import common.Common;

public class Sherbrooke {
	public static HashMap<String, String> eventList = new HashMap<String, String>();
	HashMap<String, Integer> a = new HashMap<String, Integer>();
	HashMap<String, Integer> b = new HashMap<String, Integer>();
	HashMap<String, Integer> c = new HashMap<String, Integer>();
	Common cm = new Common();
	public HashMap<String, String> Muser = new HashMap<String, String>();// event id and customer id

	public static void main(String args[]) throws Exception {
		ClientImpl stub = new ClientImpl();
		Registry registry = LocateRegistry.createRegistry(5557);
		registry.bind("Function", stub);
		System.out.println("Sherbrooke Server is Up & Running");

		eventList.put("Conference", "a");
		eventList.put("Trade Show", "b");
		eventList.put("Seminar", "c");

		DatagramSocket MSocket = null;
		try {
			MSocket = new DatagramSocket(6002);
			// create socket at agreed port
			byte[] buffer = new byte[1000];

			System.out.println("Sherbrooke UDP Server started");
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				MSocket.receive(request);
				Sherbrooke m=new Sherbrooke();
				//byte[] fullid=request.getData();
				String fullid=new String(request.getData());
				
				//print via udp
				if(fullid.equalsIgnoreCase("a") || fullid.equalsIgnoreCase("b") || fullid.equalsIgnoreCase("c")){
					 m.a.entrySet().forEach(entry->{
				            System.out.println(entry.getKey() + " " + entry.getValue());  
				         });
				}
				else{
					String var=fullid.substring(0, 1);
					String eventID=fullid.substring(1, 9);
					String customerID=fullid.substring(9, 17);
					String status=fullid.substring(17);
					
					
					m.changeHashMap(var, eventID, customerID, status);
					
					String done="Appointment booked in another server";
					byte [] msg =done.getBytes();
					DatagramPacket reply = new DatagramPacket(msg, msg.length, request.getAddress(),
							request.getPort());
					MSocket.send(reply);
				}
				
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (MSocket != null)
				MSocket.close();
		}

	}

	public String getHashMap(String evenType) {

		String value = eventList.get(evenType);
		System.out.println("List for event type "+value);
		if(value.equalsIgnoreCase("a")){
			a.entrySet().forEach(entry->{
	            System.out.println(entry.getKey() + " " + entry.getValue());  
	         });
		}
		else if(value.equalsIgnoreCase("b")){
			b.entrySet().forEach(entry->{
	            System.out.println(entry.getKey() + " " + entry.getValue());  
	         });
		}
		else if(value.equalsIgnoreCase("c")){
			c.entrySet().forEach(entry->{
	            System.out.println(entry.getKey() + " " + entry.getValue());  
	         });
		}

		return value;

	}

	public void addHashMap(String var, String key, int Value) {
		if (var == "a") {
			if (a.get(key) != null) {

				int val = a.get(key);
				a.replace(key, val++);
				System.out.print("Value updated for " + key + "to " + val);
			} else {
				a.put(key, Value);
				System.out.print("Added Successfully");
			}
		} else if (var == "b") {
			if (b.get(key) != null) {

				int val = b.get(key);
				b.replace(key, val++);
				System.out.print("Value updated for " + key + "to " + val);
			} else {
				b.put(key, Value);
				System.out.print("Added Successfully");
			}
		} else if (var == "c") {
			if (c.get(key) != null) {

				int val = c.get(key);
				c.replace(key, val++);
				System.out.print("Value updated for " + key + "to " + val);
			} else {
				c.put(key, Value);
				System.out.print("Added Successfully");
			}
		}

	}

	public void removeHashMap(String var, String key) {
		if (var == "a") {
			if (a.get(key) != null) {
				if(cm.getevents(key)!=null){
					String vvar=cm.getevents(key);
					Sherbrooke m1=new Sherbrooke();
					char[] c=key.toCharArray();
					String dat=String.valueOf(c[4]+c[5]);
					int i=Integer.parseInt(String.valueOf(dat));
					i++;
					for(;i<30;i++){
						if(i<10)
						key=key.substring(0, 4)+"0"+ Integer.toString(i)+key.substring(7);
						else
							key=key.substring(0, 4)+ Integer.toString(i)+key.substring(7);
						
						if(a.get(key) != null){
							m1.changeHashMap(var, key, vvar, "book");
							cm.removeusers(key);
					}
					}		
					
				}
				a.remove(key);
				System.out.print("Removed " + key);
			} else {

				System.out.print("No record");
			}
		} else if (var == "b") {
			if (b.get(key) != null) {
				b.remove(key);
				System.out.print("Removed " + key);
			} else {

				System.out.print("No record");
			}
		} else if (var == "c") {
			if (c.get(key) != null) {
				c.remove(key);
				System.out.print("Removed " + key);
			} else {

				System.out.print("No record");
			}
		}

	}
	//function to update customer wrt events
	public void changeHashMap(String var, String key, String customerId,String status) {
		if (var == "a") {
			//if event id exists
			if (a.get(key) != null) {
				int val = a.get(key);//get the value
				if (val > 0 && status.equals("book")) //check what needs to be done
					{
					a.replace(key, val--);//update vlue
					System.out.print("Booked Event " + key);
					Muser.put(key,customerId);//update in local hashmap
				 cm.setusers(key,customerId); Muser.put(key,customerId);//update in generic hashmap
					 
				} else if(status.equals("cancel")){
					a.replace(key, val++);
					System.out.print("Cancelled Event " + key);
					Muser.remove(key);
					cm.removeusers(key);
				}
				
				else
					System.out.print("Unsuccessful");

			} else {

				System.out.print("Event is not available");
			}
		} else if (var == "b") {
			if (b.get(key) != null) {

				int val = b.get(key);
				if (val > 0) {
					b.replace(key, val--);
					System.out.print("Booked Event " + key);
					/*
					 * cm.setusers(key,customerId); Muser.put(key,customerId);
					 */
				} else
					System.out.print("No space available for event");

			} else {

				System.out.print("Event is not available");
			}
		} else if (var == "c") {
			if (c.get(key) != null) {

				int val = c.get(key);
				if (val > 0) {
					c.replace(key, val--);
					System.out.print("Booked Event " + key);
					/*
					 * cm.setusers(key,customerId); Muser.put(key,customerId);
					 */

				} else
					System.out.print("No space available for event");

			} else {

				System.out.print("Event is not available");
			}
		}

	}
	public void UDPConnect(int serverPort,String combinedId) {
		DatagramSocket aSocket = null;
		try {
			System.out.println("Sherbrooke client started");
			aSocket = new DatagramSocket();    
			byte [] message = combinedId.getBytes();
			
			InetAddress aHost = InetAddress.getByName("localhost");
			
			//int serverPort = this.;		                                                 
			//DatagramPacket request =new DatagramPacket(m,  args[0].length(), aHost, serverPort);
			DatagramPacket request =new DatagramPacket(message,  combinedId.length(), aHost, serverPort);
			
		
			aSocket.send(request);	
			System.out.println("Request message sent via UDP"+ new String(request.getData()));
			
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));	
		}
		catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e){System.out.println("IO: " + e.getMessage());
		}
		finally {if(aSocket != null) aSocket.close();}
	}
	
	/*public void display(String var){
		var.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());  
         });
	
	}*/
}