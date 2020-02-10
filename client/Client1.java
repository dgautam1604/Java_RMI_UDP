package client;

import implementation.Customer;
import implementation.Manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client1 {

	public static void main(String args[]) throws Exception {
		
		int ConnectionNumber=5000;//default connection
		System.out.print("Enter your id ");
		Scanner sc = new Scanner(System.in);
		String id = sc.nextLine().toUpperCase();
		char[] ch = id.toCharArray();
		
		//Unique id in a string
		char[] ch2 = { ch[4], ch[5], ch[6], ch[7] };
		String id1 = new String(ch2);// validity of id needs to be checked

		try {
			int i = Integer.parseInt(id1.trim());
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
			System.exit(0);
		}

		// check length of ID
		if (ch.length != 8) {
			System.out.print("Invalid id ");
			System.exit(0);
		} 
		
		else 
		{
			//Check if its Manager or Client
			if (ch[3] == 'M') {
				System.out.print("Welcome Manager " + id1);
			} else if (ch[3] == 'C') {
				System.out.print("Welcome Customer " + id1);

			}
			
			//To identify server
			char[] ch1 = { ch[0], ch[1], ch[2] };
			String serv = new String(ch1);// server identified

			if (serv.equalsIgnoreCase("MTL")) {
				System.out.print("Welcome to Montreal Server");
				ConnectionNumber=5555;

			} else if (serv.equalsIgnoreCase("QUE")) {
				System.out.print("Welcome to Quebec Server");
				ConnectionNumber=5556;
				
			} else if (serv.equalsIgnoreCase("SHE")) {
				System.out.print("Welcome to Sherbrooke Server");
				ConnectionNumber=5557;
				
			} else {
				System.out.print("Wrong Input ");
				System.exit(0);
			}
			
			//Find out the operation that should be done
			System.out.println("What would you like to do today?(Select 1,2,3)");
			if(ch[3] == 'M'){
				System.out.println("1.addEvent"+"\n"+
						"2.removeEvent"+"\n"+
						"3.listEventAvailability");
			}
			else if (ch[3] == 'C') {
				System.out.println("1.bookEvent"+"\n"+
						"2.getBookingSchedule"+"\n"+
						"3.cancelEvent");
			}
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			int m=br.read();
			
			if (ch[3] == 'M') {
				Registry registry = LocateRegistry.getRegistry(ConnectionNumber);
				Manager m1 = (Manager) registry.lookup("Function");
				if(m==1){
					System.out.println("Enter eventID, eventType, bookingCapacity in the same order");
					Scanner scin=new Scanner(System.in);
					m1.addEvent(scin.next(), scin.next(), scin.nextInt());
				}
				else if(m==2){
					System.out.println("Enter eventID, eventType in the same order");
					Scanner scin=new Scanner(System.in);
					m1.removeEvent(scin.next(), scin.next());
					
				}
				else if(m==3){
					System.out.println("Enter eventType in the same order");
					Scanner scin=new Scanner(System.in);
					m1.listEventAvailability(scin.next());

				}
				else{
					System.out.println("Wrong Inuput");
					System.exit(0);
				}
				
				
			} else if (ch[3] == 'C') {
				Registry registry = LocateRegistry.getRegistry(ConnectionNumber);
				Customer c1 = (Customer) registry.lookup("Function");
				
				if(m==1){
					System.out.println("Enter customerID, eventID, eventType in the same order");
					Scanner scin=new Scanner(System.in);
					c1.bookEvent(scin.next(), scin.next(), scin.next());

				}
				else if(m==2){
					System.out.println("Enter customerID");
					Scanner scin=new Scanner(System.in);
					c1.getBookingSchedule(scin.next());
					
									
				}
				else if(m==3){
					System.out.println("Enter customerID, eventID, eventType in the same order");
					Scanner scin=new Scanner(System.in);
					c1.cancelEvent(scin.next(), scin.next(), scin.next());
					
				}
				else{
					System.out.println("Wrong Input");
					System.exit(0);
				}

			}
			
			
			/*
			 * System.out.print("Enter a number: "); a=sc.nextInt();
			 * System.out.print("Enter a number: "); b=sc.nextInt();
			 * 
			 * n = obj.add(a, b); System.out.println("Addition is : " + n);
			 */
		}

	}

}
