package com.shopping.granny.obj.collectable;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

public abstract class Collectable extends Entity {
	protected Sprite sprite;
	
	public Sprite getSprite() {
		return sprite;
	}

	public Collectable(){
		super();
	}
	
	public void clean(){
		sprite.clearEntityModifiers();
		sprite.clearUpdateHandlers();
	}
}
