package net.codexploration.loggerSample;

public interface ILogger {
	public final int ALL =1;
	public final int TRACE =2;
	public final int DEBUG =3;
	public final int INFO = 4;
	public final int WARNING =5;
	public final int ERROR =6;
	public final int OFF =7;
	
	public int getLogLevel ();
	public void setLogLevel (int level);
	public void log(int level, String message);

}
