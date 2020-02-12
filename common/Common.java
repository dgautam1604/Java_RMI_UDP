package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Common {
	HashMap<String, String> user = new HashMap<String, String>();//event id and customer id
	HashMap<String, Integer> events = new HashMap<String, Integer>();//event id and booking capacity
	
	 public String getevents(String key) {

	      String value = user.get(key);
	      return value;
	 }
	 public void setusers(String k,String l) {

	      user.put(k, l);
	 }
	 public void removeusers(String event) {

	      user.remove(event);
	 }
	 public List<String> getusers(String customerId) {

		 List<String> listOfKeys = null;
		 
			//Check if Map contains the given value
			if(user.containsValue(customerId))
			{
				// Create an Empty List
				listOfKeys = new ArrayList<String>();
						
				// Iterate over each entry of map using entrySet
				for (Map.Entry<String, String> entry : user.entrySet()) 
				{
					// Check if value matches with given value
					if (entry.getValue().equals(customerId))
					{
						// Store the key from entry to the list
						listOfKeys.add(entry.getKey());
					}
				}
			}
			// Return the list of keys whose value matches with given value.
			return listOfKeys;
	 }
	 
	
}