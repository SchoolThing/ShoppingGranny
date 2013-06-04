package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Cheese extends Collectable {

	public Cheese(float x, float y){
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmCheeseRegion().getWidth(), MainActivity
				.getSharedInstance().getmCheeseRegion().getHeight(),
				MainActivity.getSharedInstance().getmCheeseRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());

	}
}
