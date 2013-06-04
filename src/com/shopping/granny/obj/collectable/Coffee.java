package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Coffee extends Collectable {

	public Coffee(float x, float y) {
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmCoffeeRegion().getWidth(), MainActivity
				.getSharedInstance().getmCoffeeRegion().getHeight(),
				MainActivity.getSharedInstance().getmCoffeeRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
	}
}
