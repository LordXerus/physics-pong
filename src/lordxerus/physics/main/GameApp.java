package lordxerus.physics.main;

import org.dyn4j.geometry.Vector2;
import processing.core.PApplet;

public class GameApp extends PApplet{

	public void settings(){
		size(1000, 600);
	}

    Camera camera;

	public void setup() {
		camera = new Camera(createGraphics(width, height));
		rectMode(RADIUS);
	}

	float lastMillis = 0;
	final float spring_constant = 1000;
	public void draw(){
		camera.render();
		camera.setPositionOffset(random(-1f, 1f), random(-1f, 1f));

		Vector2 offset = camera.getPosOffset();
		System.out.print(offset.x);
		System.out.print(", ");
		System.out.println(offset.y);

		image(camera.getGraphics(), 0, 0);

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