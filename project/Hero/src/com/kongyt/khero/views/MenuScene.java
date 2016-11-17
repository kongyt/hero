package com.kongyt.khero.views;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kongyt.khero.common.GameSceneData;
import com.kongyt.khero.managers.GM;
import com.kongyt.khero.managers.RM;
import com.kongyt.khero.utils.SV;

public class MenuScene extends BaseScene {

	private Image menuBg;
	private Image level1;
	private Image level2;

	
	public MenuScene(){
		TextureAtlas atlas = RM.instance().getTexAtls("menu/menu.atlas");
		Sprite tmp1;
		tmp1 = atlas.createSprite("menu_bg");
		menuBg = new Image(tmp1);
		uiStage.addActor(menuBg);
	
		tmp1 = atlas.createSprite("level1");
		level1 = new Image(tmp1);		
		level1.setPosition(60, 140);
		uiStage.addActor(level1);
		level1.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				GameSceneData sceneData = new GameSceneData();
				sceneData.sceneId = SV.SCENE_GAME;
				sceneData.levelId = 1;
				GM.instance().changeScene(sceneData);
				System.out.println("click1");
				return super.touchDown(event, x, y, pointer, button);
			}

			
		});
		
		
		tmp1 = atlas.createSprite("level2");
		level2 = new Image(tmp1);
		level2.setPosition(440, 140);
		uiStage.addActor(level2);		
		level2.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				GameSceneData sceneData = new GameSceneData();
				sceneData.sceneId = SV.SCENE_GAME;
				sceneData.levelId = 2;
				GM.instance().changeScene(sceneData);
				System.out.println("click2");
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		
	}	
	
	
	
	
}
