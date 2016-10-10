package team.brick.shootem.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import team.brick.shootem.game.Handler;

/**
 *  An Entity is anything in the game which is not a Tile. 
 *	It defines the dimensions, boundaries, and position
 *	on the screen of an Entity.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public abstract class Entity {

	public static final int DEFAULT_HEALTH = 3;
	
	protected int health;
	protected Handler handler;
	protected float x, y;
	protected int posX, posY;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean active = true;
	protected int collidedWith;
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(int amt){
		health -= amt;
		if(health <= 0){
			active = false;
			die();
		}
	}
	
	/**
	 * Checks for collision between entities by checking for an intersection
	 * between boundaries of each entity.
	 * 
	 * @param xOffset
	 * @param yOffset
	 * @return true there is a collision
	 * @return false if there is no collision
	 */
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				collidedWith = handler.getWorld().getEntityManager().getIndex(e);
				return true;
			}
				
		}
		return false;
	}
	
	/**
	 * Returns a rectangle specifying the collision bounds for an Entity.
	 * 
	 * @param xOffset
	 * @param yOffset
	 * @return Rectangle with collision bounds.
	 */
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public boolean isActive(){
		return active;
	}
	
	/**
	 * @return x the x position of the entity
	 */
	public float getX() {
		return x;
	}

	/**
	 * Set the x position of the Entity
	 * 
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * @return y the y position of the Entity
	 */
	public float getY() {
		return y;
	}

	/**
	 * Set the y position of the Entity
	 * 
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * @return width the Width of the Entity
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the Entity
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return height the height of the Entity
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the Height of the Entity
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return health the current health of the Creature
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the new health of the Creature
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
}
