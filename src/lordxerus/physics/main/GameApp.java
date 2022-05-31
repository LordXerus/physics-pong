package lordxerus.physics.main;

import processing.core.PApplet;

public class GameApp extends PApplet{

	public void settings(){
		size(1000, 1000);
	}

	public void setup() {

	}

	float lastMillis = 0;
	final float spring_constant = 1000;
	public void draw(){

	}

	public void mousePressed(){

	}
	
	// Virtual key system
	
	public static void main(String[] args){
		String[] processingArgs = {"GameApp"};
		GameApp mySketch = new GameApp();
		PApplet.runSketch(processingArgs, mySketch);
	}
}