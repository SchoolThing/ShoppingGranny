package com.shopping.granny.scene;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.text.Text;

import android.util.Log;

import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.singleton.DifficultyTimer;

public class GameLoopUpdateHandler implements IUpdateHandler {
	private float durationChangeOffset;
	private int difficultyLevel;
	private int speedChangeOffset;
	private int pointsPerSecond;
	
	public GameLoopUpdateHandler() {
		pointsPerSecond = 3;
		durationChangeOffset = 0.005f;
		difficultyLevel = 0;
		speedChangeOffset = 50;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		GameScene scene = (GameScene)MainActivity.getSharedInstance().getmCurrentScene();
		scene.setScore(scene.getScore()+pSecondsElapsed*pointsPerSecond);
		long totalScore = ((long)scene.getScore()) + scene.getExtraScore();
		scene.getScoreBoard().setText("Score: "+ totalScore);
		scene.getPinsNumberText().setText("Pins: "+scene.getPinNumber());
		scene.getLevelText().setText("Level:" + (difficultyLevel+1));
		scene.addLineUp();
		scene.cleaner();
		if (DifficultyTimer.getSharedInstance().checkValidity()) {
			GameScene.setMoveDuration(GameScene.getMoveDuration() - durationChangeOffset);
			difficultyLevel++;
			scene.setObstacleSpeed(scene.getObstacleSpeed() + speedChangeOffset);
			scene.getmGranny().setPinSpeed(scene.getmGranny().getPinSpeed() + speedChangeOffset);
			scene.clear();
		}
	}

	@Override
	public void reset() {
	}

}
