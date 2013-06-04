package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Lemon extends Collectable {

	public Lemon(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmLemonRegion().getWidth(), MainActivity
				.getSharedInstance().getmLemonRegion().getHeight(),
				MainActivity.getSharedInstance().getmLemonRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}