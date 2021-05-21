package zad1;

enum TaskState{
	CREATED
	, RUNNING
	, ABORTED
	, READY
}

public class StringTask implements Runnable {
	private TaskState currentTaskState;
	Thread thread;
	private boolean currentTaskFlag = false;
	private String result = "";
	private String _userTxt;
	private int _userCounter;
	
	
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
		for(int counter = 0; counter < _userCounter && !thread.isInterrupted(); counter++)
			result += _userTxt;
		currentTaskFlag = true;
		currentTaskState = TaskState.READY;
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
		currentTaskState = TaskState.ABORTED;
        thread.interrupt();
        currentTaskFlag = true;
	}
	public boolean isDone() {
		return currentTaskFlag;
	}
}
