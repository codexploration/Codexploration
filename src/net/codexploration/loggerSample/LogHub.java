package net.codexploration.loggerSample;

import java.util.LinkedList;

public class LogHub {
	private LinkedList<AbstractLogger>_loggers = new LinkedList<AbstractLogger>();
	
	public void register(AbstractLogger logger){
		_loggers.add(logger);
	}
	
	public void unregister (AbstractLogger logger){
		_loggers.remove(logger);
	}
	
	public void log (int logLevel, String message){
		for (AbstractLogger logger:_loggers){
			logger.log(logLevel, message);
		}
	}

}
