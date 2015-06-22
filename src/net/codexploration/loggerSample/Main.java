package net.codexploration.loggerSample;

public class Main {
	
	public static void main (String [] args){
		ConsoleLogger consLog = new ConsoleLogger(AbstractLogger.ALL);
		TextFileLogger fileLog = new TextFileLogger(AbstractLogger.ALL, "test.log");
		LogHub logHub = new LogHub();
		
		logHub.register(consLog);
		logHub.register(fileLog);
		
		logHub.log(AbstractLogger.ERROR,"Error");
		logHub.log(AbstractLogger.INFO, "Info");
		logHub.log(AbstractLogger.ALL, "file");
		logHub.unregister(fileLog);
		logHub.log(AbstractLogger.ALL,"file2");
		
	}
}
