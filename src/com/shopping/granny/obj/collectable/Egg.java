package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Egg extends Collectable {

	public Egg(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmEggRegion().getWidth(), MainActivity
				.getSharedInstance().getmEggRegion().getHeight(),
				MainActivity.getSharedInstance().getmEggRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}