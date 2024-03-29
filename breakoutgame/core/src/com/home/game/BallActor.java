package com.home.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.home.game.BreakoutGame;
import com.home.game.Contdead;

public class BallActor extends Actor implements Contdead {

	private ShapeRenderer shapeRenderer;
	private Fixture fixture;
	private boolean isDying = false;
	private Vector2 worldToScreen;

	public BallActor(World world, Vector2 scale) {
		shapeRenderer = new ShapeRenderer();
		worldToScreen = scale;

		setColor(Color.WHITE);
		setSize(20, 20);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(10, 20);
		Body body = world.createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(1);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 1.0f;
		body.setLinearVelocity(20f, 20f);
		body.setUserData(this);
		fixture = body.createFixture(fixtureDef);

		circle.dispose();
	}

	@Override
	public float getX() {
		return fixture.getBody().getPosition().x;
	}

	@Override
	public float getY() {
		return fixture.getBody().getPosition().y;
	}

	public void act(float delta) {
		if (getY() * worldToScreen.x + getHeight() < 0) {
			isDying = true;
		}

	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() * worldToScreen.x, getY() * worldToScreen.y, 0);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(0, 0, getHeight() / 2);
		shapeRenderer.end();
		batch.begin();
	}
	
	@Override
	public float getHeight() {
		return super.getHeight() * BreakoutGame.scale.y;
	}

	public void reset() {
		isDying = false;
		fixture.getBody().setTransform(10, 20, 0);
		fixture.getBody().setLinearVelocity(20f, 20f);
	}

	@Override
	public void contact(Contdead other) {
	}

	@Override
	public boolean isDying() {
		return isDying;
	}

}
