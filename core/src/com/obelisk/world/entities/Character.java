package com.obelisk.world.entities;

import com.badlogic.gdx.Gdx;
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

	float speed = 1/30f;
	float range = 1f;
	
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
	Array<Node> defaultpath = new Array<Node>();
	int pathcounter = MathUtils.random(0, 2);
	
	Character target;
	protected int health;
	protected int str, dex, con, apt, wis, cha;
	protected int mstr, mdex, mcon, mapt, mwis, mcha;
	
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
		
		//profChart = new ProfessionChart(this);
		//profChart.addLevel("Miner");
		
		tarPos = new Vector3();
		nextPos = new Vector3();
		tarPos.set(x, y, 0);
		
		anim = new Animations();
	}
	
	public void show(){
		vel = new Vector3(0f, 0f, 0);
		nextPos = new Vector3();
		desVel = new Vector3();
		itempos = new Vector3(pos);
		path = null;
		
		str = GameMain.d6(3);
		dex = GameMain.d6(3);
		con = GameMain.d6(3);
		apt = GameMain.d6(3);
		wis = GameMain.d6(3);
		cha = GameMain.d6(3);

		mstr = setMod(str);
		mdex = setMod(dex);
		mcon = setMod(con);
		mapt = setMod(apt);
		mwis = setMod(wis);
		mcha = setMod(cha);
		
		health = 8 + mcon;
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
	
	public void move(ActiveEntity entity){
		speed = Golem.MAX_SPEED * Gdx.graphics.getDeltaTime();
		
		if (pos.x <= nextPos.x + speed && pos.x >= nextPos.x - speed && pos.y <= nextPos.y + speed && pos.y >= nextPos.y){
			if (path.size > 1){
				path.removeIndex(path.size - 1);
				nextPos.set(path.peek().getX() + body.getOriginX(), path.peek().getY() + body.getOriginY(), 0);
	
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
		if (!atdest){
			substate = repositioning;
		}else if (atdest){
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
				if (pathcounter == 0){
					Vector3 desPos;
					float toler = .75f;
					int range = 5;
					float slope = (tarPos.y - pos.y) / (tarPos.x - pos.x);
					int direction = 0;
					do{
						desPos = tarPos.cpy();
						if (direction == 8 || direction == 0 && tarPos.x < pos.x + range && tarPos.x > pos.x - range && tarPos.y < pos.y){
							direction = 1;
							desPos.y += 1;
						}else if (direction == 1 || direction == 0 && slope > toler && tarPos.x < pos.x + toler){
							direction = 2;
							desPos.x += 1;
							desPos.y += 1;
						}else if (direction == 2 || direction == 0 && slope < toler && slope > -toler && tarPos.x < pos.x){
							direction = 3;
							desPos.x += 1;
						}else if (direction == 3 || direction == 0 && slope < -toler && tarPos.x < pos.x + toler){
							direction = 4;
							desPos.x += 1;
							desPos.y -= 1;
						}else if (direction == 4 || direction == 0 && tarPos.x < pos.x + range && tarPos.x > pos.x - range && tarPos.y > pos.y){
							direction = 5;
							desPos.y -= 1;
						}else if (direction == 5 || direction == 0 && slope > toler && tarPos.x > pos.x + toler){
							direction = 6;
							desPos.x -= 1;
							desPos.y -= 1;
						}else if (direction == 6 || direction == 0 && slope < toler && slope > -toler && tarPos.x > pos.x){
							direction = 7;
							desPos.x -= 1;
						}else if (direction == 7 || direction == 0 && slope < -toler && tarPos.x > pos.x + toler){
							direction = 8;
							desPos.x -= 1;
							desPos.y += 1;
						}
					}while(!canPath(pos, desPos));
					path = findPath(pos, desPos);
					atdest = false;
					if (path == null)
						path = defaultpath;
					pathcounter = MathUtils.random(60, 180);
					nextPos.set(path.peek().getX() + .1f, path.peek().getY() + .1f, 0);
				}else
					pathcounter--;
				move(this);
				break;
			case attacking:
				pathcounter = 0;
				rotation = (float) (MathUtils.radiansToDegrees * Math.atan2(tarPos.y, tarPos.x) - 90);
				if(pos.dst2(target.pos) > 1)
					atdest = false;
				//helditem.use();
				break;
		}
	}
//	public void fire(){
//		if (weapon.canFire()){
//			for (int i = 0; i < 4; i++){
//				Item ammo = equippeditems.getKeyAt(i);
//				if (ammo != null && ammo.isAmmo()){
//					if (ammo.getAmmo() > 0){
//						if (weapon.fire(body.getX() + body.getOriginX(), body.getY() + body.getOriginY())){
//							ammo.setAmmo(ammo.getAmmo() - 1);
//						}
//					}
//				}
//			}
//		}
//	}
	public void updatesprite(){
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
	
	public void addItem(Item item, boolean isNew){
		inventory.add(item);
		item.pickedUp(this);
		if (isNew)
			itemmanager.addItem(item);
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
		equipped.insert(pos, item);
	}
	public void unequipItem(int pos){
		Item item = equipped.get(pos);
		if(item != null){
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
		else return range;
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
	}
}

