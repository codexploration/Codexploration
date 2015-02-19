package net.codexploration.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		//Creating the ThreadPool
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//Creating 50 sample Threads and execute in ThreadPool
		for (int i=1; i<50; i++){
			SampleThread sT = new SampleThread(i);
			executor.execute(sT);
		}
	}

}
