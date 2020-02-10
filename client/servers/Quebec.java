package servers;

import implementation.ClientImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;


public class Quebec {
	public static void main(String args[]) throws Exception
	{
		ClientImpl obj = new ClientImpl();
		Registry registry = LocateRegistry.createRegistry(5556);
		//registry.bind("Addition", obj);
		System.out.println("Quebec Server is Up & Running");
		
		HashMap<String, String> eventList= new HashMap<String, String>();
		HashMap<String, String> a = new HashMap<String, String>();
		HashMap<String, String> b = new HashMap<String, String>();
		HashMap<String, String> c = new HashMap<String, String>();
		
		eventList.put("Conference", "a");
		eventList.put("Trade Show", "b");
		eventList.put("Seminar", "c");
	}
}
