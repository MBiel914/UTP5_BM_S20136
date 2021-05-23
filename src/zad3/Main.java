/**
 *
 *  @author Bielecki Michał S20136
 *
 */

package zad3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws InterruptedException {
	  List<Item> items = new ArrayList<Item>();
	  MyResult result = new MyResult();
	  
	  Runnable createItemsRunnable = () -> {
		  synchronized(items) {
			  try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Towary.txt"))) {
				    String line;
				    int counter = 0, power = 2;
				    
				    while ((line = br.readLine()) != null) {
				    	items.add(new Item(Integer.parseInt(line.split(" ")[0]), Double.parseDouble(line.split(" ")[1])));
				    	counter++;
				    	
				    	if (counter == power*100) {
				    		System.out.println("utworzono " + power*100 + " obiektów");
				    		power += 2;
				    	}
				    }
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		  }
	  };
	  Thread createItemsThread = new Thread(createItemsRunnable);
	  createItemsThread.start();
	  
	  Runnable addWeightItems = () -> {
		  int power = 1, counter = 0;
		  
		  synchronized(items) {
			  for(Item item : items) {
				  result.add(item.GetWeight());
				  counter++;

				  if(counter == power*100) {
					  System.out.println("policzono wage " + power*100 + "towarów");
					  power++;
				  }
			  }
			  
			  result.ShowResult();
		  }
	  };
	  Thread addWeightThread = new Thread(addWeightItems);
	  addWeightThread.start();
  }
}
