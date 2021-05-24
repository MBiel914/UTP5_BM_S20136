package zad3;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Warehouse {
	BlockingQueue<Item> _items;
	static int _counter;
	static int _counterGet;
	static boolean _flag = false;
	Random random = new Random();
	private boolean _allItemsAdded = false;
	
	public Warehouse(int size){
        if(size > 1)
        	_items = new ArrayBlockingQueue<Item>(size);
        else
            throw new IllegalArgumentException("Wrong size of array.");
    }
	synchronized public void AllItemsAdded() {
		_allItemsAdded = true;
	}
	synchronized public boolean IfAllItemsAdded() {
		return _allItemsAdded;
	}
	synchronized void addItem(Item newItem) throws InterruptedException {
		while (_flag) {
            wait();
        }

		_flag = true;

        _items.put(newItem);

        notify();
        _flag = false;
	}
	synchronized double getWeight() throws InterruptedException{
        while (_flag) {
            wait();
        }
        
        notify();
        _flag = false;
        return _items.take().GetWeight();
    }
}
