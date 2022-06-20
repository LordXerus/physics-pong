package lordxerus.simple;

import lordxerus.simple.rendering.*;
import lordxerus.simple.rendering.renderers.AbstractRenderer;
import lordxerus.simple.rendering.renderers.CircleRenderer;
import lordxerus.simple.rendering.renderers.RectRenderer;
import lordxerus.simple.scenes.AbstractScene;
import lordxerus.simple.scenes.PongScene;
import processing.core.PApplet;

public class GameApp extends PApplet{

	public void settings(){
		size(800, 500);
	}
	Camera camera;
	RenderWorld<AbstractRenderer> renderWorld = new RenderWorld<>();

	CircleRenderer circle;

	public void setup() {
		rectMode(RADIUS);
		camera = new Camera(this, width, height, renderWorld);
		camera.setBG_color(0xff000000);
		RectRenderer box = new RectRenderer();

		renderWorld.addRenderer(box, 0);

		AbstractScene.Scenes.pushScene(new PongScene(this));
		frameRate(144);
	}

	public void draw(){


		background(0xff000000);
		// camera.setPositionOffset(random(-3f, 3f), random(-1f, 1f));
		//circle.setActivated(!circle.getActivated());
		camera.draw();
		image(camera.getGraphics(), 0, 0);
		System.out.println(frameRate);

		for(AbstractScene scene : AbstractScene.Scenes.getScenes()) {
			scene.doTick();
		}

	}

	public void mousePressed(){

	}
	@Override
	public void keyPressed() {
		AbstractScene.Keyboard.raw.notifyPressed(key, keyCode);
	}
	@Override
	public void keyReleased() {
		AbstractScene.Keyboard.raw.notifyReleased(key, keyCode);
	}
	
	// Virtual key system
	
	public static void main(String[] args){
		String[] processingArgs = {"gong"};
		GameApp mySketch = new GameApp();
		PApplet.runSketch(processingArgs, mySketch);
	}
}