package com.obelisk.world;

import com.badlogic.gdx.Gdx;
<<<<<<< HEAD
=======
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
<<<<<<< HEAD
=======
import com.badlogic.gdx.utils.ObjectMap;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
import com.obelisk.GameMain;
import com.obelisk.world.entities.Player;
import com.obelisk.world.items.Item;

public class Ui {

	GameMain GM;
	Player player;
	float HEIGHT, WIDTH;
	
	Stage uistage, pausestage;
<<<<<<< HEAD
=======
	TextureAtlas atlas;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
	Table infocorner, chartable, pausemenu, invMenu;
	VerticalGroup inventory, equipment, equipmentLabels;
	
	ScrollPane invPane;
		
	Skin uiskin;
<<<<<<< HEAD
		
=======
	
	Color textcolor = new Color();//Color.rgb565(120, 68, 33);
	
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
	TextButton returnbutton, charbutton, blankButton;
	
	int phealth = 0;
	boolean needhup = true;
	Label health, invLabel, equipLabel, heldLabel, wornLabel, armorLabel, backLabel;
	TextButton heldButton, wornButton, armorButton, backButton;
	Array<TextButton> playerItems, otherItems;
	Array<Item> playerInv, otherInv, playerEquip;
	
	public void show(float HEIGHT, float WIDTH, GameMain GM){
		this.HEIGHT = HEIGHT;
		this.WIDTH = WIDTH;
		this.GM = GM;
<<<<<<< HEAD
						
=======
		
		textcolor.set(120f/256, 68f/256, 33f/256, 1);
				
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
		// Ui Stage -------------------------------------------
		uistage = new Stage();
		Gdx.input.setInputProcessor(uistage);
		
		uiskin = new Skin(Gdx.files.internal("res/uiskin.json"));	
		
		health = new Label("Health: " + phealth, uiskin);
		
		infocorner = new Table();
		infocorner.setBackground(uiskin.getDrawable("default-round-large"));
		infocorner.add(health).pad(4, 8, 4, 0);
		infocorner.setPosition(0, HEIGHT * (1 - 1f/5f));
		infocorner.setSize(WIDTH / 6, HEIGHT / 5);
		infocorner.left().top();
		
		uistage.addActor(infocorner);
		
		charbutton = new TextButton("Info", uiskin);
		charbutton.addAction(new charAction(charbutton));
		
		chartable = new Table();
		chartable.setSize(WIDTH, HEIGHT);
		chartable.setPosition(0, 0);
		chartable.left().bottom();
		
		blankButton = new TextButton("", uiskin);
		invLabel = new Label("Inventory", uiskin);
		equipLabel = new Label("Equipped Items", uiskin);
		heldLabel = new Label("Held:", uiskin);
		heldButton = new TextButton("", uiskin);
		wornLabel = new Label("Worn:", uiskin);
		wornButton = new TextButton("", uiskin);
		armorLabel = new Label("Armor:", uiskin);
		armorButton = new TextButton("", uiskin);
		backLabel = new Label("Back:", uiskin);
		backButton = new TextButton("", uiskin);
		heldButton.setVisible(false);
		wornButton.setVisible(false);
		armorButton.setVisible(false);
		backButton.setVisible(false);
		
		equipmentLabels = new VerticalGroup();
		equipmentLabels.left();
		equipmentLabels.addActor(heldLabel);
		equipmentLabels.addActor(wornLabel);
		equipmentLabels.addActor(armorLabel);
		equipmentLabels.addActor(backLabel);

		equipment = new VerticalGroup();
		equipment.addActor(heldButton);
		equipment.addActor(wornButton);
		equipment.addActor(armorButton);
		equipment.addActor(backButton);
				
		playerItems = new Array<TextButton>();
		
		invMenu = new Table(uiskin);
		invMenu.setBackground(uiskin.getDrawable("default-round-large"));
		invMenu.add(equipLabel).center().row();
		invMenu.add(equipmentLabels).left();
		invMenu.add(equipment).left().row();
		invMenu.add(invLabel).row();
		invMenu.top().left();
		invMenu.setVisible(false);
		
		inventory = new VerticalGroup();
		inventory.setSize(WIDTH / 4, HEIGHT / 4);
		
		invPane = new ScrollPane(inventory, uiskin);
		invMenu.add(invPane);
		
		chartable.add(invMenu).top().left().size(WIDTH / 4, HEIGHT / 1.5f);
		chartable.row();
		chartable.add(charbutton).left().bottom().minWidth(WIDTH / 4);
		
		uistage.addActor(chartable);
		
		// Pause Stage ---------------------------------------
		pausestage = new Stage();
				
		returnbutton = new TextButton("Quit to Main Menu", uiskin);
		returnAction action = new returnAction(returnbutton);
		returnbutton.addAction(action);		
		
		pausemenu = new Table();
		pausemenu.add(returnbutton);
		pausemenu.setPosition(0, 0);
		pausemenu.setSize(WIDTH, HEIGHT);
		
		pausestage.addActor(pausemenu);
		
	}
	
	public void resize(float WIDTH, float HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		infocorner.setPosition(0, HEIGHT * (1 - 1f/5f));
		infocorner.setSize(WIDTH / 6, HEIGHT / 5);
		chartable.setSize(WIDTH, HEIGHT);
		pausemenu.setSize(WIDTH, HEIGHT);
	}

	public void render(int state){
		switch (state){
			case 1:{
				if (needhup = true)
					health.setText("Health: " + phealth);
				
				Gdx.input.setInputProcessor(uistage);
				uistage.act(Gdx.graphics.getDeltaTime());
				uistage.draw();				
				break;
			}
			case 2:{
				Gdx.input.setInputProcessor(pausestage);
				pausestage.act();
				pausestage.draw();
				break;
			}
		}
	}
	
	public void updatePlayerUi(int health, Array<Item> inv, Array<Item> equip, Player player){
		this.player = player;
		if (phealth != health)
			needhup = true;
		else
			needhup = false;
		phealth = health;
		playerInv = inv;
		playerEquip = equip;
		
		for (int i = 0; i < playerEquip.size; i++){
			Item pItem = playerEquip.get(i);
			switch(i){
			case 0:{
				if (pItem != null && pItem.getButton() == null){
					TextButton button = new TextButton(pItem.getName(), uiskin);
<<<<<<< HEAD
					Gdx.app.log("Ui.updatePlayerUi", "pItem.getName for case 0: " + pItem.getName());
					button.addAction(new equipAction(heldButton, pItem));
=======
					button.addAction(new equipAction(button, pItem));
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
					pItem.setButton(button);
				}
				if (pItem != null && heldButton != pItem.getButton()){
					equipment.addActorAt(0, pItem.getButton());
					equipment.removeActor(heldButton);
					heldButton = pItem.getButton();
					heldButton.setVisible(true);
					invMenu.invalidate();
				}else if (pItem == null){
<<<<<<< HEAD
=======
					heldButton = blankButton;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
					heldButton.setVisible(false);
				}
				break;
			}
			case 1:
				if (pItem != null && pItem.getButton() == null){
					TextButton button = new TextButton(pItem.getName(), uiskin);
<<<<<<< HEAD
					button.addAction(new equipAction(wornButton, pItem));
					playerEquip.get(i).setButton(button);
				}
				if (pItem != null){
					wornButton = pItem.getButton();
					wornButton.setVisible(true);
				}else{
					//wornButton.setVisible(false);
=======
					button.addAction(new equipAction(button, pItem));
					playerEquip.get(i).setButton(button);
				}
				if (pItem != null && wornButton != pItem.getButton()){
					equipment.addActorAt(0, pItem.getButton());
					equipment.removeActor(wornButton);
					wornButton = pItem.getButton();
					wornButton.setVisible(true);
					invMenu.invalidate();
				}else if (pItem == null){
					wornButton = blankButton;
					wornButton.setVisible(false);
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
				}
				wornButton.invalidate();
				break;
			case 2:
				if (pItem != null && pItem.getButton() == null){
					TextButton button = new TextButton(pItem.getName(), uiskin);
<<<<<<< HEAD
					button.addAction(new equipAction(armorButton, pItem));
					playerEquip.get(i).setButton(button);
				}
				if (pItem != null){
					armorButton = pItem.getButton();
					armorButton.setVisible(true);
				}else{
					//armorButton.setVisible(false);
=======
					button.addAction(new equipAction(button, pItem));
					playerEquip.get(i).setButton(button);
				}
				if (pItem != null && armorButton != pItem.getButton()){
					equipment.addActorAt(0, pItem.getButton());
					equipment.removeActor(armorButton);
					armorButton = pItem.getButton();
					armorButton.setVisible(true);
					invMenu.invalidate();
				}else if (pItem == null){
					armorButton = blankButton;
					armorButton.setVisible(false);
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
				}
				armorButton.invalidate();
				break;
			case 3:
				if (pItem != null && pItem.getButton() == null){
					TextButton button = new TextButton(pItem.getName(), uiskin);
<<<<<<< HEAD
					button.addAction(new equipAction(backButton, pItem));
					playerEquip.get(i).setButton(button);
				}
				if (pItem != null){
					backButton = pItem.getButton();
					backButton.setVisible(true);
				}else{
					//backButton.setVisible(false);
=======
					button.addAction(new equipAction(button, pItem));
					playerEquip.get(i).setButton(button);
				}
				if (pItem != null && backButton != pItem.getButton()){
					equipment.addActorAt(0, pItem.getButton());
					equipment.removeActor(backButton);
					backButton = pItem.getButton();
					backButton.setVisible(true);
					invMenu.invalidate();
				}else if (pItem == null){
					backButton = blankButton;
					backButton.setVisible(false);
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
				}
				backButton.invalidate();
				break;
			}
		}
		
//		Gdx.app.log("UI.updatePlayerUi", "heldButton = " + heldButton.getText() + " visible = " + heldButton.isVisible());
//		Gdx.app.log("UI.updatePlayerUi", "armor = " + armorButton.isVisible());
		
		while(playerInv.size > playerItems.size){
			playerItems.add(blankButton);
		}
		
		if (playerInv.size > 0){
			for (int i = 0; i < playerInv.size; i++){
<<<<<<< HEAD
				if (playerInv.get(i) != null && playerInv.get(i).getButton() == null){
					TextButton button = new TextButton(playerInv.get(i).getName(), uiskin);
					button.addAction(new equipAction(playerItems.get(i), playerInv.get(i)));
					playerInv.get(i).setButton(button);
				}
				if(playerInv.get(i) != null && playerInv.get(i).getButton().getActions() != playerItems.get(i).getActions()){
					playerItems.insert(i, playerInv.get(i).getButton());
=======
				Item item = playerInv.get(i);
				if (item != null && item.getButton() == null){
					TextButton button = new TextButton(item.getName(), uiskin);
					button.addAction(new equipAction(button, playerInv.get(i)));
					item.setButton(button);
				}
				if(item != null && item.getButton() != playerItems.get(i)){
					playerItems.insert(i, item.getButton());
				}
				if(item == null){
					Gdx.app.log("Error:Ui", "playerInv has null item");
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
				}
			}
		}
		if (GameMain.isHalfSec(false)){
			playerItems.truncate(playerInv.size);
			inventory.clear();
			for (int i = 0; i < playerItems.size; i++){
<<<<<<< HEAD
=======
				playerItems.get(i).setVisible(true);
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
				inventory.addActor(playerItems.get(i));
			}
			inventory.invalidate();
		}

		//invMenu.invalidateHierarchy();
		
	}
	
	public void dispose(){
		//uistage.dispose();
	}
	
	private class returnAction extends Action{
		Button button;
		public returnAction(Button actor){
			this.button = actor;
		}
		@Override
		public boolean act(float delta) {
			if(button.isChecked()){
				GM.endGame();
				return true;
			}else{
				return false;
			}
		}
	}
	
	private class charAction extends Action{

		int delay = 15;
		int delayAmt = 25;
		
		boolean isOpen = false;
		Button button;
		public charAction(Button button){
			this.button = button;
		}
		@Override
		public boolean act(float delta) {
			if (delay > 0)
				delay--;
			
			if(isOpen && button.isPressed() && delay == 0){
				invMenu.setVisible(false);
				isOpen = false;
				delay = delayAmt;
				return false;
			}else if(!isOpen && button.isPressed() && delay == 0){
				invMenu.setVisible(true);
<<<<<<< HEAD
				invMenu.invalidate();
				invMenu.setTouchable(Touchable.enabled);
=======
				invMenu.setTouchable(Touchable.enabled);
				invMenu.invalidate();
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
				isOpen = true;
				delay = delayAmt;
				return false;
			}else{
				return false;
			}
		}	
	}
	
	private class equipAction extends Action{

		Button button;
		Item item;
<<<<<<< HEAD
=======
		
		int delay = 0;
		int delayAmt = 30;
		
		boolean equipped = false;
		
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
		public equipAction(Button button, Item item){
			this.button = button;
			this.item = item;
		}
		@Override
		public boolean act(float delta) {
<<<<<<< HEAD
			if (/*item.getEquipSlot() >= 0 &&*/ button.isPressed()){
				player.unequipItem(item.getEquipSlot());
				player.equipItem(item, item.getEquipSlot());
				Gdx.app.log("Ui line 217", "Item equipped");
				return false;
			}
			else{
				return false;
			}
=======
			if (delay > 0)
				delay--;
			
			if (item.getEquipSlot() >= 0 && button.isPressed() && !equipped && delay == 0){
				player.equipItem(item, item.getEquipSlot());
				equipped = true;
				delay = delayAmt;
			}else if(item.getEquipSlot() >= 0 && button.isPressed() && equipped && delay == 0){
				player.unequipItem(item.getEquipSlot());
				equipped = false;
				delay = delayAmt;
			}
			return false;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
		}
		
	}
	
}
