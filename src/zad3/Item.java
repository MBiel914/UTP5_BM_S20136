package zad3;

public class Item {
	private int _id;
	private double _weight;
	public Item(int id, double weight) {
		_id = id;
		_weight = weight;
	}
	
	public double GetWeight() {
		return _weight;
	}
}
