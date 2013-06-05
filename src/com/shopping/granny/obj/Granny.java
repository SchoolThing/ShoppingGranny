package com.shopping.granny.obj;

import java.util.LinkedList;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.obj.collectable.Collectable;
import com.shopping.granny.singleton.PinShootCooldown;

public class Granny {

	private boolean moveable;
	private GrannySprite mGrannySprite;
	private LinkedList<Collectable> inventory;
	private AnimatedSprite pin;
	private float pinSpeed = 300;
	PhysicsHandler pinHandler;

	// -------------------------------------------------------------
	public boolean isMoveable() {
		return moveable;
	}
	
	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isShootReady(){
		return PinShootCooldown.getSharedInstance().checkValidity();
	}
	
	public float getPinSpeed(){
		return pinSpeed;
	}
	
	public void setPinSpeed(float s) {
		pinSpeed = s;
	}

	// --------------------------------------------------------------
	public Granny(final float initialX, final float initalY,
			final ITiledTextureRegion pGrannyTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		this.mGrannySprite = new GrannySprite(initialX, initalY,
				pGrannyTextureRegion.getWidth(),
				pGrannyTextureRegion.getHeight(), pGrannyTextureRegion,
				pVertexBufferObjectManager);
		inventory = new LinkedList<Collectable>();
	}

	public GrannySprite getGrannySprite() {
		return this.mGrannySprite;
	}

	public PhysicsHandler getPinHandler() {
		return pinHandler;
	}

	public AnimatedSprite getPin(){
		return this.pin;
	}
	
	public void setPin(AnimatedSprite pin) {
		this.pin = pin;
	}

	public LinkedList<Collectable> getInventory() {
		return this.inventory;
	}

	public void restart() {
		inventory.clear();
		setMoveable(false);
		MoveYModifier mod = new MoveYModifier(0.4f, this.getGrannySprite()
				.getY(), 216) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				super.onModifierFinished(pItem);
				setMoveable(true);
			}
		};
		getGrannySprite().registerEntityModifier(mod);
	}

	public void setPinHandler(PhysicsHandler handler) {
		this.pinHandler = handler;
	}
}