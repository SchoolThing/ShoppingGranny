package com.shopping.granny.singleton;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ObstaclesCooldown {
	private boolean valid;
	private Timer timer;
	private long delay = 2000;
	private static ObstaclesCooldown instance;
	
	public static ObstaclesCooldown getSharedInstance(){
		if(instance == null)
			instance = new ObstaclesCooldown();
		return instance;
	}
	
	private ObstaclesCooldown(){
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
		public void run() {
			valid = true;
		}
	}
}
