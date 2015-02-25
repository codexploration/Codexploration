package net.codexploration.thread.sample3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueuePutter implements Runnable{

	private String _name= "QueuePutter ";
	private int _sleepTime=1000;
	private static volatile boolean _shutDownRequestAll;
	private int number;
	private SampleQueue _sampleQueue;
	private final DateFormat _dateFormat;
	private Date _timestamp;
	
	QueuePutter(int nr, SampleQueue sampleQueue){
		_name += nr;
		_sampleQueue=sampleQueue;
		_dateFormat= new SimpleDateFormat("HH:mm:ss");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!_shutDownRequestAll){
			_sampleQueue.putNr(number);
			_timestamp = new Date();
			System.out.println("["+_dateFormat.format(_timestamp)+"]"+_name+": Putted "+number+" into queue.");
			number++;
			try {
				Thread.sleep(_sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		_timestamp = new Date();
		System.out.println("["+_dateFormat.format(_timestamp)+"] Shutting down "+_name);
	}
	
	public static void shutdown (){
		_shutDownRequestAll=true;
	}
	
}
