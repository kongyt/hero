package com.kongyt.khero;

import com.badlogic.gdx.Game;
import com.kongyt.khero.common.SceneData;
import com.kongyt.khero.managers.GM;
import com.kongyt.khero.utils.SV;

public class KHero extends Game {

	public KHero(){
		GM.instance().registerGame(this);
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub	
		
		SceneData sceneData = new SceneData();
		sceneData.sceneId = SV.SCENE_LOADING;
		GM.instance().changeScene(sceneData);
	}

}
