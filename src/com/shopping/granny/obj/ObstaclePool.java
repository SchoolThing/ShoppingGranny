package com.shopping.granny.obj;

import org.andengine.util.adt.pool.GenericPool;

import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.scene.GameScene;

public class ObstaclePool extends GenericPool<Obstacle> {

	private static ObstaclePool instance;
	
	public static ObstaclePool sharedObstaclePool(){
		if(getInstance() == null)
			setInstance(new ObstaclePool());
		return getInstance();
	}
	
	public static ObstaclePool getInstance() {
		return instance;
	}

	public static void setInstance(ObstaclePool instance) {
		ObstaclePool.instance = instance;
	}

	public ObstaclePool(){
		super();
	}
	
	@Override
	protected Obstacle onAllocatePoolItem() {
		return new Obstacle();
	}

	protected void onHandleRecycleItem(final Obstacle e){
		e.getSprite().setVisible(false);
		e.getSprite().detachSelf();
		e.clean();
	}
}
