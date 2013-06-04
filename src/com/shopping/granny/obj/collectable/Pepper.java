package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Pepper extends Collectable {

	public Pepper(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmPepperRegion().getWidth(), MainActivity
				.getSharedInstance().getmPepperRegion().getHeight(),
				MainActivity.getSharedInstance().getmPepperRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}