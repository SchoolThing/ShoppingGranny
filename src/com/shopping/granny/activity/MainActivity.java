package com.shopping.granny.activity;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import android.graphics.Typeface;
import android.util.Log;

import com.shopping.granny.manager.HighscoreManager;
import com.shopping.granny.scene.EndGameScene;
import com.shopping.granny.scene.GameScene;
import com.shopping.granny.scene.HelpScene;
import com.shopping.granny.scene.MainMenuScene;
import com.shopping.granny.scene.SplashScene;

public class MainActivity extends SimpleBaseGameActivity {

	public static int CAMERA_WIDTH = 800;
	public static int CAMERA_HEIGHT = 480;

	private Camera mCamera;
	private Scene mCurrentScene;

	private Font mFont;
	private TextureRegion mBackgroundTextureRegion;
	private TiledTextureRegion mGrannyTextureRegion;
	private TextureRegion mButtonUpTextureRegion;
	private TextureRegion mButtonDownTextureRegion;
	private TextureRegion mObstacleTextureRegion;
	private ITexture backgroundTexture;
	private ITexture grannyTexture;
	private BitmapTexture buttonUpTexture;
	private BitmapTexture buttonDownTexture;
	private BitmapTexture obstacleTexture;
	private ITexture LoaderTexture;
	private ITexture SplashScreenBackground;
	private ITexture mTileTextureRegion;
	private TiledTextureRegion mLoaderTextureRegion;
	private TextureRegion mSplashScreenRegion;
	private BitmapTexture AppleTexture;
	private TextureRegion mAppleRegion;
	private BitmapTexture BreadTexture;
	private TextureRegion mBreadRegion;
	private BitmapTexture CheeseTexture;
	private TextureRegion mCheeseRegion;
	private BitmapTexture CoffeeTexture;
	private TextureRegion mCoffeeRegion;
	private TextureRegion mEggRegion;
	private BitmapTexture EggTexture;
	private BitmapTexture LemonTexture;
	private TextureRegion mLemonRegion;
	private BitmapTexture MilkTexture;
	private TextureRegion mMilkRegion;
	private BitmapTexture PearTexture;
	private TextureRegion mPearRegion;
	private BitmapTexture PepperTexture;
	private TextureRegion mPepperRegion;
	private TextureRegion mSaltRegion;
	private BitmapTexture SaltTexture;
	private TextureRegion mTomatoRegion;
	private BitmapTexture TomatoTexture;
	private ITexture MenuSceneBackground;
	private TextureRegion mMenuSceneBackgroundRegion;
	private BitmapTexture PlayButton;
	private TextureRegion mPlayButtonRegion;
	private BitmapTexture PlayAgainButton;
	private TextureRegion mPlayAgainButtonRegion;
	private BitmapTexture ExitGameButton;
	private TextureRegion mExitGameButtonRegion;
	private ITexture rollingPin;
	private TiledTextureRegion mRollingPinTextureRegion;
	private ITexture shootButton;
	private TextureRegion mShootButtonTextureRegion;
	private HighscoreManager highscoreManager;
	private BitmapTexture HowtoButton;
	private TextureRegion mHowtoButtonRegion;

	// ------------------------------------------------------------------------
	public Font getmFont() {
		return mFont;
	}

	public TextureRegion getmShootButtonTextureRegion(){
		return mShootButtonTextureRegion;
	}
	
	public HighscoreManager getHighscoreManager() {
		return highscoreManager;
	}

	public TextureRegion getmBackgroundTextureRegion() {
		return mBackgroundTextureRegion;
	}

	public TiledTextureRegion getmGrannyTextureRegion() {
		return mGrannyTextureRegion;
	}

	public TextureRegion getmButtonUpTextureRegion() {
		return mButtonUpTextureRegion;
	}

	public TextureRegion getmButtonDownTextureRegion() {
		return mButtonDownTextureRegion;
	}

	public Camera getmCamera() {
		return mCamera;
	}

	public Scene getmCurrentScene() {
		return mCurrentScene;
	}

	public void setmCurrentScene(Scene pScene) {
		mCurrentScene = pScene;
		getEngine().setScene(pScene);
	}

	public TextureRegion getmObstacleTextureRegion() {
		return mObstacleTextureRegion;
	}

	public TextureRegion getmSplashScreenRegion() {
		return mSplashScreenRegion;
	}

	public TiledTextureRegion getmLoaderTextureRegion() {
		return mLoaderTextureRegion;
	}

	public TextureRegion getmAppleRegion() {
		return mAppleRegion;
	}

	public TextureRegion getmBreadRegion() {
		return mBreadRegion;
	}

	public TextureRegion getmCheeseRegion() {
		return mCheeseRegion;
	}

	public TextureRegion getmCoffeeRegion() {
		return mCoffeeRegion;
	}

	public TextureRegion getmEggRegion() {
		return mEggRegion;
	}

	public TextureRegion getmLemonRegion() {
		return mLemonRegion;
	}

	public TextureRegion getmMilkRegion() {
		return mMilkRegion;
	}

	public TextureRegion getmPearRegion() {
		return mPearRegion;
	}

	public TextureRegion getmPepperRegion() {
		return mPepperRegion;
	}

	public TextureRegion getmSaltRegion() {
		return mSaltRegion;
	}

	public TextureRegion getmTomatoRegion() {
		return mTomatoRegion;
	}

	public TextureRegion getmMenuSceneBackgroundRegion() {
		return mMenuSceneBackgroundRegion;
	}

	public TextureRegion getmPlayButtonRegion() {
		return mPlayButtonRegion;
	}

	public TextureRegion getmPlayAgainButtonRegion() {
		return mPlayAgainButtonRegion;
	}

	public TextureRegion getmExitGameButtonRegion() {
		return mExitGameButtonRegion;
	}

	public BitmapTexture getPlayButton() {
		return PlayButton;
	}

	public BitmapTexture getPlayAgainButton() {
		return PlayAgainButton;
	}

	public ITexture getBackgroundTexture() {
		return backgroundTexture;
	}

	public void setBackgroundTexture(ITexture backgroundTexture) {
		this.backgroundTexture = backgroundTexture;
	}

	public ITexture getGrannyTexture() {
		return grannyTexture;
	}

	public void setGrannyTexture(ITexture grannyTexture) {
		this.grannyTexture = grannyTexture;
	}

	public BitmapTexture getButtonUpTexture() {
		return buttonUpTexture;
	}

	public void setButtonUpTexture(BitmapTexture buttonUpTexture) {
		this.buttonUpTexture = buttonUpTexture;
	}

	public BitmapTexture getButtonDownTexture() {
		return buttonDownTexture;
	}

	public void setButtonDownTexture(BitmapTexture buttonDownTexture) {
		this.buttonDownTexture = buttonDownTexture;
	}

	public ITexture getLoaderTexture() {
		return LoaderTexture;
	}

	public void setLoaderTexture(ITexture loaderTexture) {
		LoaderTexture = loaderTexture;
	}

	public BitmapTexture getAppleTexture() {
		return AppleTexture;
	}

	public void setAppleTexture(BitmapTexture appleTexture) {
		AppleTexture = appleTexture;
	}

	public BitmapTexture getBreadTexture() {
		return BreadTexture;
	}

	public void setBreadTexture(BitmapTexture breadTexture) {
		BreadTexture = breadTexture;
	}

	public BitmapTexture getCheeseTexture() {
		return CheeseTexture;
	}

	public void setCheeseTexture(BitmapTexture cheeseTexture) {
		CheeseTexture = cheeseTexture;
	}

	public BitmapTexture getCoffeeTexture() {
		return CoffeeTexture;
	}

	public void setCoffeeTexture(BitmapTexture coffeeTexture) {
		CoffeeTexture = coffeeTexture;
	}

	public BitmapTexture getEggTexture() {
		return EggTexture;
	}

	public void setEggTexture(BitmapTexture eggTexture) {
		EggTexture = eggTexture;
	}

	public BitmapTexture getLemonTexture() {
		return LemonTexture;
	}

	public void setLemonTexture(BitmapTexture lemonTexture) {
		LemonTexture = lemonTexture;
	}

	public BitmapTexture getExitGameButton() {
		return ExitGameButton;
	}

	public void setExitGameButton(BitmapTexture exitGameButton) {
		ExitGameButton = exitGameButton;
	}

	public BitmapTexture getObstacleTexture() {
		return obstacleTexture;
	}

	public BitmapTexture getMilkTexture() {
		return MilkTexture;
	}

	public BitmapTexture getPearTexture() {
		return PearTexture;
	}

	public BitmapTexture getPepperTexture() {
		return PepperTexture;
	}

	public BitmapTexture getSaltTexture() {
		return SaltTexture;
	}

	public BitmapTexture getTomatoTexture() {
		return TomatoTexture;
	}

	public ITexture getSplashScreenBackground() {
		return SplashScreenBackground;
	}

	public ITexture getMenuSceneBackground() {
		return MenuSceneBackground;
	}

	public TiledTextureRegion getmRollingPinTextureRegion() {
		return mRollingPinTextureRegion;
	}	
	
	public ITexture getRollingPin() {
		return rollingPin;
	}

	// ------------------------------------------------------------------------


	public static MainActivity instance;

	// @Override
	// protected void onPause() {
	// super.onPause();
	// }

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				getmCamera());
	}

	@Override
	protected void onCreateResources() {
		this.mFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL), 32,
				Color.WHITE.hashCode());
		this.mFont.load();
		try {
			/* --------- Background for Game ------ */
			backgroundTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
						public InputStream open() throws IOException {
							return getAssets().open("gfx/bg.png");
						}
					});
			backgroundTexture.load();
			this.mBackgroundTextureRegion = TextureRegionFactory
					.extractFromTexture(backgroundTexture);
			/*-------------------------------------*/

			/*------- Loader texture in Splash Screen*/
			LoaderTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Loader.png");
						}
					});
			LoaderTexture.load();
			this.mLoaderTextureRegion = TextureRegionFactory
					.extractTiledFromTexture(LoaderTexture, 8, 1);
			/*-----------------------------------------*/

			/*-------Splash Screen Background*/
			SplashScreenBackground = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						public InputStream open() throws IOException {
							return getAssets().open("gfx/SplashBG.png");
						}
					});
			SplashScreenBackground.load();
			this.mSplashScreenRegion = TextureRegionFactory
					.extractFromTexture(SplashScreenBackground);
			/*-------------------------------*/

			/*---------The Granny we play with -------*/
			grannyTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
						public InputStream open() throws IOException {
							return getAssets().open("gfx/MovingGranny.png");
						}
					});
			grannyTexture.load();
			this.mGrannyTextureRegion = TextureRegionFactory
					.extractTiledFromTexture(grannyTexture, 2, 1);
			/*-------------------------------*/

			/*---------Up Arrow --------------*/
			buttonUpTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						public InputStream open() throws IOException {
							return getAssets().open("gfx/Arrow-Up.png");
						}
					});
			buttonUpTexture.load();
			this.mButtonUpTextureRegion = TextureRegionFactory
					.extractFromTexture(buttonUpTexture);
			/*--------------------------------*/

			/*-------------Down Arrow ------------*/
			buttonDownTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						public InputStream open() throws IOException {
							return getAssets().open("gfx/Arrow-Down.png");
						}
					});

			buttonDownTexture.load();
			this.mButtonDownTextureRegion = TextureRegionFactory
					.extractFromTexture(buttonDownTexture);
			/*------------------------------------*/

			/*----------- Obstacles ----------*/
			obstacleTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/barrier.png");
						}

					});

			obstacleTexture.load();
			mObstacleTextureRegion = TextureRegionFactory
					.extractFromTexture(obstacleTexture);
			/*-----------------------------------*/
			/*-------Apple ----------*/
			AppleTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/apple.png");
						}
					});
			AppleTexture.load();
			mAppleRegion = TextureRegionFactory
					.extractFromTexture(AppleTexture);
			/*------------------------*/
			// bread
			BreadTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Bread.png");
						}
					});
			BreadTexture.load();
			mBreadRegion = TextureRegionFactory
					.extractFromTexture(BreadTexture);
			// end of bread

			// cheese
			CheeseTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Cheese.png");
						}
					});
			CheeseTexture.load();
			mCheeseRegion = TextureRegionFactory
					.extractFromTexture(CheeseTexture);
			// end of cheese

			// coffee
			CoffeeTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Coffee.png");
						}
					});
			CoffeeTexture.load();
			mCoffeeRegion = TextureRegionFactory
					.extractFromTexture(CoffeeTexture);
			// end of coffee

			// egg
			EggTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Egg.png");
						}
					});
			EggTexture.load();
			mEggRegion = TextureRegionFactory.extractFromTexture(EggTexture);
			// end of egg

			// lemon
			LemonTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Lemon.png");
						}
					});
			LemonTexture.load();
			mLemonRegion = TextureRegionFactory
					.extractFromTexture(LemonTexture);
			// end of lemon

			// Milk
			MilkTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Milk.png");
						}
					});
			MilkTexture.load();
			mMilkRegion = TextureRegionFactory.extractFromTexture(MilkTexture);
			// end of milk

			// Pear
			PearTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Pear.png");
						}
					});
			PearTexture.load();
			mPearRegion = TextureRegionFactory.extractFromTexture(PearTexture);
			// end of pear

			// Pepper
			PepperTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Pepper.png");
						}
					});
			PepperTexture.load();
			mPepperRegion = TextureRegionFactory
					.extractFromTexture(PepperTexture);
			// end of pepper

			// salt
			SaltTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Salt.png");
						}
					});
			SaltTexture.load();
			mSaltRegion = TextureRegionFactory.extractFromTexture(SaltTexture);
			// end of salt

			// tomato
			TomatoTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Tomato.png");
						}
					});
			TomatoTexture.load();
			mTomatoRegion = TextureRegionFactory
					.extractFromTexture(TomatoTexture);
			// end of tomato

			// MenuScene Background
			MenuSceneBackground = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open(
									"gfx/MenuScene-background.png");
						}
					});
			MenuSceneBackground.load();
			mMenuSceneBackgroundRegion = TextureRegionFactory
					.extractFromTexture(MenuSceneBackground);
			// end of background

			// Play button
			PlayButton = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Button-Play.png");
						}
					});
			PlayButton.load();
			mPlayButtonRegion = TextureRegionFactory
					.extractFromTexture(PlayButton);
			// end of button

			//how to button
			HowtoButton = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/howto-button.png");
				}
			});
			HowtoButton.load();
			mHowtoButtonRegion = TextureRegionFactory.extractFromTexture(HowtoButton);
			// end of button
			
			// Play Again button
			PlayAgainButton = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/PlayAgain-button.png");
						}
					});
			PlayAgainButton.load();
			mPlayAgainButtonRegion = TextureRegionFactory
					.extractFromTexture(PlayAgainButton);
			// end of button

			// Quit button
			ExitGameButton = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/Quitbutton.png");
						}
					});
			ExitGameButton.load();
			mExitGameButtonRegion = TextureRegionFactory
					.extractFromTexture(ExitGameButton);
			// end of button

			// rolling pin
			rollingPin = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/rollpin.png");
						}
					});
			rollingPin.load();
			mRollingPinTextureRegion = TextureRegionFactory
					.extractTiledFromTexture(rollingPin, 4, 1);
			// end of rolling pin
			
			//shoot button
			shootButton = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener(){
				@Override
				public InputStream open() throws IOException{
					return getAssets().open("gfx/shootButton.png");
				}
			});
			shootButton.load();
			mShootButtonTextureRegion = TextureRegionFactory.extractFromTexture(shootButton); 
			
			//end of shoot button
		} catch (IOException e) {
			Debug.e(e);
		}

	}

	public TextureRegion getmHowtoButtonRegion() {
		return mHowtoButtonRegion;
	}

	@Override
	protected Scene onCreateScene() {
		highscoreManager = new HighscoreManager(this);
		this.mEngine.registerUpdateHandler(new FPSLogger());
		mCurrentScene = new SplashScene();
		return mCurrentScene;
	}

	public static MainActivity getSharedInstance() {
		if (instance == null) {
			return new MainActivity();
		}
		return instance;
	}

	@Override
	public void onPauseGame() {
		try {
			super.onPauseGame();
		} catch (Exception ex) {
			Log.e("Exception",
					ex.getClass().getName() + " - " + ex.getMessage());
		}

	}

	@Override
	public void onBackPressed() {
		if (mCurrentScene instanceof MainMenuScene){
			mCurrentScene = null;
			super.onBackPressed();
		}
		else if(mCurrentScene instanceof HelpScene){
			//Log.d("hello", "sdhfakrjfg");
			setmCurrentScene(new MainMenuScene());
		}
		else if(mCurrentScene instanceof GameScene){
			((GameScene) mCurrentScene).detach();
			setmCurrentScene(new MainMenuScene());
		}
	}

	@Override
	public void onDestroyResources() {
		backgroundTexture.unload();
		buttonDownTexture.unload();
		buttonUpTexture.unload();
		grannyTexture.unload();
		obstacleTexture.unload();
		LoaderTexture.unload();
		SplashScreenBackground.unload();
		AppleTexture.unload();
		BreadTexture.unload();
		CheeseTexture.unload();
		CoffeeTexture.unload();
		EggTexture.unload();
		PearTexture.unload();
		PepperTexture.unload();
		SaltTexture.unload();
		TomatoTexture.unload();
		LemonTexture.unload();
		MilkTexture.unload();
		PlayButton.unload();
		MenuSceneBackground.unload();
		rollingPin.unload();
	}

}
