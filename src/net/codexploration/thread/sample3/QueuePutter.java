package net.codexploration.thread.sample3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueuePutter implements Runnable{
	//stub for thread name
	private String _name= "QueuePutter ";
	//base sleep time
	private int _sleepTime=1000;
	//flag will cause all threads to stop if set true
	private static volatile boolean _shutDownRequestAll;
	//int value to add
	private int _number;
	//queue to add number to
	private SampleQueue _sampleQueue;
	//variables needed to create timestamp
	private final DateFormat _dateFormat;
	private Date _timestamp;
	
	//constructor completes name and sets Queue
	QueuePutter(int nr, SampleQueue sampleQueue){
		_name += nr;
		_sampleQueue=sampleQueue;
		_dateFormat= new SimpleDateFormat("HH:mm:ss");
	}
	@Override
	public void run() {
		try{
		// thread runs as long _shutDownRequestAll is not set true
			while (!_shutDownRequestAll){
				//put number to queue
				_sampleQueue.putNr(_number);
				_timestamp = new Date();
				System.out.println("["+_dateFormat.format(_timestamp)+"]"+_name+": Putted "+_number+" into queue.");
				_number++;
				try {
					Thread.sleep(_sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		/*
		 * catches NullPointerException if queue is shutdown. This happens when shutdown method of SampleQueue is called
		 * while thread is waiting for signal.
		 */
		catch (NullPointerException npe){
			_timestamp = new Date();
			System.out.println("["+_dateFormat.format(_timestamp)+"] "+_name+" lost SampleQueue");
		}
		_timestamp = new Date();
		System.out.println("["+_dateFormat.format(_timestamp)+"] Shutting down "+_name);
	}
	
	/* static method sets _shutDownRequestAll to true. 
	* This will cause any running SampleThreads to end their run method.
	*/
	static void shutdown (){
		_shutDownRequestAll=true;
	}
	
}
