package apritykin;

import java.util.NoSuchElementException;
// Andrew Pritykin -  NJIT CS610 Summer 2015: Module 3 Programming Assignment 1

public class AirlineQueue implements Queue {
	
	private Object[] array;
    private int size = 0;						// Default size of queue set to 0
    private int currentHead = 0; 				// Index of the item at the front / head.
    private int peopleServed = 0;			    // Integer of how many people serviced in each class
    private long previousArrivalTime = 0;		// Time taken for last passenger to join queue
    private long nextArrivalTime = 0;			// Time taken for following passenger to join queue
    private int averageArrivalRate = 1;			// Placeholder to store / compare the average arrival rate per class
    private int nextItemTail = 0; 				// Index of the next item to be added to array
    private int maxQueueLength = 0;				// Maximum queue length

    Clock clock = null;
	
	public AirlineQueue(int capacity, Clock clock) {
        array = new Object[capacity];
        this.clock = clock;
    }
	
	public void enqueue(Object item) {
        if (size == array.length) {
            throw new IllegalStateException("Maximum queue limit reached. Cannot add.");
        }
        array[nextItemTail] = item;
        // current time we add the passenger to queue
        previousArrivalTime = clock.getCurrentTime();
        // random generate next arrival time for following passenger
        nextArrivalTime = previousArrivalTime + randomArrivalInterval();
        nextItemTail = (nextItemTail + 1) % array.length;
        size++;
        //If the size of the queue is larger than the current max queue length, set the new length to what size is.
        if (size > maxQueueLength) {
        	maxQueueLength = size;
        }
     }
	
	public Object dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from empty queue");
        }
        Object item = array[currentHead];
        array[currentHead] = null;
        currentHead = (currentHead + 1) % array.length;
        size--;
        // peopleServed is incremented only if someone enters the line, and then leaves the line
        peopleServed++;
        return item;
    }
	
	public Object peek() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot peek into empty queue");
        }
        return array[size - 1];
    }
    /*
        Below are public objects that return the following and are used in the QueueSimulation.java class:
        -Size
        -maxLength
        -isEmpty
        -setArrivalRate
        -getNextArrivalTime
        The only private object is randomArrivalInterval which is only used within the AirlineQueue.java class
     */
    public int size() {
        return size;
    }

    public int maxLength() {
        return maxQueueLength;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void setArrivalRate(int s) {
    	averageArrivalRate = s;
    }
    
    public long getNextArrivalTime() {
    	
    	return nextArrivalTime;
    }
    
    private int randomArrivalInterval() {
    	float f = (float) Math.random()*2*averageArrivalRate;
    	return Math.round(f);
    }
}
/*
    Convenience implementation type used in this program is a Queue.
    Resource: https://docs.oracle.com/javase/tutorial/collections/implementations/queue.html
    First In First Out (FIFO) implementation
 */
interface Queue {
    boolean isEmpty();					// Queue is empty True/False.
    Object peek();						// Returns the front item from the queue without removing it from its place.
    int size();							// Returns the number of items in queue.
    void  enqueue(Object x);			// Adds the item to the rear of the queue.
	Object dequeue() throws Exception;	// Removes/Returns the front item form the queue.
}

