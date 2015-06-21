package apritykin;
import java.util.Scanner;


public class QueueSimulation {
	//Declaration of class variables for the user program below:
	static Clock clock = new Clock();
	//Create new queues for both first class and coach
	static AirlineQueue firstClassQueue = new AirlineQueue(1000, clock) {};
	static AirlineQueue coachClassQueue = new AirlineQueue(1000, clock) {};
	static int durationLength = 0;
	//As stated in the assignment description:
	//There are two service stations for first class, and three for coach.
	static ServiceAgent firstClassServiceStation1 = new ServiceAgent(clock) {};
	static ServiceAgent firstClassServiceStation2 = new ServiceAgent(clock) {};
	static ServiceAgent coachClassServiceStation1 = new ServiceAgent(clock) {};
	static ServiceAgent coachClassServiceStation2 = new ServiceAgent(clock) {};
	static ServiceAgent coachClassServiceStation3 = new ServiceAgent(clock) {};
	static PassengersServiced passengersServiced = new PassengersServiced() {};
	//Variables for rate and service time for first class(1) and coach(2)
	static int rate1 = 0;
	static int rate2 = 0;
	static int service1 = 0;
	static int service2 = 0;
	//Empty String to store user input for the program
	static String inputInfo = "";


	//Main operation for the queue simulation
	public static void main(String[] args) throws Exception {
		//Console output / input that is prompted to the user (below)
		//** If user enters anything but an integer, the program will crash / exit **

		//Introduction
		System.out.println("*** Welcome to Airline Queue Program! *** \n\n");
		System.out.println("Please enter the duration of the total queue runtime in seconds.");
		Scanner input = new Scanner(System.in);
		inputInfo = input.nextLine();
		durationLength = Integer.parseInt(inputInfo);

		System.out.println("Next enter the average rate of arrival for first class.");
		inputInfo = input.nextLine();
		rate1 = Integer.parseInt(inputInfo);
		
		System.out.println("Now enter the average rate of arrival for coach class.");
		inputInfo = input.nextLine();
		rate2 = Integer.parseInt(inputInfo);
		
		System.out.println("Enter the maximum service time for first class.");
		inputInfo = input.nextLine();
		service1 = Integer.parseInt(inputInfo);
		
		System.out.println("Lastly enter the maximum service time for coach class.");
		inputInfo = input.nextLine();
		service2 = Integer.parseInt(inputInfo);
		
		//Set the arrival rate for each of the queues (First and Coach class)
		firstClassQueue.setArrivalRate(rate1);
		coachClassQueue.setArrivalRate(rate2);
		//Use the getMaximumServiceTime method to get the data on each of the service stations for First and Coach class
		firstClassServiceStation1.getMaximumServiceTime(service1);
		firstClassServiceStation2.getMaximumServiceTime(service1);
		coachClassServiceStation1.getMaximumServiceTime(service2);
		coachClassServiceStation2.getMaximumServiceTime(service2);
		coachClassServiceStation3.getMaximumServiceTime(service2);

		//Take user input data and begin processing
		runQueue();

	}
	// The main method that puts the program together
	public static void runQueue() {
		//Run this method until the current time is less than the user defined duration length.
		while (clock.getCurrentTime() < durationLength) {
			addToQueue();
			queueToServiceAgent();
			passengersCompletedToBoardPlane();
			clock.updateTime();
		}
		outputResults();
	}
	
	public static void addToQueue(){
		
		// check FirstClassQueue, next arrival time and add new passenger if appropriate
		if (clock.getCurrentTime() >= firstClassQueue.getNextArrivalTime()) {
			Passenger p = new Passenger(true);
			firstClassQueue.enqueue(p);
		}
		// check CoachQueue, next arrival time and add new passenger if appropriate
		if (clock.getCurrentTime() >= coachClassQueue.getNextArrivalTime()) {
			Passenger p = new Passenger(false);
			coachClassQueue.enqueue(p);
		}
		
	}

	public static void queueToServiceAgent(){
		//For both First and Coach, if Agent is available move the passenger from the queue to the agent
		//This is applied to all the service stations if/else statements below.

		//If the service station is not occupied & the queue is not empty
		if (firstClassServiceStation1.isOccupied() == false) {
			if (! firstClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) firstClassQueue.dequeue();
				firstClassServiceStation1.newPassenger(passenger);
			}
			//If there is a passenger in the queue
			else if (! coachClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) coachClassQueue.dequeue();
				firstClassServiceStation1.newPassenger(passenger);
			}
		}
		if (firstClassServiceStation2.isOccupied() == false) {
			if (! firstClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) firstClassQueue.dequeue();
				firstClassServiceStation2.newPassenger(passenger);
			} else if (! coachClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) coachClassQueue.dequeue();
				firstClassServiceStation2.newPassenger(passenger);
			}
		}
		if (coachClassServiceStation1.isOccupied() == false) {
			if (! coachClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) coachClassQueue.dequeue();
				coachClassServiceStation1.newPassenger(passenger);
			} else if (! firstClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) firstClassQueue.dequeue();
				coachClassServiceStation1.newPassenger(passenger);
			}
		}
		if (coachClassServiceStation2.isOccupied() == false) {
			if (! coachClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) coachClassQueue.dequeue();
				coachClassServiceStation2.newPassenger(passenger);
			} else if (! firstClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) firstClassQueue.dequeue();
				coachClassServiceStation2.newPassenger(passenger);
			}
		}
		if (coachClassServiceStation3.isOccupied() == false) {
			if (! coachClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) coachClassQueue.dequeue();
				coachClassServiceStation3.newPassenger(passenger);
			} else if (! firstClassQueue.isEmpty()) {
				Passenger passenger = (Passenger) firstClassQueue.dequeue();
				coachClassServiceStation3.newPassenger(passenger);
			}
		}
		
	}
	
	public static void passengersCompletedToBoardPlane(){
		//When a passenger is serviced, move them to the new list of passengersServiced
		
		if (firstClassServiceStation1.isComplete()) {
			Passenger passenger = firstClassServiceStation1.deletePassenger();
			passengersServiced.addPassenger(passenger);
		}else{
			//Placeholder for possible error handle
		}
		if (firstClassServiceStation2.isComplete()) {
			Passenger passenger = firstClassServiceStation2.deletePassenger();
			passengersServiced.addPassenger(passenger);
		}else{
			//Placeholder for possible error handle
		}
		if (coachClassServiceStation1.isComplete()) {
			Passenger passenger = coachClassServiceStation1.deletePassenger();
			passengersServiced.addPassenger(passenger);
		}else{
			//Placeholder for possible error handle
		}
		if (coachClassServiceStation2.isComplete()) {
			Passenger passenger = coachClassServiceStation2.deletePassenger();
			passengersServiced.addPassenger(passenger);
		}else{
			//Placeholder for possible error handle
		}
		if (coachClassServiceStation3.isComplete()) {
			Passenger passenger = coachClassServiceStation3.deletePassenger();
			passengersServiced.addPassenger(passenger);
		}else{
			//Placeholder for possible error handle
		}
		
	}
	
	public static void outputResults(){
		/*
			Statistics generated for passengers include:
			average service time
			maximum service time
			number served in each class
			maximum queue length
		 */
		System.out.println("\n" + "----------------------------------------\n" + "Results of Airline Queue:\n");
		System.out.println("The average service time: " + passengersServiced.averageServiceTime() + " seconds");
		System.out.println("The maximum service time: " + passengersServiced.maximumServiceTime() + " seconds");
		System.out.println("Number of First class passengers served: " + passengersServiced.firstClassPassengersNumber() + " passengers");
		System.out.println("Number of Coach class passengers served: " + passengersServiced.coachClassPassengersNumber() + " passengers");
		System.out.println("First class maximum queue length: " + firstClassQueue.maxLength());
		System.out.println("Coach class maximum queue length: " + coachClassQueue.maxLength());
	}
	
}
