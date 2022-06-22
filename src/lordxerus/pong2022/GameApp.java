package lordxerus.pong2022;

import lordxerus.pong2022.rendering.*;
import lordxerus.pong2022.rendering.renderers.AbstractRenderer;
import lordxerus.pong2022.rendering.renderers.CircleRenderer;
import lordxerus.pong2022.rendering.renderers.RectRenderer;
import lordxerus.pong2022.scenes.AbstractScene;
import lordxerus.pong2022.scenes.pongScene.PongScene;
import org.dyn4j.dynamics.Settings;
import processing.core.PApplet;

public class GameApp extends PApplet{
	public void settings(){
		size(1000, 700);
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
		frameRate(60);

		System.out.println(this.dataPath("wall.mp3"));
	}

	public void draw(){


		background(0xff000000);
		// camera.setPositionOffset(random(-3f, 3f), random(-1f, 1f));
		//circle.setActivated(!circle.getActivated());
		camera.draw();
		image(camera.getGraphics(), 0, 0);
		//System.out.println(frameRate);

		for(AbstractScene scene : AbstractScene.Scenes.getScenes()) {
			scene.doTick();
			textSize(20);
			//System.out.println(dataPath("wall.mp3"));
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