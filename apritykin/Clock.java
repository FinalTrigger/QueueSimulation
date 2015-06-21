package apritykin;

public class Clock {
	long currentTime = 0;

	public Clock() {
		
	}
	public long getCurrentTime() {
		return currentTime;
	}
	
	public void updateTime() {
		currentTime++;
	}

}
