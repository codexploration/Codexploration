package net.codexploration.loggerSample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractLogger implements ILogger {
	protected int _logLevel;
	private Date _timestamp;
	private final DateFormat _dateFormat;
	
	public AbstractLogger () {
		_logLevel=AbstractLogger.ERROR;
		_dateFormat= new SimpleDateFormat("HH:mm:ss");
	}
	public AbstractLogger (int logLevel){
		_logLevel =logLevel;
		_dateFormat= new SimpleDateFormat("HH:mm:ss");
	}
	public int getLogLevel(){
		return _logLevel;
	}
	public void setLogLevel(int logLevel){
		_logLevel=logLevel;
	}
	protected String getTimestamp(){
		_timestamp = new Date();
		return _dateFormat.format(_timestamp);		
	}
	
	public abstract void log (int logLevel, String message); 
}
