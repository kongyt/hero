package com.kongyt.khero.views;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kongyt.khero.common.SceneData;
import com.kongyt.khero.managers.GM;
import com.kongyt.khero.managers.RM;
import com.kongyt.khero.utils.SV;

public class LoadingScene extends BaseScene{
	
	private Image loading_bg;

	
	public LoadingScene(){
		
		TextureAtlas atlas = RM.instance().getTexAtls("loading/loading.atlas");
		Sprite tmp1 = atlas.createSprite("loading_bg");
		loading_bg = new Image(tmp1);
		uiStage.addActor(loading_bg);
		loading_bg.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				SceneData sceneData = new SceneData();
				sceneData.sceneId = SV.SCENE_LOGIN;
				
				GM.instance().changeScene(sceneData);
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		
		System.out.println("LoadingΩÁ√Ê");
	}
}
