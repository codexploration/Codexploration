package net.codexploration.thread.sample3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueueTaker implements Runnable {

	private String _name = "QueueTaker ";
	private int _sleepTime = 1000;
	private static boolean _shutDownRequestAll;
	private SampleQueue _sampleQueue;
	private final DateFormat _dateFormat;
	private Date _timestamp;
	
	QueueTaker(int nr, SampleQueue sampleQueue){
		_name += nr;
		_sleepTime = _sleepTime*nr;
		_sampleQueue=sampleQueue;
		_dateFormat= new SimpleDateFormat("HH:mm:ss");
	}
	
	@Override
	public void run() {
		try {
			// TODO Auto-generated method stub
			while (!_shutDownRequestAll){
				int number = _sampleQueue.takeNr();
				_timestamp = new Date();
				System.out.println("["+_dateFormat.format(_timestamp)+"]"+_name+": Taken "+number+" from queue.");
				try {
					Thread.sleep(_sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (NullPointerException npe){
			_timestamp = new Date();
			System.out.println("["+_dateFormat.format(_timestamp)+"] "+_name+" lost SampleQueue");
		}
		_timestamp = new Date();
		System.out.println("["+_dateFormat.format(_timestamp)+"] Shutting down "+_name);
	}
	

	public static void shutdown(){
		_shutDownRequestAll=true;
	}
}
