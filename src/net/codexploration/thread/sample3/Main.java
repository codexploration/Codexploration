package net.codexploration.thread.sample3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		SampleQueue sampleQueue = new SampleQueue();
		//Creating the ThreadPool
		ExecutorService executor = Executors.newCachedThreadPool();
		//Creating 5 sample Threads and execute in ThreadPool
		for (int i=1; i<6; i++){
			QueuePutter putter = new QueuePutter(i, sampleQueue);
			executor.execute(putter);
		}
		for (int i=1; i<11;i++){
			QueueTaker taker = new QueueTaker(i,sampleQueue);
			executor.execute(taker);
		}
		
		
		//Pausing the main thread for 30 sec
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//calling static shutdown method on SampleThread. This will cause all SampleThreads to shutdown 
		QueuePutter.shutdown();
		QueueTaker.shutdown();
		sampleQueue.shutdown();
		//shutting down the executor. After that the JVM is finished and will stop.
		executor.shutdown();
	}

}
