package com.shopping.granny.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HighscoreManager {
	private static final String HIGHSCORE_KEY = "HIGHSCORE_KEY";
	
	private Context context;
	
	public HighscoreManager(Context context) {
		this.context = context;
	}
	
	public void setHighscore(long totalScore) {
		getSharedPreferences().edit().putLong(HIGHSCORE_KEY, totalScore).commit();
	}
	
	public void removeHighscore() {
		getSharedPreferences().edit().remove(HIGHSCORE_KEY).commit();
	}
	
	public long getHighscore() {
		return getSharedPreferences().getLong(HIGHSCORE_KEY, 0);
	}
	
	private SharedPreferences getSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
