package com.shopping.granny.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.shopping.granny.activity.MainActivity;

public class SplashScene extends Scene {

	private Camera mCamera;

	public SplashScene() {
		mCamera = MainActivity.getSharedInstance().getmCamera();

		float bgHeight = MainActivity.getSharedInstance()
				.getmSplashScreenRegion().getHeight();
		float bgWidth = MainActivity.getSharedInstance()
				.getmSplashScreenRegion().getWidth();

		Sprite mBackgroundSprite = new Sprite(0, 0, bgWidth, bgHeight,
				MainActivity.getSharedInstance().getmSplashScreenRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
		attachChild(mBackgroundSprite);

		AnimatedSprite loader = new AnimatedSprite(MainActivity.CAMERA_WIDTH
				/ 2
				- MainActivity.getSharedInstance().getmLoaderTextureRegion()
						.getWidth() / 2, MainActivity.CAMERA_HEIGHT
				/ 2
				- MainActivity.getSharedInstance().getmLoaderTextureRegion()
						.getHeight() / 2, MainActivity.getSharedInstance()
				.getmLoaderTextureRegion(), MainActivity.getSharedInstance()
				.getVertexBufferObjectManager());
		loader.animate(100);
		this.attachChild(loader);
		
		TimerHandler t = new TimerHandler(3f,new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				MainActivity.getSharedInstance().setmCurrentScene(new MainMenuScene());
				detachSelf();
			}
		});

		 this.registerUpdateHandler(t);
		
	}
}
