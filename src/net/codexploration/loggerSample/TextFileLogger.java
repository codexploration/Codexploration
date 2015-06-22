package net.codexploration.loggerSample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFileLogger extends AbstractLogger{
	private String _filePath;
	
	public TextFileLogger(String filePath){
		super();
		_filePath=filePath;
	}
	public TextFileLogger(int logLevel, String filePath) {
		super(logLevel);
		_filePath=filePath;
	}

	@Override
	public void log(int logLevel, String message) {
		String callerName = Thread.currentThread().getStackTrace()[2].getClassName();
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(_filePath, true)));
			writer.println(getTimestamp()+"["+callerName+"]"+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (writer!=null){
				writer.close();
			}
		}
		
		
	}
}
