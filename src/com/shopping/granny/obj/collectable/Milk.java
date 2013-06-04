package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Milk extends Collectable {

	public Milk(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmMilkRegion().getWidth(), MainActivity
				.getSharedInstance().getmMilkRegion().getHeight(),
				MainActivity.getSharedInstance().getmMilkRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}