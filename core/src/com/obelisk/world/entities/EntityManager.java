package com.obelisk.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.GameMain;
import com.obelisk.InputHandler;
import com.obelisk.world.WorldMain;
import com.obelisk.world.entities.skills.Skill;
import com.obelisk.world.items.Item;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.items.ItemType;
import com.obelisk.world.pathfinding.Astar;
import com.obelisk.world.pathfinding.Node;
import com.obelisk.world.pathfinding.PathfindingManager;
import com.obelisk.world.physics.Collisions;

public class EntityManager {
	
	Player player;
	
	InputHandler input;
	ItemManager itemmanager;
	Collisions collisions;
	WorldMain WM;
	Astar astar;
	
	public Array<ActiveEntity> loadedentities = new Array<ActiveEntity>();
	public Array<Projectile> projectiles = new Array<Projectile>();
	Array<ActiveEntity> unloadedentities = new Array<ActiveEntity>();

	private Array<Skill> skills;
	
	Texture character_textures;
	TextureRegion human_1;
	
	int biot_freq = 1800;
	int wave = 0;
	int endcounter = 150;
	boolean ended = false;
	
	public void show(InputHandler input, Collisions collisions, WorldMain WM, ItemManager itemmanager, PathfindingManager PM){
		this.itemmanager = itemmanager;
		this.collisions = collisions;
		this.WM = WM;
		this.input = input;
		this.astar = PM.getAstar();
		
		character_textures = new Texture(Gdx.files.internal("res/Characters.png"));
		character_textures.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		
		human_1 = new TextureRegion(character_textures, 0, 0, 64, 256);
		
		
		
		Vector3 startpos = new Vector3(128, 128, 0);
		while (!WorldMain.map.getWalkable(startpos.x, startpos.y)){
			startpos = new Vector3(MathUtils.random(64, 128), MathUtils.random(64, 128), 0);
		}
		addPlayer(startpos.x, startpos.y);
		
		
		addEntity(startpos.x + 5f, startpos.y + 5f);
		addEntity(startpos.x - 5f, startpos.y + 30f);

	}
	public void render(SpriteBatch batch, float angle, Rectangle camrect){
		//if (camrect.contains(player.pos.x, player.pos.y))
//		if (WorldMain.tic%biot_freq == 0){
//			wave++;
//			for (int i = 0; i < wave; i++){
//				float x = player.pos.x + MathUtils.random(-35f, 35f);
//				float y = player.pos.y + MathUtils.random(-35f, 35f);
//				addEntity(x, y);
//			}
//		}

		if(ended){
			endcounter--;
			if (endcounter == 0){
				WM.endGame();
			}
		}
		
		
//		if (WorldMain.tic%biot_freq == 0){
//			loadedentities.add(new Biot(player.pos.x + MathUtils.random(-30f, 30f), player.pos.y + MathUtils.random(-30f, 30f), itemmanager, this, collisions));
//			Biot biot = (Biot) loadedentities.peek();
//			biot.show(biot_1);
//			biot.addEquippedItem(new KineticPistol(itemmanager.getTexture("kineticpistol"), false, 0, 0, itemmanager, biot), true, 0);
//			if (biot_freq > 100)
//				biot_freq -=100;
//		}
		
		for (int i = 0; i < loadedentities.size; i++){
			ActiveEntity entity = loadedentities.get(i);
			entity.render(batch);
		}
			
		Projectile projectile;
		for (int i = 0; i < projectiles.size; i++){
			projectile = projectiles.get(i);
			projectile.render(batch);
		}
		
//		if (player.getPath() != null){
//			testpath = player.getPath();
//			
//			Texture nodetexture = new Texture(Gdx.files.internal("res/StoneWall.png"));
//			testpathsprites.clear();
//			for (int i = 0; i < testpath.size; i++){
//				Node node = testpath.get(i);
//				testpathsprites.add(new Sprite(nodetexture));
//				testpathsprites.peek().setSize(1f, 1f);
//				testpathsprites.peek().setPosition(node.getX(), node.getY());
//			}
//			
//			for (int i = 0; i < testpathsprites.size; i++){
//				testpathsprites.get(i).draw(batch);
//			}
//		}
	}
	public void dispose(){
		character_textures.dispose();
	}
	public void addProjectile(Projectile projectile){
		projectiles.add(projectile);
	}
	public void removeProjectile(Projectile projectile){
		projectiles.removeValue(projectile, false);
	}
	public void addPlayer(float x, float y){
		loadedentities.add(new Player(x, y, this, WM, itemmanager));
		player = (Player) loadedentities.peek();
		player.show(human_1, input, CharacterHelper.warrior);

		Item item = new Item(ItemType.Pickaxe, itemmanager.getTexture("pickaxe"), false, 0, 0);
		player.addItem(item, true);

	}
	public void removeEntity(ActiveEntity entity){
		if (entity.equals(player)){
			ended = true;
		}
		loadedentities.removeValue(entity, true);
	}
	public void addEntity(float x, float y){
		while (!WorldMain.map.getWalkable(x, y)){
			x += MathUtils.random(-3f, 3f);
			y += MathUtils.random(-3f, 3f);
		}
		loadedentities.add(new NPC(x, y, itemmanager, this));
		NPC biot = (NPC) loadedentities.peek();
		biot.show(human_1, CharacterHelper.warrior);
	}
	public Array<Node> findPath(Vector3 startPos, Vector3 endPos){
		return astar.findPath(startPos, endPos);
	}
	public boolean canPath(Vector3 startPos, Vector3 endPos){
		return astar.canPath(startPos, endPos);
	}
	public float getplayerX(){
		return player.pos.x + player.width / 2;
	}
	public float getplayerY(){
		return player.pos.y + player.height / 2;
	}
	public float getCharRange(Character character){
		return character.getRange();
	}
	public int getWave(){
		return wave;
	}
}
