package com.shopping.granny.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.andengine.util.adt.pool.GenericPool;

import com.shopping.granny.obj.collectable.Apple;
import com.shopping.granny.obj.collectable.Bread;
import com.shopping.granny.obj.collectable.Cheese;
import com.shopping.granny.obj.collectable.Coffee;
import com.shopping.granny.obj.collectable.Collectable;
import com.shopping.granny.obj.collectable.Egg;
import com.shopping.granny.obj.collectable.Lemon;
import com.shopping.granny.obj.collectable.Milk;
import com.shopping.granny.obj.collectable.Pear;
import com.shopping.granny.obj.collectable.Pepper;
import com.shopping.granny.obj.collectable.Salt;
import com.shopping.granny.obj.collectable.Tomato;

public class CollectablePool extends GenericPool<Collectable>{
	private static CollectablePool instance;
	
	public static CollectablePool sharedCollectablePool(){
		if(getInstance()==null){
			setInstance(new CollectablePool());
		}
		return instance;
	}
	
	public static void setInstance(CollectablePool collectablePool) {
		CollectablePool.instance = collectablePool;
	}

	private static CollectablePool getInstance() {
		return instance;
	}

	public CollectablePool(){
		super();
	}
//	TODO ADD THIS SHIT DUDE
	@Override
	protected Collectable onAllocatePoolItem() {
		Random randCollectable = new Random();
		int collectableType = randCollectable.nextInt(11);
		return getCollectableByType(collectableType);
	}

	protected void onHandleRecycleItem(final Collectable e){
		e.getSprite().setVisible(false);
		e.getSprite().detachSelf();
		e.clean();
	}
	
	private Collectable getCollectableByType(int collectableType) {
		Collectable res = new Tomato(0, 0);
		switch(collectableType){
		case 0: 
			res = new Apple(0, 0);
			break;
		case 1:
			res = new Bread(0, 0);
			break;
		case 2:
			res = new Cheese(0, 0);
			break;
		case 3:
			res = new Coffee(0, 0);
			break;
		case 4:
			res = new Egg(0, 0);
			break;
		case 5:
			res = new Lemon(0, 0);
			break;
		case 6:
			res = new Milk(0, 0);
			break;
		case 7:
			res = new Pear(0, 0);
			break;
		case 8:
			res = new Pepper(0, 0);
			break;
		case 9:
			res = new Salt(0, 0);
			break;
		case 10:
			res = new Tomato(0, 0);
			break;
		}
		
		return res;
	}

}
