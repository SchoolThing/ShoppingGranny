package com.shopping.granny.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.opengl.GLES20;
import android.util.Log;

import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.obj.collectable.Cheese;
import com.shopping.granny.obj.collectable.Coffee;
import com.shopping.granny.obj.collectable.Lemon;
import com.shopping.granny.obj.collectable.Pepper;

public class MainMenuScene extends MenuScene implements
		IOnMenuItemClickListener {

	private Camera mCamera;

	private boolean helpIsOpen;

	public static int CAMERA_WIDTH = 800;
	public static int CAMERA_HEIGHT = 480;
	protected static final int MENU_PLAY = 0;
	protected static final int MENU_HOWTO = 1;

	public MainMenuScene() {

		super(MainActivity.getSharedInstance().getmCamera());

		float bgHeight = MainActivity.getSharedInstance()
				.getmMenuSceneBackgroundRegion().getHeight();
		float bgWidth = MainActivity.getSharedInstance()
				.getmMenuSceneBackgroundRegion().getWidth();

		Sprite mBackgroundSprite = new Sprite(0, 0, bgWidth, bgHeight,
				MainActivity.getSharedInstance()
						.getmMenuSceneBackgroundRegion(), MainActivity
						.getSharedInstance().getVertexBufferObjectManager());
		attachChild(mBackgroundSprite);

		createCoffee();
		createCheese();
		createPepper();
		createLemon();
		createPlayButton();
		createHowtoButton();
	}

	private void createHowtoButton() {
		SpriteMenuItem howto = new SpriteMenuItem(1, MainActivity
				.getSharedInstance().getmHowtoButtonRegion(), MainActivity
				.getSharedInstance().getVertexBufferObjectManager());

		howto.setPosition(MainActivity.CAMERA_WIDTH
				/ 2
				- MainActivity.getSharedInstance().getmHowtoButtonRegion()
						.getWidth() / 2, MainActivity.CAMERA_HEIGHT
				/ 2
				- MainActivity.getSharedInstance().getmHowtoButtonRegion()
						.getHeight() / 2 + howto.getHeight() + 30);
		this.addMenuItem(howto);
		this.setOnMenuItemClickListener(this);

	}

	private void createPlayButton() {
		SpriteMenuItem play = new SpriteMenuItem(0, MainActivity
				.getSharedInstance().getmPlayButtonRegion(), MainActivity
				.getSharedInstance().getVertexBufferObjectManager());

		play.setPosition(MainActivity.CAMERA_WIDTH
				/ 2
				- MainActivity.getSharedInstance().getmPlayButtonRegion()
						.getWidth() / 2, MainActivity.CAMERA_HEIGHT
				/ 2
				- MainActivity.getSharedInstance().getmPlayButtonRegion()
						.getHeight() / 2);
		this.addMenuItem(play);
		this.setOnMenuItemClickListener(this);

	}

	private void createLemon() {
		Lemon lemon = new Lemon(CAMERA_WIDTH, CAMERA_HEIGHT);
		MoveModifier Up = new MoveModifier(2f, CAMERA_WIDTH, 0, 0,
				CAMERA_HEIGHT);
		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(2f, 0, 360));
		lemon.getSprite().registerEntityModifier(rotate);
		lemon.getSprite().registerEntityModifier(loop);
		attachChild(lemon.getSprite());
	}

	private void createPepper() {
		Pepper pepper = new Pepper(CAMERA_WIDTH, CAMERA_HEIGHT);
		MoveModifier Up = new MoveModifier(3f, CAMERA_WIDTH, 0 - pepper
				.getSprite().getWidth(), CAMERA_HEIGHT, CAMERA_HEIGHT - 300);
		// MoveModifier Down = new MoveModifier(1f, CAMERA_WIDTH - 600,
		// CAMERA_WIDTH - 700, CAMERA_HEIGHT - 300, CAMERA_HEIGHT);

		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(2f, 0, 360));
		pepper.getSprite().registerEntityModifier(rotate);
		pepper.getSprite().registerEntityModifier(loop);
		attachChild(pepper.getSprite());
	}

	private void createCheese() {
		Cheese cheese = new Cheese(CAMERA_WIDTH, CAMERA_HEIGHT);
		MoveModifier Up = new MoveModifier(2f, CAMERA_WIDTH,
				CAMERA_WIDTH - 300, CAMERA_HEIGHT, CAMERA_HEIGHT - 500);
		MoveModifier Down = new MoveModifier(2f, CAMERA_WIDTH - 300,
				CAMERA_WIDTH - 500, CAMERA_HEIGHT - 500, CAMERA_HEIGHT);

		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up, Down));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(2f, 0, 360));
		cheese.getSprite().registerEntityModifier(rotate);
		cheese.getSprite().registerEntityModifier(loop);
		attachChild(cheese.getSprite());

	}

	private void createCoffee() {
		Coffee coffee = new Coffee(CAMERA_WIDTH, CAMERA_HEIGHT);

		MoveModifier Up = new MoveModifier(2f, CAMERA_WIDTH, 0, 0,
				CAMERA_HEIGHT - 300);
		MoveModifier Down = new MoveModifier(2f, 0, CAMERA_WIDTH,
				CAMERA_HEIGHT - 300, 0);

		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up, Down));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(2f, 0, 360));
		coffee.getSprite().registerEntityModifier(rotate);
		coffee.getSprite().registerEntityModifier(loop);
		attachChild(coffee.getSprite());
	}
	
	

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MENU_PLAY:
			MainActivity.getSharedInstance().setmCurrentScene(new GameScene());
			return true;
			
		case MENU_HOWTO:
			//Log.d("we are here", "asdfasdf");
			MainActivity.getSharedInstance().setmCurrentScene(new HelpScene());
			return true;
		}
		return false;
	}

}
