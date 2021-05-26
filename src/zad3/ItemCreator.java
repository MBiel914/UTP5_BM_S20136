package zad3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ItemCreator implements Runnable{
    BlockingQueue<Item> _warehouse;
    
    public ItemCreator(BlockingQueue<Item> warehouse){
        this._warehouse = warehouse;
    }
    @Override
    public void run(){
    	try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Towary.txt"))) {
		    String line;
		    int counter = 0, power = 2;
		    
		    while ((line = br.readLine()) != null) {
		    	_warehouse.put(new Item(Integer.parseInt(line.split(" ")[0]), Double.parseDouble(line.split(" ")[1])));
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
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}