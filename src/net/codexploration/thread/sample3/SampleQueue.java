package net.codexploration.thread.sample3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SampleQueue {
	//list of integer values
	private Queue<Integer> _numbers = new LinkedList<Integer>();
	//ReentrantLock used to synchronize methods
	private final Lock _lock = new ReentrantLock();
	//two conditions for notifying threads
	private final Condition _queueAvailable = _lock.newCondition();
	private final Condition _newNumber = _lock.newCondition();
	//length of the queue
	private final int _queueLength=10;
	
	//Puts an integer value into the queue
	public void putNr(int number){
		//Aquire lock
		_lock.lock();
		try{
			//Wait for queue to be empty
			if (_numbers.size()>= _queueLength){
				System.out.println("Queue full. Waiting...");
				//blocks thread until queueAvailable signal is risen
				_queueAvailable.await();
			}
			//add number to the list
			_numbers.add(number);
			System.out.println("Queue length: "+_numbers.size());
			//signal all waiting threads, that new number is available
			_newNumber.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//release the lock
			_lock.unlock();
		}
	}
	
	//takes an integer value from the queue
	public int takeNr (){
		//aquire lock
		_lock.lock();
		int number = 0;
		try{
			//wait for at least one value in queue
			if(_numbers.size()==0){
				System.out.println("Queue empty. Waiting...");
				//blocks thread until newNumber signal is risen
				_newNumber.await();
			}
			//takes the head of queue
			number = _numbers.poll();
			System.out.println("Queue length: "+_numbers.size());
			//signal all waiting threads, that queue is not full
			_queueAvailable.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//release the lock
			_lock.unlock();
		}
		return number;
	}
	
	/*Shutdown Method.
	 * Aquire lock. Set numbers to null. signal all waiting threads.
	 */
	public void shutdown(){
		_lock.lock();
		_numbers=null;
		_queueAvailable.signalAll();
		_newNumber.signalAll();
		_lock.unlock();
	}

}
