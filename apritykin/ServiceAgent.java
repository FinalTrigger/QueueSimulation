package apritykin;

public class ServiceAgent {
	
	Passenger currentPassenger = null;
	private long passengerArrivedTime = 0;
	private long passengerCompleteTime = 0;
	Clock clock = null;
	int maximumServiceTime = 0;
	
	//Sets the current clock
	public ServiceAgent(Clock clock) {
		this.clock = clock;
	}
	//Set maximumServiceTime to s (which is passed in from QueueSimulation.java class)
	void getMaximumServiceTime(int s) {
		maximumServiceTime = s;
	}
	//floating point number that is equal to a random value times the maximumServiceTime
	private int randomServiceTime() {
		float f = (float) Math.random()*maximumServiceTime;
    	return Math.round(f);
	}
	/*
		Below we do the following:
		-Create a new passenger
		-Get the current time of clock for when the passenger arrived.
		-Generate random service time for this passenger.
		-Calculate total time = time arrived + service time
	 */
	public void newPassenger(Passenger passenger){
		currentPassenger = passenger;
		passengerArrivedTime = clock.getCurrentTime();
		int timeToService = randomServiceTime();
		passengerCompleteTime = passengerArrivedTime + timeToService;
		currentPassenger.setServiceTime(timeToService);
	}
	
	public Passenger deletePassenger(){
		Passenger p = currentPassenger;
		currentPassenger = null;
		return p;
	}

	//Boolean to check to see if currentPassenger is not empty, return True for is
	Boolean isOccupied() {
		return (currentPassenger != null);
	}
	//Boolean to check to see
	Boolean isComplete() {
		return isOccupied() && (clock.getCurrentTime() >= passengerCompleteTime);
	}
	
	
}
