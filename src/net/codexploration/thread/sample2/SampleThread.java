package net.codexploration.thread.sample2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleThread implements Runnable {
	//stub for thread name
	private String _name = "SampleThread ";
	//base sleep time
	private int _sleepTime = 1000;
	
	//variables needed to create timestamp later on
	private final DateFormat _dateFormat;
	private Date _timestamp;
	
	//flag will cause all threads to stop if set true
	private static volatile boolean _shutDownRequestAll;
	
	//constructor completes name and sleep time
	SampleThread (int nr){
		_name += nr;
		_sleepTime=nr*_sleepTime;
		_dateFormat= new SimpleDateFormat("HH:mm:ss");
	}
		
	@Override
	public void run() {
		//thread runs as long _shutDownRequestAll is not set true
		while(!_shutDownRequestAll){
			//get actual time
			_timestamp = new Date();
			//print thread name
			System.out.println("["+_dateFormat.format(_timestamp)+"]"+_name);
			
			//sleep
			try {
				Thread.sleep(_sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//print shutdown Message after loop
		_timestamp = new Date();
		System.out.println("["+_dateFormat.format(_timestamp)+"] Shutting down "+_name);
	}
	
	// static method sets _shutDownRequestAll to true. 
	// This will cause any running SampleThreads to end their run method.
	public static void shutdown (){
		_shutDownRequestAll=true;
	}
}
