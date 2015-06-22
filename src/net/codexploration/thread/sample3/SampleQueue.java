package net.codexploration.thread.sample3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SampleQueue {
	private Queue<Integer> _numbers = new LinkedList<Integer>();
	private final Lock _lock = new ReentrantLock();
	private final Condition _queueAvailable = _lock.newCondition();
	private final Condition _newNumber = _lock.newCondition();
	private final int _queueLength=10;
	
	public void putNr(int number){
		_lock.lock();
		try{
			while (_numbers.size()>= _queueLength){
				System.out.println("Queue full. Waiting...");
				_queueAvailable.await();
			}
			_numbers.add(number);
			System.out.println("Queue length: "+_numbers.size());
			_newNumber.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			_lock.unlock();
		}
	}
	
	public int takeNr (){
		_lock.lock();
		int number = 0;
		try{
			while(_numbers.size()==0){
				System.out.println("Queue empty. Waiting...");
				_newNumber.await();
			}
			number = _numbers.poll();
			System.out.println("Queue length: "+_numbers.size());
			_queueAvailable.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			_lock.unlock();
		}
		return number;
	}
	
	public void shutdown(){
		_lock.lock();
		_numbers=null;
		_queueAvailable.signalAll();
		_newNumber.signalAll();
		_lock.unlock();
	}

}
