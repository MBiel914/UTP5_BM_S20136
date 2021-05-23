package zad3;

public class MyResult {
	private double result;
	public MyResult() {
		result = 0;
	}
	public void add(double value) {
		result+= value;
	}
	public void ShowResult() {
		System.out.println(result);
	}
}
