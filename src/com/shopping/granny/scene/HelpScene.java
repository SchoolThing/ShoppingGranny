package com.shopping.granny.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.app.R;

public class HelpScene extends Scene implements IOnSceneTouchListener {

	private Camera mCamera;
	private Text helpText;

	public HelpScene() {

		mCamera = MainActivity.getSharedInstance().getmCamera();

		MainActivity activity = MainActivity.getSharedInstance();

		float bgHeight = activity.getmBackgroundTextureRegion().getHeight();
		float bgWidth = activity.getmBackgroundTextureRegion().getWidth();

		Sprite mBackgroundSprite = new Sprite(0, 0, bgWidth, bgHeight,
				activity.getmBackgroundTextureRegion(),
				activity.getVertexBufferObjectManager());
		attachChild(mBackgroundSprite);

		helpText = new Text(300, 30, activity.getmFont(),
				activity.getString(R.string.help_text1), activity.getString(
						R.string.help_text1).length(),
				activity.getVertexBufferObjectManager());
		attachChild(helpText);

		helpText = new Text(
				30,
				120,
				activity.getmFont(),
				activity.getString(R.string.help_text2) + "\n"
						+ activity.getString(R.string.help_text3) + "\n"
						+ activity.getString(R.string.help_text4) + "\n"
						+ activity.getString(R.string.help_text5) + "\n"
						+ activity.getString(R.string.help_text6) + "\n"
						+ activity.getString(R.string.help_text7),
						(activity.getString(R.string.help_text2) + "\n"
						+ activity.getString(R.string.help_text3) + "\n"
						+ activity.getString(R.string.help_text4) + "\n"
						+ activity.getString(R.string.help_text5) + "\n"
						+ activity.getString(R.string.help_text6)+"\n"+ activity.getString(R.string.help_text7)).length(), activity
						.getVertexBufferObjectManager());
		attachChild(helpText);

	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}

}
