package com.shopping.granny.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.os.Process;

import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.manager.HighscoreManager;

public class EndGameScene extends CameraScene {
	private MainActivity activity;
	private float X = MainActivity.CAMERA_WIDTH
			/ 2
			- MainActivity.getSharedInstance().getmPlayAgainButtonRegion()
					.getWidth() / 2;
	private float Y = MainActivity.CAMERA_HEIGHT
			/ 2
			- MainActivity.getSharedInstance().getmPlayAgainButtonRegion()
					.getHeight() / 2;
	private HighscoreManager highscoreManager;
	private ButtonSprite playAgainButton;
	private ButtonSprite ExitButton;

	@SuppressWarnings("static-access")
	public EndGameScene(Camera mCamera) {
		super(mCamera);
		activity = MainActivity.getSharedInstance();

		setBackground(new Background(0, 0, 0));

		long score = (long) ((GameScene) activity.getmCurrentScene())
				.getScore();
		long extraScore = (long) ((GameScene) activity.getmCurrentScene())
				.getExtraScore();
		long totalScore = score + extraScore;

		Text Score = new Text(250, 100, activity.getmFont(), "Score:" + score,
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
		attachChild(Score);

		Text ExtraScore = new Text(250, 150, activity.getmFont(),
				"Extra Score:" + extraScore, MainActivity.getSharedInstance()
						.getVertexBufferObjectManager());
		attachChild(ExtraScore);

		Text TotalScore = new Text(250, 205, activity.getmFont(),
				"Total Score:" + totalScore, MainActivity.getSharedInstance()
						.getVertexBufferObjectManager());
		attachChild(TotalScore);

		highscoreManager = activity.getHighscoreManager();
		long highscore = highscoreManager.getHighscore();
		if (highscore < totalScore) {			
			highscoreManager.setHighscore(highscore = totalScore);			
		}

		Text HighScore = new Text(220, 10, activity.getmFont(), "HIGHSCORE: " + highscore, MainActivity.getSharedInstance().getVertexBufferObjectManager());
		HighScore.setScale(1.2f);
		HighScore.setPosition(activity.CAMERA_WIDTH/2 - HighScore.getWidth()/2, 10);
		attachChild(HighScore);
		
		playAgainButton = new ButtonSprite(200, 300, MainActivity
				.getSharedInstance().getmPlayAgainButtonRegion(), MainActivity
				.getSharedInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pToucheAreaLocalX, final float pTouchAreaLocalY) {
				int eventAction = pSceneTouchEvent.getAction();
				switch (eventAction) {
				case TouchEvent.ACTION_UP: {
					((GameScene) activity.getmCurrentScene()).resetValues();
					return true;
				}
				}
				return false;

			}
		};
		attachChild(playAgainButton);
		registerTouchArea(playAgainButton);

		ExitButton = new ButtonSprite(400, 300, MainActivity
				.getSharedInstance().getmExitGameButtonRegion(), MainActivity
				.getSharedInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pToucheAreaLocalX, final float pToucheAreaLocalY) {
				int eventAction = pSceneTouchEvent.getAction();
				switch (eventAction) {
				case TouchEvent.ACTION_UP: {
					activity.onDestroyResources();
					((GameScene) activity.getmCurrentScene()).detach();
					activity.finish();
					return true;
				}
				}
				return false;
			}
		};
		attachChild(ExitButton);
		registerTouchArea(ExitButton);

		Line line = new Line(playAgainButton.getX(), 200, ExitButton.getX()
				+ ExitButton.getWidth(), 200, 2,
				activity.getVertexBufferObjectManager());
		attachChild(line);
	}
}
