package com.obelisk.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
<<<<<<< HEAD
=======
import com.badlogic.gdx.utils.ArrayMap;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
import com.obelisk.world.Map;
import com.obelisk.world.items.Item;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.pathfinding.Node;
import com.obelisk.world.physics.Collisions;

public abstract class Character extends ActiveEntity{

	float speed = 1/30f;
	float range = 1f;
	
	int superstate, substate;
	static final int idle = 0;
	
	//====== SuperStates
	static final int hunting = 2;
	static final int moving = 1;
	
<<<<<<< HEAD
	//====== Substates
=======
	//====== SubStates
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
	static final int repositioning = 1;
	static final int firing = 2;
	
	boolean atdest = false;
	
	Animations anim;
	TextureRegion t_still, t_move1, t_move2;
	
	public Array<Node> path = new Array<Node>();
	Array<Node> defaultpath = new Array<Node>();
	int pathcounter = MathUtils.random(0, 2);
	
	Character target;
	
<<<<<<< HEAD
=======
	ProfessionChart profChart;
	
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
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
	
	public Character(float x, float y, ItemManager itemmanager, EntityManager entitymanager, Collisions collisions) {
		super(x, y, entitymanager, collisions);
		
		this.itemmanager = itemmanager;

		equipped.insert(0, null);
		equipped.insert(1, null);
		equipped.insert(2, null);
		equipped.insert(3, null);
		
<<<<<<< HEAD
=======
		//profChart = new ProfessionChart(this);
		//profChart.addLevel("Miner");
		
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
		tar_pos = new Vector3();
		next_pos = new Vector3();
		tar_pos.set(x, y, 0);
		
		anim = new Animations();
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
		speed = Biot.MAX_SPEED * Gdx.graphics.getDeltaTime();
		
		if (pos.x <= next_pos.x + speed && pos.x >= next_pos.x - speed && pos.y <= next_pos.y + speed && pos.y >= next_pos.y){
			if (path.size > 1){
				path.removeIndex(path.size - 1);
				next_pos.set(path.peek().getX() + body.getOriginX(), path.peek().getY() + body.getOriginY(), 0);
			}else{
				atdest = true;
				path = null;
			}
		}
		
		vel.set(next_pos.cpy().sub(pos).nor().scl(speed));
//		force = des_vel.sub(vel);
//		acc = force.scl(100 / Biot.MASS);
//		vel.add(acc);
//		vel.limit(speed);
		if (vel.len() > .02f)
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
		tar_pos.set(target.getPos());
		if (!atdest){
			substate = repositioning;
		}else if (atdest){
			substate = firing;
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
					Vector3 des_pos = tar_pos;
					float toler = .75f;
					int range = 5;
					float slope = (tar_pos.y - pos.y) / (tar_pos.x - pos.x);
					if (tar_pos.x < pos.x + range && tar_pos.x > pos.x - range && tar_pos.y < pos.y)
//						direction = 1;
						des_pos.y += 1;
					else if (slope > toler && tar_pos.x < pos.x + toler){
//						direction = 2;
						des_pos.x += 1;
						des_pos.y += 1;
					}
					else if (slope < toler && slope > -toler && tar_pos.x < pos.x)
//						direction = 3;
						des_pos.x += 1;
					else if (slope < -toler && tar_pos.x < pos.x + toler){
//						direction = 4;
						des_pos.x += 1;
						des_pos.y -= 1;
					}
					if (tar_pos.x < pos.x + range && tar_pos.x > pos.x - range && tar_pos.y > pos.y)
//						direction = 5;
						des_pos.y -= 1;
					else if (slope > toler && tar_pos.x > pos.x + toler){
//						direction = 6;
						des_pos.x -= 1;
						des_pos.y -= 1;
					}
					else if (slope < toler && slope > -toler && tar_pos.x > pos.x)
//						direction = 7;
						des_pos.x -= 1;
					else if (slope < -toler && tar_pos.x > pos.x + toler){
//						direction = 8;
						des_pos.x -= 1;
						des_pos.y += 1;
					}
					path = findPath(pos, des_pos);
					atdest = false;
					if (path == null)
						path = defaultpath;
					pathcounter = MathUtils.random(60, 180);
					next_pos.set(path.peek().getX() + .1f, path.peek().getY() + .1f, 0);
				}else
					pathcounter--;
				move(this);
				break;
			case firing:
				pathcounter = 0;
				rotation = (float) (MathUtils.radiansToDegrees * Math.atan2(tar_pos.y, tar_pos.x));
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
		
		if (vel.len() > 0.01f){
			anim.Walking(true);
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
<<<<<<< HEAD
=======
		unequipItem(pos);
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
		inventory.removeValue(item, true);
		equipped.insert(pos, item);
	}
	public void unequipItem(int pos){
		Item item = equipped.get(pos);
<<<<<<< HEAD
		equipped.removeIndex(pos);
		inventory.add(item);
=======
		if(item != null){
			equipped.removeIndex(pos);
			inventory.add(item);
		}
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
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
<<<<<<< HEAD
		int changetime = 15;
=======
		int changetime = 13;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
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

