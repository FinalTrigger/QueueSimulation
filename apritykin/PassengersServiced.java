package apritykin;

public class PassengersServiced {

	private Passenger[] array;
	int totalPassengers = 0;
	int firstClassPassengers = 0;
	int coachClassPassengers = 0;
	int arrayLength = 10000;

    //Create a new array for passengers that are serviced
	public PassengersServiced() {
		array = new Passenger[arrayLength];
	}
	
	void addPassenger(Passenger passenger) {
		
		try {
			array[totalPassengers] = passenger;
		} catch(Exception e) {
			//If the array is filled, increase the size of the second array and move the elements to new array.
			//Once new array is filled, populate the original array with the new content
			arrayLength =  2*arrayLength;
			Passenger[] array2 = new Passenger[arrayLength];
			for (int i = 0; i < array.length; i++) {
				array2[i] = array[i];
			}
			array = array2;
		}
		//Begin to increment the amount of passengers, then filter them into either first class or coach
		totalPassengers++;
		if (passenger.isFirstClass()) {
			firstClassPassengers++;
		} else { 
			coachClassPassengers++;
		}
	}

	//Once we have the total amount of passengers, for each passenger service time in the array
	//we calculate the average service time and return it as a long.
	long averageServiceTime() {
		long sum = 0;
		for (int i = 0; i < totalPassengers - 1; i++) {
			sum += array[i].getServiceTime();
		}
		return sum/ totalPassengers;
	}

	//Once we calculate the total amount of passengers, for each passenger in the array, we compare all the service
	//times to find the maximum within the array and return it as a long.
	long maximumServiceTime() {
		long currentMax = 0;
		for (int i = 0; i < totalPassengers - 1; i++) {
			if (array[i].getServiceTime() > currentMax) {
				currentMax = array[i].getServiceTime();
			}
		}
		return currentMax;
	}
	//Calls to return the amount of first class and coach class passengers
	int firstClassPassengersNumber() {
		return firstClassPassengers;
	}
	int coachClassPassengersNumber() {
		return coachClassPassengers;
	}
	
}
