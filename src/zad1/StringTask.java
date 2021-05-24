package zad1;

enum TaskState{
	CREATED
	, RUNNING
	, ABORTED
	, READY
}

public class StringTask implements Runnable {
	public volatile TaskState currentTaskState;
	Thread thread;
	private boolean currentTaskFlag = false;
	public volatile String result = "";
	public volatile String _userTxt;
	public volatile int _userCounter;
	
	public StringTask(String userTxt, int userCounter) {
		currentTaskState = TaskState.CREATED;
		_userTxt = userTxt;
		_userCounter = userCounter;
	}
	public StringTask(StringTask task) {
		this(task._userTxt, task._userCounter);
	}
	@Override
	public void run() {
		int counter;
		for(counter = 0; counter < _userCounter && !thread.isInterrupted(); counter++)
			result += _userTxt;
		
		if (counter == _userCounter) {
			currentTaskState = TaskState.READY;		
			currentTaskFlag = true;
		}
	}
	public String getResult() {
		return result;
	}
	public TaskState getState() {
		return currentTaskState;
	}
	public void start() {
		currentTaskState = TaskState.RUNNING;
        thread = new Thread(this);
        thread.start();
	}
	public void abort() {
		thread.interrupt();
		currentTaskState = TaskState.ABORTED;
		currentTaskFlag = true;
	}
	public boolean isDone() {
		return currentTaskFlag;
	}
}
