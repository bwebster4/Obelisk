package com.obelisk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class InputHandler {
	
	float WIDTH, HEIGHT;
	
	boolean isWPressed = false;
	boolean isAPressed = false;
	boolean isSPressed = false;
	boolean isDPressed = false;
	boolean isZPressed = false;
	boolean isXPressed = false;
	boolean isEPressed = false, isEReleased = false, isEHeld = false;
	boolean isQPressed = false, isQReleased = false, isQHeld = false;
	boolean isEscPressed = false, isEscReleased = false, isEscHeld = false;;
	boolean is1Pressed = false;
	boolean is2Pressed = false;
	boolean is3Pressed = false;
	boolean is4Pressed = false;
	boolean isLeftButtonPressed = false, isLeftButtonHeld = false, isLeftButtonReleased = true;
	boolean isRightButtonPressed = false, isRightButtonHeld = false, isRightButtonReleased = true;
	int mousex, mousey;
	Vector3 mousepos;
	public InputHandler(float WIDTH, float HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		mousepos = new Vector3();
	}
	
	public void resize(float width, float height){
		this.WIDTH = width;
		this.HEIGHT = height;
	}
	
	public void update(){
		isWPressed = Gdx.input.isKeyPressed(Keys.W);
		isAPressed = Gdx.input.isKeyPressed(Keys.A);
		isSPressed = Gdx.input.isKeyPressed(Keys.S);
		isDPressed = Gdx.input.isKeyPressed(Keys.D);
		isZPressed = Gdx.input.isKeyPressed(Keys.Z);
		isXPressed = Gdx.input.isKeyPressed(Keys.X);
		isEPressed = Gdx.input.isKeyPressed(Keys.E);
		pressrelease("E");
		isQPressed = Gdx.input.isKeyPressed(Keys.Q);
		pressrelease("Q");
		isEscPressed = Gdx.input.isKeyPressed(Keys.ESCAPE);
		pressrelease("Esc");
		is1Pressed = Gdx.input.isKeyPressed(Keys.NUM_1);
		is2Pressed = Gdx.input.isKeyPressed(Keys.NUM_2);
		is3Pressed = Gdx.input.isKeyPressed(Keys.NUM_3);
		is4Pressed = Gdx.input.isKeyPressed(Keys.NUM_4);
		
		isLeftButtonPressed = Gdx.input.isButtonPressed(Buttons.LEFT);
		pressrelease("LMB");
		isRightButtonPressed = Gdx.input.isButtonPressed(Buttons.RIGHT);
		pressrelease("RMB");
		
		mousepos.set(Gdx.input.getX(), HEIGHT-Gdx.input.getY(), 0);
	}
	void pressrelease(String type){
		if (type == "E"){
			if (isEPressed && isEReleased){
				isEReleased = false;
				isEHeld = true;
			}else if (isEPressed && !isEReleased){
				isEHeld = true;
				isEPressed = false;
			}else{
				isEHeld = false;
				isEReleased = true;
			}
		}else if (type == "Q"){
			if (isQPressed && isQReleased){
				isQReleased = false;
				isQHeld = true;
			}else if (isQPressed && !isQReleased){
				isQHeld = true;
				isQPressed = false;
			}else{
				isQHeld = false;
				isQReleased = true;
			}
		}else if (type == "Esc"){
			if (isEscPressed && isEscReleased){
				isEscReleased = false;
				isEscHeld = true;
			}else if (isEscPressed && !isEscReleased){
				isEscHeld = true;
				isEscPressed = false;
			}else{
				isEscHeld = false;
				isEscReleased = true;
			}
		}else if (type == "LMB"){
			if (isLeftButtonPressed && isLeftButtonReleased){
				isLeftButtonReleased = false;
				isLeftButtonHeld = true;
			}else if (isLeftButtonPressed && !isLeftButtonReleased){
				isLeftButtonHeld = true;
				isLeftButtonPressed = false;
			}else{
				isLeftButtonHeld = false;
				isLeftButtonReleased = true;
			}
		}
		else if (type == "RMB"){
			if (isRightButtonPressed && isRightButtonReleased){
				isRightButtonReleased = false;
				isRightButtonHeld = true;
			}else if (isRightButtonPressed && !isRightButtonReleased){
				isRightButtonHeld = true;
				isRightButtonPressed = false;
			}else{
				isRightButtonHeld = false;
				isRightButtonReleased = true;
			}
		}
		

	}
	public boolean isWPressed(){
		return isWPressed;
	}
	public boolean isAPressed(){
		return isAPressed;
	}
	public boolean isSPressed(){
		return isSPressed;
	}
	public boolean isDPressed(){
		return isDPressed;
	}
	public boolean isZPressed(){
		return isZPressed;
	}
	public boolean isXPressed(){
		return isXPressed;
	}
	public boolean isEPressed(){
		return isEPressed;
	}
	public boolean isEHeld(){
		return isEHeld;
	}
	public boolean isQPressed(){
		return isQPressed;
	}
	public boolean isEscPressed(){
		return isEscPressed;
	}
	public boolean is1Pressed(){
		return is1Pressed;
	}
	public boolean is2Pressed(){
		return is2Pressed;
	}
	public boolean is3Pressed(){
		return is3Pressed;
	}
	public boolean is4Pressed(){
		return is4Pressed;
	}
	public boolean isLeftButtonPressed(){
		return isLeftButtonPressed;
	}
	public boolean isLeftButtonHeld(){
		return isLeftButtonHeld;
	}
	public boolean isRightButtonPressed(){
		return isRightButtonPressed;
	}
	public boolean isRightButtonHeld(){
		return isRightButtonHeld;
	}
	public Vector3 getMousePos(){
		return mousepos;
	}
}
