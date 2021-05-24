/**
 *
 *  @author Bielecki Michał S20136
 *
 */

package zad3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
  public static void main(String[] args) throws InterruptedException {
	  //Warehouse warehouse = new Warehouse(400);
	  BlockingQueue<Item> warehouse = new ArrayBlockingQueue<Item>(1000);
      ItemCreator itemCreator = new ItemCreator(warehouse);
      WeightCounter weightCounter = new WeightCounter(warehouse);

      Thread itemCreatorThread = new Thread(itemCreator);
      Thread weightCounterThread = new Thread(weightCounter);

      itemCreatorThread.start();
      	Thread.sleep(100);
      weightCounterThread.start();
  }
}
