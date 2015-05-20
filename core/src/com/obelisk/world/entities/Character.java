package com.obelisk.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.GameMain;
import com.obelisk.world.Map;
import com.obelisk.world.items.Item;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.pathfinding.Node;

public abstract class Character extends ActiveEntity{

	static float STANDARD_SPEED = 2f;
	float speed = 1 / 30f;
	
	int superstate, substate;
	static final int idle = 0;
	
	//====== SuperStates
	static final int hunting = 2;
	static final int moving = 1;
	
	//====== SubStates
		//===for Hunting
		static final int repositioning = 1;
		static final int attacking = 2;
	
	boolean atdest = false;
	
	Animations anim;
	TextureRegion t_still, t_move1, t_move2;
	
	public Array<Node> path = new Array<Node>();
	protected Array<Node> defaultpath = new Array<Node>();
	protected int pathcounter = MathUtils.random(0, 2);
	
	Character target;
	protected int maxHealth, currentHealth;
	protected int str, dex, con, apt, wis, cha;
	protected int mstr, mdex, mcon, mapt, mwis, mcha;
	
	protected int meleeAttackBonus, rangeAttackBonus, armorClass;
	
	ProfessionChart profChart;
	
	Array<Item> inventory = new Array<Item>();
	Array<Item> equipped = new Array<Item>();
	/*
	 * Equipped Item values:
	 * 	0 - Held Item
	 *  1 - Worn
	 * 	2 - Armor
	 * 	3 - Back
	 */
//	ArrayMap<Item, Integer> equippeditems = new ArrayMap<Item, Integer>(true, 4);
	ItemManager itemmanager;
	
	public Character(float x, float y, ItemManager itemmanager, EntityManager entitymanager) {
		super(x, y, entitymanager);
		
		this.itemmanager = itemmanager;

		equipped.insert(0, null);
		equipped.insert(1, null);
		equipped.insert(2, null);
		equipped.insert(3, null);
		
		tarPos = new Vector3();
		nextPos = new Vector3();
		tarPos.set(x, y, 0);
		
		anim = new Animations();
	}
	
	public void show(int profession){
		vel = new Vector3(0f, 0f, 0);
		nextPos = new Vector3();
		desVel = new Vector3();
		itempos = new Vector3(pos);
		path = null;
		
		str = GameMain.rollDice("3d6");
		dex = GameMain.rollDice("3d6");
		con = GameMain.rollDice("3d6");
		apt = GameMain.rollDice("3d6");
		wis = GameMain.rollDice("3d6");
		cha = GameMain.rollDice("3d6");

		profChart = new ProfessionChart(this);
		profChart.addLevel(profession);
		
		
		setAbilityModifiers();
		maxHealth = Integer.parseInt(profChart.getHitDie(profession).substring(2)) + mcon;
		armorClass = 10 + mdex;
		meleeAttackBonus = profChart.getProfession(profession).getBAB() + mstr;
		rangeAttackBonus = profChart.getProfession(profession).getBAB() + mdex;
		
		currentHealth = maxHealth;
	}
	public void setAbilityModifiers(){
		mstr = setMod(str);
		mdex = setMod(dex);
		mcon = setMod(con);
		mapt = setMod(apt);
		mwis = setMod(wis);
		mcha = setMod(cha);
	}
	public void levelUp(int profession){
		profChart.addLevel(profession);
		
		maxHealth += GameMain.rollDice(profChart.getHitDie(profession)) + mcon;
		meleeAttackBonus = profChart.getProfession(profession).getBAB() + mstr;
		rangeAttackBonus = profChart.getProfession(profession).getBAB() + mdex;
	}
	
	public void destroy(){
		for (int i = 0; i < equipped.size; i++){
			if (equipped.get(i) != null)
				equipped.get(i).dropped(pos.x, pos.y);
		}
		for (int i = 0; i < inventory.size; i++){
			inventory.get(i).dropped(pos.x, pos.y);
		}
	}
	public void setupTextures(TextureRegion t){
		t_still = new TextureRegion(t, 0, 0, 64, 64);
		t_move1 = new TextureRegion(t, 64, 0, 64, 64);
		t_move2 = new TextureRegion(t, 128, 0, 64, 64);
	}
	
	public void renderItems(SpriteBatch batch){
		for(int i = 0; i < equipped.size; i++){
			if(equipped.get(i) != null)
				equipped.get(i).renderFromCharacter(batch);
		}
	}
	
	public void move(ActiveEntity entity){
		speed = STANDARD_SPEED * Gdx.graphics.getDeltaTime();
		
		if (pos.x <= nextPos.x + speed && pos.x >= nextPos.x - speed && pos.y <= nextPos.y + speed && pos.y >= nextPos.y){
			if (path.size > 1){
				path.removeIndex(path.size - 1);
				nextPos.set(path.peek().getX() + 0.5f, path.peek().getY() + 0.5f, 0);
			}else{
				atdest = true;
				path = null;
				return;
			}
		}
		
		
		vel.set(nextPos.cpy().sub(pos).nor().scl(speed));
//		force = des_vel.sub(vel);
//		acc = force.scl(100 / Biot.MASS);
//		vel.add(acc);
//		vel.limit(speed);
		
		if (vel.len() > .01f)
			rotation = MathUtils.radiansToDegrees * MathUtils.atan2(vel.y, vel.x);

		pos.add(vel);
		
		if (pos.x < 0)
			pos.x = 0;
		if (pos.x > Map.CHUNK_SIZE * Map.WORLD_SIZE)
			pos.x = Map.CHUNK_SIZE * Map.WORLD_SIZE;
		if (pos.y < 0)
			pos.y = 0;
		if (pos.y > Map.CHUNK_SIZE * Map.WORLD_SIZE)
			pos.y = Map.CHUNK_SIZE * Map.WORLD_SIZE;
	}
	
	public void hunt(){
		tarPos.set(target.getPos());
		if (pos.dst(tarPos) > size){
			substate = repositioning;
		}else if (pos.dst(tarPos) <= size){
			substate = attacking;
		}else{
			substate = idle;
		}
		switch(substate){
			case idle:
				vel.set(0,0,0);
				pathcounter = 0;
				break;
			case repositioning:
				if (pathcounter == 0 || path == null){
					
					path = findPath(pos, tarPos);
					atdest = false;
					if (path == null)
						path = defaultpath;
					pathcounter = MathUtils.random(60, 180);
					nextPos.set(path.peek().getX() + .5f, path.peek().getY() + .5f, 0);
				}else
					pathcounter--;
				move(this);
				break;
			case attacking:
				pathcounter = 0;
				rotation = (float) (MathUtils.radiansToDegrees * Math.atan2(tarPos.y - pos.y, tarPos.x  - pos.x));
				attack();
				break;
		}
	}

	public void updateSprites(){
		while (rotation < 0)
			rotation += 360;
		while (rotation > 360)
			rotation -=360;
		
		if (superstate == moving){
			anim.Walking(true);
		}else if (superstate == hunting){
			if(substate == repositioning)
				anim.Walking(true);
			else if(substate == attacking)
				anim.Still();
			else
				anim.Still();
		}else
			anim.Still();
		
		body.setRotation(rotation + 90);
		body.setPosition(getPos().x - body.getOriginX(), getPos().y - body.getOriginY());
		
		for(int i = 0; i < equipped.size; i++){
			Item item = equipped.get(i);
			if(item != null)
				item.setPos(pos, rotation);
			
		}

	}
	
	private int attackCounter = 0;
	public void attack(){
		if(attackCounter == 30 - mdex * 2){
			int damage;
			if(GameMain.rollDice("1d20") + meleeAttackBonus >= target.armorClass){
				if(equipped.get(0) == null)
					damage = GameMain.rollDice("1d2") + mstr;
				else{
					damage = GameMain.rollDice(equipped.get(0).getDamage()) + mstr;
				}
					if (damage < 1) damage = 1;
				target.changeHealth(- damage);
			}
			attackCounter = 1;
		}else
			attackCounter++;
	}
	public void changeHealth(int amount){
		currentHealth += amount;
	}
	public int setMod(int abscore){
		
		if (abscore == 1)
			return -4;
		else if(abscore == 2 || abscore == 3)
			return -3;
		else if(abscore == 4 || abscore == 5)
			return -3;
		else if(abscore == 6 || abscore == 7)
			return -2;
		else if(abscore == 8 || abscore == 9)
			return -1;
		else if(abscore == 10 || abscore == 11)
			return 0;
		else if(abscore == 12 || abscore == 13)
			return 1;
		else if(abscore == 14 || abscore == 15)
			return 2;
		else if(abscore == 16 || abscore == 17)
			return 3;
		else if(abscore == 18 || abscore == 19)
			return 4;
		else if(abscore == 20 || abscore == 21)
			return 5;
		else
			return 0;
	}
	
// ========== Items
	
	public void addItem(Item item){
		inventory.add(item);
		item.pickedUp();
	}
	public void removeItem(Item item){
		inventory.removeValue(item, true);
	}

//	public void setItemCount(boolean equipped, int index, int value){
//		if (equipped){
//			inventory.setValue(index, value);
//		}
//	}
	
	public void equipItem(Item item, int pos){
		unequipItem(pos);
		inventory.removeValue(item, true);
		item.setVisible(true);
		equipped.insert(pos, item);
	}
	public void unequipItem(int pos){
		Item item = equipped.get(pos);
		
		if(item != null){
			item.setVisible(false);
			equipped.removeIndex(pos);
			inventory.add(item);
		}
	}
	public void dropItem(Item item, float x, float y){
		if (inventory.contains(item, true)){
			inventory.removeValue(item, true);
			item.dropped(x, y);
		}	
	}
//	public void useItem(Item item, int index){
//		if (equippeditems.containsKey(item)){
//			if (equippeditems.getValueAt(index) > 1){
//				setItemCount(true, index, equippeditems.getValueAt(index) - 1);
//			}else{
//				equippeditems.removeIndex(index);
//				equippeditems.insert(index, null, 0);
//			}
//		}
//	}
	public float getRange(){
		if (equipped.get(0)!= null)
			return equipped.get(0).getRange();
		else return size;
	}
	
// ========== Animations

	private class Animations{
		int counter = 0;
		int changetime = 13;
		boolean isWalking = false;
		
		public void Walking(boolean isWalking){
			if (this.isWalking != isWalking){
				counter = 0;
				this.isWalking = true;
			}
			counter++;
			if (counter < changetime)
				body.setRegion(t_move1);
			else if (counter < 2 * changetime && counter >= changetime)
				body.setRegion(t_still);
			else if (counter < 3 * changetime && counter >= changetime * 2)
				body.setRegion(t_move2);
			else if (counter < 4 * changetime && counter >= changetime * 3)
				body.setRegion(t_still);
			else
				counter = 0;
		}
		public void Still(){
			isWalking = false;
			body.setRegion(t_still);
		}
		public void attacking(){
			equipped.get(0);
		}
	}
}

