package com.kongyt.khero.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kongyt.khero.managers.TM;
import com.kongyt.khero.utils.SV;

public abstract class BaseScene implements Screen {
	
	private SpriteBatch batch;
	protected Stage gameStage;
	protected Stage uiStage;
	public PerspectiveCamera cam;
	
	protected boolean isPause;
	
	protected boolean DEBUG = SV.DEBUG;
	protected int numDrawCalls = 0;
	protected BitmapFont font;
	protected Group paintGroup;
	protected Group debugGroup;
	
	
	public BaseScene(){
		
		batch = new SpriteBatch();
		
		
		gameStage = new Stage(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT, SV.WINDOW_SCALE_MODE, batch);
		
		//设置透视相机
		cam = new PerspectiveCamera(60.0f, SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT);		
		cam.far = 1000;
		cam.position.x = 400;
		cam.position.y = 240;
		cam.position.z = (float) (240 / Math.tan(30.0f / 180.0f * Math.PI ));
		gameStage.setCamera(cam);
		
		
		isPause = false;
		
		uiStage = new Stage(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT, SV.WINDOW_SCALE_MODE, batch);
		
		
		paintGroup = new Group();
		paintGroup.setSize(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT);
		gameStage.addActor(paintGroup);
		
		//调试信息
		if(DEBUG){
			font = new BitmapFont();			
			
			Group debugGroup = new Group(){

				@Override
				public void draw(SpriteBatch batch, float parentAlpha) {
					// TODO Auto-generated method stub
					super.draw(batch, parentAlpha);
					numDrawCalls = batch.renderCalls;
				}
				
			};
			uiStage.addActor(debugGroup);
		}
		
	}

	
	private float delayTime = 0;
	
	//除非必要，否则不用重写此方法
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(isPause)
			return;		
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		delayTime += delta;
		while(delayTime >= SV.PHYSICS_TIME_SPAN){
			delayTime -= SV.PHYSICS_TIME_SPAN;
			TM.instance().step(delta);	
			gameStage.act(delta);
			uiStage.act(delta);
		}
		
		gameStage.draw();
		uiStage.draw();
		//调试信息
		if(DEBUG){
			batch.begin();
			font.draw(batch, "DrawCalls : " + numDrawCalls, 20, 100);
			font.draw(batch, "NativeHeap: " + Gdx.app.getNativeHeap() / 1024f / 1024f + " Mb", 20, 70);
			font.draw(batch, "JavaHeap  : " + Gdx.app.getJavaHeap()/1024f/1024f + " Mb", 20, 40);
			batch.end();
			
			numDrawCalls = 0;
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(uiStage);
		multiplexer.addProcessor(gameStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		this.isPause = true;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		isPause = true;
		
		if(font != null){
			font.dispose();
			font = null;
		}
		
		if(batch != null){
			batch.dispose();
			batch = null;
		}
		
		if(gameStage != null){
			gameStage.dispose();
			gameStage = null;
		}

		if(uiStage != null){
			uiStage.dispose();
			uiStage = null;
		}
		
		
		
	}
	
	//响应返回键消息
	public void onBack(){
		
	}

}
