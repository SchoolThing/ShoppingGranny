package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Pear extends Collectable {

	public Pear(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmPearRegion().getWidth(), MainActivity
				.getSharedInstance().getmPearRegion().getHeight(),
				MainActivity.getSharedInstance().getmPearRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}