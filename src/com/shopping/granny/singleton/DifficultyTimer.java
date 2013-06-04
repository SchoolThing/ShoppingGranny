package com.shopping.granny.singleton;

import java.util.Timer;
import java.util.TimerTask;

public class DifficultyTimer {
	private static DifficultyTimer instance;
	private Timer timer;
	private boolean valid;
	private long seconds = 1000;
	private long delay = 20 * seconds;
	
	public static DifficultyTimer getSharedInstance(){
		if(instance == null){
			instance = new DifficultyTimer();
		}
		return instance;
	}
	
	public DifficultyTimer(){
		timer = new Timer();
		valid = true;
	}
	
	public boolean checkValidity(){
		if(valid){
			valid = false;
			timer.schedule(new Task(), delay);
			return true;
		}
		return false;
	}
	
	
	class Task extends TimerTask{

		@Override
		public void run() {
			valid = true;
		}
		
	}
}
