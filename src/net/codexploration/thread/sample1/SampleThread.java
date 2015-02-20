package net.codexploration.thread.sample1;

public class SampleThread implements Runnable{

	//stub for thread name
	private String _name = "SampleThread ";
	//base sleep time
	private int _sleepTime = 1000;

	//constructor completes name and sleep time
	SampleThread (int nr){
		_name += nr;
		_sleepTime=nr*_sleepTime;
	}
	
	@Override
	public void run() {
		//thread runs infinite
		while(true){
			//print thread name
			System.out.println(_name);
			
			//sleep
			try {
				Thread.sleep(_sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
