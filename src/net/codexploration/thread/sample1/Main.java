package net.codexploration.thread.sample1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		//Creating the ThreadPool
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//Creating 4 sample Threads and execute in ThreadPool
		for (int i=1; i<5000; i++){
			SampleThread sT = new SampleThread(i);
			executor.execute(sT);
		}
	}

}
