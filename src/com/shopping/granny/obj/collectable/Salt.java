package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Salt extends Collectable {

	public Salt(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmSaltRegion().getWidth(), MainActivity
				.getSharedInstance().getmSaltRegion().getHeight(),
				MainActivity.getSharedInstance().getmSaltRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}