package com.shopping.granny.obj;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Obstacle {
	private Sprite sprite;
	private PhysicsHandler mPhysicsHandler;

	public Sprite getSprite() {
		return sprite;
	}
	
	public PhysicsHandler getmPhysicsHandler(){
		return mPhysicsHandler;
	}

	public void setmPhysicsHandler(PhysicsHandler mPhysicsHandler) {
		this.mPhysicsHandler = mPhysicsHandler;
	}
	
	public Obstacle() {
		sprite = new Sprite(0, 0, MainActivity.getSharedInstance()
				.getmObstacleTextureRegion(), MainActivity.getSharedInstance()
				.getVertexBufferObjectManager());
	}

	public void clean() {
		sprite.clearEntityModifiers();
		sprite.clearUpdateHandlers();
	}

}
