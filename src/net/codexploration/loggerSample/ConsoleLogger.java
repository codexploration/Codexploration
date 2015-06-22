package net.codexploration.loggerSample;

public class ConsoleLogger extends AbstractLogger{

	public ConsoleLogger(){
		super();
	}
	public ConsoleLogger (int logLevel){
		super(logLevel);
	}
	
	@Override
	public void log(int logLevel, String message) {
		if (_logLevel<=logLevel){
			String callerName = Thread.currentThread().getStackTrace()[2].getClassName();
			System.out.println (getTimestamp()+"["+callerName+"]"+message);
		}
		
	}

}
