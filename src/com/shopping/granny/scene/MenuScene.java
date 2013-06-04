package com.shopping.granny.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.obj.collectable.Cheese;
import com.shopping.granny.obj.collectable.Coffee;
import com.shopping.granny.obj.collectable.Lemon;
import com.shopping.granny.obj.collectable.Pepper;

public class MenuScene extends Scene {

	private Camera mCamera;

	public static int CAMERA_WIDTH = 800;
	public static int CAMERA_HEIGHT = 480;

	public MenuScene() {
		mCamera = MainActivity.getSharedInstance().getmCamera();

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

		MainActivity.getSharedInstance().setmCurrentScene(this);
	}

	private void createLemon() {
		Lemon lemon = new Lemon(CAMERA_WIDTH, CAMERA_HEIGHT);
		MoveModifier Up = new MoveModifier(2f, CAMERA_WIDTH ,
				0, 0, CAMERA_HEIGHT);
//		MoveModifier Down = new MoveModifier(2f, CAMERA_WIDTH - 400,
//				CAMERA_WIDTH - 100, CAMERA_HEIGHT, CAMERA_HEIGHT);

		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(1f, 0, 360));
		lemon.getSprite().registerEntityModifier(rotate);
		lemon.getSprite().registerEntityModifier(loop);
		attachChild(lemon.getSprite());
	}

	private void createPepper() {
		Pepper pepper = new Pepper(CAMERA_WIDTH, CAMERA_HEIGHT);
		MoveModifier Up = new MoveModifier(3f, CAMERA_WIDTH,
				0-pepper.getSprite().getWidth(), CAMERA_HEIGHT, CAMERA_HEIGHT - 300);
//		MoveModifier Down = new MoveModifier(1f, CAMERA_WIDTH - 600,
//				CAMERA_WIDTH - 700, CAMERA_HEIGHT - 300, CAMERA_HEIGHT);

		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(1f, 0, 360));
		pepper.getSprite().registerEntityModifier(rotate);
		pepper.getSprite().registerEntityModifier(loop);
		attachChild(pepper.getSprite());
	}

	private void createCheese() {
		Cheese cheese = new Cheese(CAMERA_WIDTH, CAMERA_HEIGHT);
		MoveModifier Up = new MoveModifier(2f, CAMERA_WIDTH,
				CAMERA_WIDTH - 300, CAMERA_HEIGHT, CAMERA_HEIGHT - 500);
		MoveModifier Down = new MoveModifier(2f, CAMERA_WIDTH-300,
				CAMERA_WIDTH - 500, CAMERA_HEIGHT - 500, CAMERA_HEIGHT);

		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up, Down));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(1f, 0, 360));
		cheese.getSprite().registerEntityModifier(rotate);
		cheese.getSprite().registerEntityModifier(loop);
		attachChild(cheese.getSprite());

	}

	private void createCoffee() {
		Coffee coffee = new Coffee(CAMERA_WIDTH, CAMERA_HEIGHT);

		MoveModifier Up = new MoveModifier(2f, CAMERA_WIDTH,
				0, 0, CAMERA_HEIGHT - 300);
		MoveModifier Down = new MoveModifier(2f, 0,
				CAMERA_WIDTH , CAMERA_HEIGHT - 300, 0);

		LoopEntityModifier loop = new LoopEntityModifier(
				new SequenceEntityModifier(Up, Down));
		LoopEntityModifier rotate = new LoopEntityModifier(
				new RotationModifier(1f, 0, 360));
		coffee.getSprite().registerEntityModifier(rotate);
		coffee.getSprite().registerEntityModifier(loop);
		attachChild(coffee.getSprite());
	}

}
