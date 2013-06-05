package com.shopping.granny.singleton;

import java.util.Timer;
import java.util.TimerTask;

public class PinShootCooldown {
	private boolean valid;
	private Timer timer;
	private int delay = 10000;
	private static PinShootCooldown instance;
	
	public static PinShootCooldown getSharedInstance(){
		if(instance == null)
			instance = new PinShootCooldown();
		return instance;
	}
	
	private PinShootCooldown(){
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
		public void run(){
			valid = true;
		}
	};
}
