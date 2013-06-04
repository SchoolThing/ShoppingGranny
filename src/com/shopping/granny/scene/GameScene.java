package com.shopping.granny.scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSCounter;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.shopping.granny.activity.MainActivity;
import com.shopping.granny.obj.CollectablePool;
import com.shopping.granny.obj.Granny;
import com.shopping.granny.obj.Obstacle;
import com.shopping.granny.obj.ObstaclePool;
import com.shopping.granny.obj.collectable.Collectable;
import com.shopping.granny.obj.collectable.Tomato;
import com.shopping.granny.singleton.ObstaclesCooldown;

public class GameScene extends Scene implements IOnSceneTouchListener {
	private static float MOVE_DURATION = 0.15f;
	private Camera mCamera;
	private Granny mGranny;
	private Sprite shootButton;
	private PhysicsHandler mPhysicsHandler;
	private MoveYModifier mMoveUpModifier;
	private MoveYModifier mMoveDownModifier;
	private float GRANNY_SPEED = 200;
	private float obstacleSpeed = 200;
	private LinkedList<Obstacle> obstacles;
	private LinkedList<Collectable> collectables;
	private int OFFSET = 94;
	private int MINIMUM_Y = 5;
	private float score;
	private long extraScore;
	private Text scoreBoard;
	private Text FPSText;
	private Text levelText;
	private FPSCounter FPS;
	private int lastLane;
	private int collectableType;
	private float swipeInitialX;
	private float swipeInitialY;

	public Text getLevelText() {
		return levelText;
	}

	public float getObstacleSpeed() {
		return obstacleSpeed;
	}

	public void setObstacleSpeed(float obstacleSpeed) {
		this.obstacleSpeed = obstacleSpeed;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float f) {
		this.score = f;
	}

	public long getExtraScore() {
		return extraScore;
	}

	public Text getScoreBoard() {
		return scoreBoard;
	}

	public void setScoreBoard(Text scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public Text getFPSText() {
		return FPSText;
	}

	public void setFPSText(Text fPSText) {
		FPSText = fPSText;
	}

	public FPSCounter getFPS() {
		return FPS;
	}

	public void setFPS(FPSCounter fPS) {
		FPS = fPS;
	}

	public static void setMoveDuration(float moveDuration) {
		MOVE_DURATION = moveDuration;
	}

	public static float getMoveDuration() {
		return MOVE_DURATION;
	}

	public Granny getmGranny() {
		return mGranny;
	}

	public GameScene() {
		mCamera = MainActivity.getSharedInstance().getmCamera();

		Random rand = new Random();
		collectableType = rand.nextInt(11);

		obstacles = new LinkedList<Obstacle>();
		collectables = new LinkedList<Collectable>();

		FPS = new FPSCounter();

		this.registerUpdateHandler(FPS);

		float bgHeight = MainActivity.getSharedInstance()
				.getmBackgroundTextureRegion().getHeight();
		float bgWidth = MainActivity.getSharedInstance()
				.getmBackgroundTextureRegion().getWidth();

		Sprite mBackgroundSprite = new Sprite(0, 0, bgWidth, bgHeight,
				MainActivity.getSharedInstance().getmBackgroundTextureRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
		attachChild(mBackgroundSprite);
		MainActivity.getSharedInstance().setmCurrentScene(this);

		mGranny = new Granny(250, MINIMUM_Y
				+ OFFSET
				* 2
				+ OFFSET
				/ 2
				- MainActivity.getSharedInstance().getmGrannyTextureRegion()
						.getHeight() / 2, MainActivity.getSharedInstance()
				.getmGrannyTextureRegion(), MainActivity.getSharedInstance()
				.getVertexBufferObjectManager());

		mGranny.getGrannySprite().animate(200);
		attachChild(mGranny.getGrannySprite());

		mGranny.getGrannySprite().setPosition(
				250,
				MINIMUM_Y
						+ OFFSET
						* 2
						+ OFFSET
						/ 2
						- MainActivity.getSharedInstance()
								.getmGrannyTextureRegion().getHeight() / 2);

		FPSText = new Text(170, 0, MainActivity.getSharedInstance().getmFont(),
				"FPS: " + (long) FPS.getFPS(), "FPS: XXX".length(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
		attachChild(FPSText);

		long totalScore = ((long) score) + extraScore;

		scoreBoard = new Text(0, 0,
				MainActivity.getSharedInstance().getmFont(), "Score: "
						+ totalScore, "Score: XXXX".length(), MainActivity
						.getSharedInstance().getVertexBufferObjectManager());
		attachChild(scoreBoard);

		levelText = new Text(300, 0, MainActivity.getSharedInstance()
				.getmFont(), "Level: ", "Level: XXXX".length(), MainActivity
				.getSharedInstance().getVertexBufferObjectManager());
		attachChild(levelText);

		shootButton = new Sprite(20, 60, MainActivity.getSharedInstance()
				.getmShootButtonTextureRegion(), MainActivity
				.getSharedInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				int eventAction = pSceneTouchEvent.getAction();
				switch (eventAction) {
				case TouchEvent.ACTION_UP:
					if (mGranny.isShootReady()) {
						mGranny.setShootReady(false);
						shootButton.setVisible(false);
						shootPin();
					}
					break;
				}
				return true;
			}
		};
		
		shootButton.setVisible(false);
		attachChild(shootButton);
		
		registerTouchArea(shootButton);
		this.setOnSceneTouchListener(this);

		resetValues();

	}

	public void addLineUp() {

		if (!ObstaclesCooldown.getSharedInstance().checkValidity())
			return;
		ArrayList<Integer> lanes = new ArrayList<Integer>();
		lanes.clear();
		for (int i = 0; i < 5; i++) {
			lanes.add(MINIMUM_Y + OFFSET * i);
		}
		Random randObstacleNumber = new Random();
		int obstaclesNumber = randObstacleNumber.nextInt(4);
		Random randLane = new Random();
		int x = (int) MainActivity.CAMERA_WIDTH;
		int y = 0;
		for (int i = 0; i <= obstaclesNumber; i++) {
			int lane;
			while ((lane = randLane.nextInt(5)) == lastLane)
				;
			lastLane = lane;
			y = MINIMUM_Y + OFFSET * lane;
			if (lanes.contains(y)) {
				lanes.remove(lanes.indexOf(y));
				addObstacle(x, y);
			}
		}
		int collectableLane = randLane.nextInt(lanes.size());
		y = lanes.get(collectableLane);
		if (lanes.contains(y)) {
			addCollectable(x, y);
		}
	}

	public void addObstacle(int x, int y) {
		Obstacle obstacle = ObstaclePool.sharedObstaclePool().obtainPoolItem();
		obstacle.getSprite().setPosition(x, y);
		obstacle.getSprite().detachSelf();
		obstacle.getSprite().setVisible(true);
		obstacle.setmPhysicsHandler(new PhysicsHandler(obstacle.getSprite()));
		obstacle.getSprite().registerUpdateHandler(
				obstacle.getmPhysicsHandler());
		obstacle.getmPhysicsHandler().setVelocityX(-obstacleSpeed);
		attachChild(obstacle.getSprite());
		obstacles.add(obstacle);
	}

	public void addCollectable(int x, int y) {
		Collectable collectable = CollectablePool.sharedCollectablePool()
				.obtainPoolItem();
		collectable.getSprite().setPosition(x,
				y + (OFFSET - collectable.getSprite().getHeight()) / 2);
		collectable.getSprite().detachSelf();
		collectable.getSprite().setVisible(true);
		PhysicsHandler ph = new PhysicsHandler(collectable.getSprite());
		collectable.getSprite().registerUpdateHandler(ph);
		ph.setVelocityX(-obstacleSpeed);
		attachChild(collectable.getSprite());
		collectables.add(collectable);
	}

	public void resetValues() {
		clearUpdateHandlers();
		setScore(0);
		extraScore = 0;
		mGranny.restart();
		clearChildScene();
		purge();
		MOVE_DURATION = 0.15f;
		setObstacleSpeed(200);
		registerUpdateHandler(new GameLoopUpdateHandler());
		registerUpdateHandler(FPS);
		ObstaclePool.sharedObstaclePool().shufflePoolItems();
		CollectablePool.sharedCollectablePool().shufflePoolItems();
	}

	public void purge() {
		for (Obstacle o : obstacles) {
			ObstaclePool.sharedObstaclePool().recyclePoolItem(o);
		}
		obstacles.clear();
		for (Collectable c : collectables) {
			CollectablePool.sharedCollectablePool().recyclePoolItem(c);
		}
		collectables.clear();
	}

	public void detach() {
		Log.v("Shopping Granny", "Game Scene detached");
		clearUpdateHandlers();
		for (Obstacle o : obstacles) {
			ObstaclePool.sharedObstaclePool().recyclePoolItem(o);
		}
		obstacles.clear();
		for (Collectable c : collectables) {
			CollectablePool.sharedCollectablePool().recyclePoolItem(c);
		}
		detachChildren();
		ObstaclePool.setInstance(null);
		CollectablePool.setInstance(null);
	}

	public void cleaner() {
		synchronized (obstacles) {
			Iterator<Obstacle> it = obstacles.iterator();
			while (it.hasNext()) {
				Obstacle o = it.next();
				if (o.getSprite().getX() <= o.getSprite().getWidth()) {
					ObstaclePool.sharedObstaclePool().recyclePoolItem(o);
					it.remove();
					continue;
				}
				if (o.getSprite().collidesWith(mGranny.getGrannySprite())) {
					setChildScene(new EndGameScene(mCamera));
					clearUpdateHandlers();
				}
				if(o.getSprite().collidesWith(mGranny.getPin())){
					ObstaclePool.sharedObstaclePool().recyclePoolItem(o);
					it.remove();
					mGranny.getPin().detachSelf();
					mGranny.setPin(null);
				}
			}
		}
		synchronized (collectables) {
			Iterator<Collectable> it2 = collectables.iterator();
			while (it2.hasNext()) {
				Collectable c = it2.next();
				if (c.getSprite().getX() <= c.getSprite().getWidth()) {
					CollectablePool.sharedCollectablePool().recyclePoolItem(c);
					it2.remove();
					continue;
				}
				if (c.getSprite().collidesWith(mGranny.getGrannySprite())) {
					extraScore += 15;
					mGranny.getInventory().add(c);
					if (((mGranny.getInventory().size() % 10) == 0)) {
						shootButton.setVisible(true);
						mGranny.setShootReady(true);
					}
					CollectablePool.sharedCollectablePool().recyclePoolItem(c);
					it2.remove();
					continue;
				}
			}
		}
	}

	public void clear() {
		synchronized (this) {
			Iterator<Obstacle> it = obstacles.iterator();
			while (it.hasNext()) {
				Obstacle o = it.next();
				ObstaclePool.sharedObstaclePool().recyclePoolItem(o);
				it.remove();
				continue;
			}
			Iterator<Collectable> it2 = collectables.iterator();
			while (it2.hasNext()) {
				Collectable c = it2.next();
				CollectablePool.sharedCollectablePool().recyclePoolItem(c);
				it2.remove();
				continue;
			}
		}
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		int eventAction = pSceneTouchEvent.getAction();
		boolean moving = false;
		switch (eventAction) {
		case TouchEvent.ACTION_DOWN:
			this.swipeInitialX = pSceneTouchEvent.getX();
			this.swipeInitialY = pSceneTouchEvent.getY();
			break;
		case TouchEvent.ACTION_MOVE:
			if (((pSceneTouchEvent.getY() - this.swipeInitialY) < -10)
					&& (!moving)) {
				MoveGrannyUp();
			} else if (((pSceneTouchEvent.getY() - this.swipeInitialY) > 10)
					&& (!moving)) {
				MoveGrannyDown();
			}
			break;
		case TouchEvent.ACTION_UP:
			moving = false;
		}
		return true;
	}

	private void MoveGrannyUp() {
		if (mGranny.getGrannySprite().getY() <= MINIMUM_Y
				+ OFFSET
				/ 2
				- MainActivity.getSharedInstance().getmGrannyTextureRegion()
						.getHeight() / 2) {
			mGranny.getGrannySprite().setY(
					MINIMUM_Y
							+ OFFSET
							/ 2
							- MainActivity.getSharedInstance()
									.getmGrannyTextureRegion().getHeight() / 2);
			return;
		}

		if (mGranny.isMoveable()) {
			Log.e("MYTAGSS", "" + mGranny.getGrannySprite().getY() + "");
			mMoveUpModifier = new MoveYModifier(MOVE_DURATION, mGranny
					.getGrannySprite().getY(), mGranny.getGrannySprite().getY()
					- OFFSET) {
				@Override
				protected void onModifierStarted(final IEntity pEntity) {
					mGranny.setMoveable(false);
				}

				@Override
				protected void onModifierFinished(final IEntity pEntity) {
					mGranny.setMoveable(true);
				}
			};
			GameScene.this.mGranny.getGrannySprite().clearEntityModifiers();
			GameScene.this.mGranny.getGrannySprite().registerEntityModifier(
					mMoveUpModifier);
		}
	}

	private void MoveGrannyDown() {
		if (mGranny.getGrannySprite().getY() >= (OFFSET * 4 + MINIMUM_Y
				+ OFFSET / 2 - MainActivity.getSharedInstance()
				.getmGrannyTextureRegion().getHeight() / 2) - 10) {
			mGranny.getGrannySprite().setY(
					OFFSET
							* 4
							+ MINIMUM_Y
							+ OFFSET
							/ 2
							- MainActivity.getSharedInstance()
									.getmGrannyTextureRegion().getHeight() / 2);
			return;
		}
		if (mGranny.isMoveable()) {
			Log.e("MYTAGSS", "" + mGranny.getGrannySprite().getY() + "");
			mMoveDownModifier = new MoveYModifier(MOVE_DURATION, mGranny
					.getGrannySprite().getY(), mGranny.getGrannySprite().getY()
					+ OFFSET) {
				@Override
				protected void onModifierStarted(final IEntity pEntity) {
					mGranny.setMoveable(false);
				}

				@Override
				protected void onModifierFinished(final IEntity pEntity) {
					mGranny.setMoveable(true);
				}
			};
			GameScene.this.mGranny.getGrannySprite().registerEntityModifier(
					mMoveDownModifier);
		}
	}

	private void shootPin() {
		AnimatedSprite sprite = new AnimatedSprite(mGranny.getGrannySprite().getX() + 40, mGranny.getGrannySprite().getY()
				+ mGranny.getGrannySprite().getHeight() / 2 - MainActivity.getSharedInstance().getmRollingPinTextureRegion().getHeight() / 2,
				MainActivity.getSharedInstance().getmRollingPinTextureRegion(),
				MainActivity.getSharedInstance().getVertexBufferObjectManager());
		mGranny.setPin(sprite);
		mGranny.getPin().animate(100);
		PhysicsHandler handler = new PhysicsHandler(mGranny.getPin());
		mGranny.setPinHandler(handler);
		mGranny.getPin().registerUpdateHandler(mGranny.getPinHandler());
		mGranny.getPinHandler().setVelocityX(300);
		attachChild(mGranny.getPin());
	}
}