package com.obelisk.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.GameMain;
import com.obelisk.InputHandler;
import com.obelisk.world.Ui;
import com.obelisk.world.WorldMain;
import com.obelisk.world.items.Item;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.pathfinding.Node;
import com.obelisk.world.physics.Collisions;

public class Player extends Character{

	Ui ui;
	GameMain GM;
	Item item;
	
	InputHandler input;
	static float MAX_SPEED = 2.5f;
	int health;
	int str, dex, con, apt, wis, cha;
	int mstr, mdex, mcon, mapt, mwis, mcha;
	
	TextureRegion blank;
	
	String faction = "player";

	public Player(float x, float y, Collisions collisions, EntityManager entitymanager, GameMain GM, ItemManager itemmanager) {
		super(x, y, itemmanager, entitymanager, collisions);
		
		this.GM = GM;
		ui = GM.planetscreen.ui;
	}
	
	public void show(TextureRegion t, InputHandler input){
		vel = new Vector3(0f, 0f, 0);
		next_pos = new Vector3();
		des_vel = new Vector3();
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
		ui.updatePlayerUi(health, inventory, equipped, this);
		
		Gdx.app.log("Scores", str + " " + dex + " " + con + " " + apt + " " + wis + " " + cha + " " + health);
		
		setupTextures(t);
		
		Texture blanktexture = new Texture(Gdx.files.internal("res/BlankItem.png"));
		blank = new TextureRegion(blanktexture, 0, 0, 64, 64);
		
		body = new Sprite(t_still);
		body.setSize(.9f, .9f * body.getHeight() / body.getWidth());
		body.setOrigin(body.getX() + body.getWidth() / 2, body.getY() + body.getHeight() / 2);
		
		superstate = idle;
		
		this.input = input;
	}

	public void render(SpriteBatch batch) {
		if (health <= 0){
			entitymanager.removeEntity(this);
		}
		
		updatesize();
		
	// ====== Input
		
		if (input.isLeftButtonHeld()){
		}
		if (input.is1Pressed()){
		}
		if (input.is2Pressed()){
		}
		if (input.is3Pressed()){
		}
		if (input.is4Pressed()){
		}
		if (input.isQPressed()){
		}
//		if (input.isEPressed() || input.isEHeld()){
//			boolean added = false;
//			for (int i = 0; i < itemmanager.worlditems.size; i++){
//				item = itemmanager.worlditems.get(i);
//				if (item.inWorld){
//					if (body.getBoundingRectangle().overlaps(item.sprite.getBoundingRectangle())){
//						if (item.canStack()){
//							for(int j = 0; j < 4; j++){
//								if (equippeditems.getKeyAt(j) != null && equippeditems.getKeyAt(j).getId() == item.getId() && item.getId() != 250){
//									item.pickedUp(this);
//									equippeditems.setValue(j, equippeditems.getValueAt(j) + 1);
//									added = true;
//									itemmanager.removeItem(item);
//									break;
//								}else if (equippeditems.getKeyAt(j) != null && equippeditems.getKeyAt(j).getId() == item.getId()){
//									equippeditems.setValue(j, item.getAmmo() + equippeditems.getKeyAt(j).getAmmo());
//									equippeditems.getKeyAt(j).setAmmo(equippeditems.getValueAt(j));
//									item.pickedUp(this);
//									added = true;
//									itemmanager.removeItem(item);
//									break;
//								}
//							}
//						}
//						if (!added){
//							for (int j = 0; j < 4; j++){
//								if (equippeditems.getKeyAt(j) == null){
//									addEquippedItem(item, false, j);
//									added = true;
//									break;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		if (input.isRightButtonPressed()){
			boolean actionperformed = false;
			for (int i = 0; i < entitymanager.loadedentities.size; i++){
				ActiveEntity entity = entitymanager.loadedentities.get(i);
				if ((int) WorldMain.touchpos.x == (int) entity.getPos().x && (int) WorldMain.touchpos.y == (int) entity.getPos().y){
					target = (Character) entity;
					superstate = hunting;
					actionperformed = true;
					System.out.println("targetfound");
					break;
				}
			}
			if (!actionperformed){
				path = findPath(pos, WorldMain.touchpos);
				next_pos.set(path.peek().getX() + body.getOriginX(), path.peek().getY() + body.getOriginY(), 0);
				superstate = moving;
			}
		}
		// ====== End Input
		// ====== Main Function (States)
		
		switch(superstate){

			case idle:
				vel.set(0,0,0);
				break;
			case moving:
				if (path != null){
					move(this);
				}else
					superstate = idle;
				break;
			case hunting:
				hunt();
				break;
			
		}	
		
		updatesprite();
		body.draw(batch);	
		ui.updatePlayerUi(health, inventory, equipped, this);
		
		// ====== Items
		
		
//		TextureRegion texture1, texture2, texture3, texture4;
//		int count1, count2, count3, count4;
//		count1 = count2 = count3 = count4 = 0;
//		if (equippeditems.getKeyAt(0) != null){ 
//			texture1 = equippeditems.getKeyAt(0).getTexture(); 
//			count1 = equippeditems.getValueAt(0);
//		}else texture1 = blank;
//		if (equippeditems.getKeyAt(1) != null){ 
//			texture2 = equippeditems.getKeyAt(1).getTexture(); 
//			count2 = equippeditems.getValueAt(1);
//		}else texture2 = blank;
//		if (equippeditems.getKeyAt(2) != null){ 
//			texture3 = equippeditems.getKeyAt(2).getTexture();
//			count3 = equippeditems.getValueAt(2);
//		}else texture3 = blank;
//		if (equippeditems.getKeyAt(3) != null){ 
//			texture4 = equippeditems.getKeyAt(3).getTexture(); 
//			count4 = equippeditems.getValueAt(3);
//		}else texture4 = blank;
		
		for (int i = 0; i < inventory.size; i++){
			item = inventory.get(i);
			if (item != null){
				item.update();
				
			}
		}
//		if (helditem != null){
//			if (helditem.canFire() && input.isLeftButtonHeld()){
//				for (int i = 0; i < 4; i++){
//					Item ammo = equippeditems.getKeyAt(i);
//					if (ammo != null && ammo.isAmmo()){
//						if (ammo.getAmmo() > 0){
//							if (helditem.fire(body.getX() + body.getOriginX(), body.getY() + body.getOriginY())){
//								ammo.setAmmo(ammo.getAmmo() - 1);
//							}
//						}
//					}
//				}
//			}else if(helditem.canBuild() && input.isLeftButtonPressed()){
//				if (helditem.getId() == 1){
//					if (WorldMain.map.addBlock(1, (int) WorldMain.touchpos.x, (int) WorldMain.touchpos.y)){
//						useItem(helditem, equippeditems.indexOfKey(helditem));
//					}
//				}
//			}
//		}
		
	}

	void updatesize() {
		width = body.getWidth();
		height = body.getHeight();
	}

//	@Override
//	public void mine(Block block) {
//		if (helditem != null)
//			block.doDamage(helditem.getBlockDamage());
//	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public Sprite getSprite() {
		return body;
	}

	@Override
	public String getFaction() {
		return faction;
	}

	public Array<Node> getPath(){
		return path;
	}
}
