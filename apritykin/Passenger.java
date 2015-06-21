package apritykin;

public class Passenger {
	long serviceTime = 0;
	boolean isFirstClass = false;

	//Check to see if a passenger is first class and if so set the boolean
	public Passenger(boolean isFirstClass) {	
		this.isFirstClass = isFirstClass;
	}
	//Get the current service time and return the value as a long
	long getServiceTime() {
		return serviceTime;
	}
	// Set the service time as the current time
	void setServiceTime(long time) {
		serviceTime = time; 
	}
	//Return the current value of first class boolean
	boolean isFirstClass() {
		return isFirstClass;
	}
	

}
