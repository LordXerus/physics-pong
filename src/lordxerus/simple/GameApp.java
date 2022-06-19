package lordxerus.simple;

import lordxerus.simple.rendering.*;
import lordxerus.simple.rendering.renderers.AbstractRenderer;
import lordxerus.simple.rendering.renderers.CircleRenderer;
import lordxerus.simple.rendering.renderers.RectRenderer;
import processing.core.PApplet;

public class GameApp extends PApplet{

	public void settings(){
		size(500, 300);
	}
	Camera camera;
	RenderWorld<AbstractRenderer> renderWorld = new RenderWorld<>();

	public void setup() {
		rectMode(RADIUS);
		camera = new Camera(this, width, height, renderWorld);
		camera.setBG_color(0xff000000);
		RectRenderer box = new RectRenderer();
		renderWorld.addRenderer(box, 0);

		CircleRenderer circle = new CircleRenderer();
		circle.translate(5, 0);
		circle.setRadius(3);
		circle.setFill(0xffff0000);
		renderWorld.addRenderer(circle, 1);
		frameRate(144);
	}

	public void draw(){


		background(0xff000000);
		camera.setPositionOffset(random(-3f, 3f), random(-1f, 1f));
		camera.draw();
		image(camera.getGraphics(), 0, 0);
		System.out.println(sketchHeight());

	}

	public void mousePressed(){

	}
	
	// Virtual key system
	
	public static void main(String[] args){
		String[] processingArgs = {"gong"};
		GameApp mySketch = new GameApp();
		PApplet.runSketch(processingArgs, mySketch);
	}
}