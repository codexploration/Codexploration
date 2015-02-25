package net.codexploration.thread.sample2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		//Creating the ThreadPool
		ExecutorService executor = Executors.newCachedThreadPool();
		//Creating 5 sample Threads and execute in ThreadPool
		for (int i=1; i<6; i++){
			SampleThread sT = new SampleThread(i);
			executor.execute(sT);
		}
		
		//Pausing the main thread for 10 sec
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//calling static shutdown method on SampleThread. This will cause all SampleThreads to shutdown 
		SampleThread.shutdown();
		//shutting down the executor. After that the JVM is finished and will stop.
		executor.shutdown();
	}

}
