package team.brick.shootem.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.Player;

/**
 *	The EntityManager manages all entities, rendering each entity
 *	and calling its tick method.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	/**
	 * 	Calls the tick() method of each entity, and sorts the entities 
	 *  within the entities ArrayList.
	 */
	public void tick(){
		for(int i = 0;i < entities.size();i++){
			Entity e = entities.get(i);
			e.tick();
		}
		entities.sort(renderSorter);
	}
	
	/**
	 * Calls the render() method of each entity.
	 * 
	 * @param g
	 */
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
	}
	
	/**
	 * Adds an Entity to the entities ArrayList.
	 * 
	 * @param e the Entity to add to entities.
	 */
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	/**
	 * @return handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @param handler the Handler 
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets a new player to be the player.
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return entities the ArrayList of entities.
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * Sets a new ArrayList of entities to entities.
	 * @param entities
	 */
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
