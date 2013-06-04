package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Apple extends Collectable {

	public Apple(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmAppleRegion().getWidth(), MainActivity
				.getSharedInstance().getmAppleRegion().getHeight(),
				MainActivity.getSharedInstance().getmAppleRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}

}
