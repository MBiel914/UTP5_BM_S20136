package zad3;

import java.util.concurrent.BlockingQueue;

public class WeightCounter implements Runnable{
	BlockingQueue<Item> _warehouse;
	private double result = 0;
	private int counter = 0, power = 1;
	
    public WeightCounter(BlockingQueue<Item> warehouse){
        this._warehouse = warehouse;
    }
    @Override
    public void run(){
    	while(!_warehouse.isEmpty()) {
    		try {
				result += _warehouse.take().GetWeight();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			  counter++;

			  if(counter == power*100) {
				  System.out.println("policzono wage " + power*100 + " towarów");
				  power++;
				  try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			  }
    	}
    	
    	System.out.println(result);
    }
}
