package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Tomato extends Collectable {

	public Tomato(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmTomatoRegion().getWidth(), MainActivity
				.getSharedInstance().getmTomatoRegion().getHeight(),
				MainActivity.getSharedInstance().getmTomatoRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}