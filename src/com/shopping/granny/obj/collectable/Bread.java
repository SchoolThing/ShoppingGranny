package com.shopping.granny.obj.collectable;

import org.andengine.entity.sprite.Sprite;

import com.shopping.granny.activity.MainActivity;

public class Bread extends Collectable {

	public Bread(float x, float y){
		super();
		sprite = new Sprite(x, y, MainActivity.getSharedInstance()
				.getmBreadRegion().getWidth(), MainActivity
				.getSharedInstance().getmBreadRegion().getHeight(),
				MainActivity.getSharedInstance().getmBreadRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());

	}
	
}
