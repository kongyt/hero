package com.kongyt.khero.managers;

import android.app.Activity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.kongyt.khero.common.GameSceneData;
import com.kongyt.khero.common.SceneData;
import com.kongyt.khero.utils.SV;
import com.kongyt.khero.views.BaseScene;
import com.kongyt.khero.views.GameScene;
import com.kongyt.khero.views.LoadingScene;
import com.kongyt.khero.views.LoginScene;
import com.kongyt.khero.views.MenuScene;


//��Ϸ������GameManager
public class GM {

	private static GM s_instance;
	
	private GM(){
		
	}
	
	public static GM instance(){
		if( s_instance == null){
			s_instance = new GM();
			s_instance.init();
		}
		return s_instance;
	}
	
	
	//�ͷŵ�������
	public static void relInstance(){
		if( s_instance != null){
			s_instance.destroy();
			s_instance = null;
		}
	}
	
	//��ʼ������
	private void init(){
		
	}
	
	//���ٺ���
	private void destroy(){
		if(g_game != null){
			g_game = null;
		}
	}
	
	//====================================��Ϸ���===========================================
	
	//Activity����
	private Activity g_activity;
	
	//ע��Activity����
	public void registerActivity(Activity activity){
		this.g_activity = activity;
	}
	
	
	//Game����
	private Game g_game;
	
	//ע��Game����
	public void registerGame(Game game){
		this.g_game = game;
	}	
	
	
	private SceneData lastSceneData;		//�����ϴεĳ�����������
	private SceneData currentSceneData;		//���汾�εĳ�����������
	
	//���ݳ���id�л�����
	public void changeScene(SceneData sceneData){
		if( sceneData == null)
			return;
		
		if( this.g_game == null)
			return;
		
		//��������
		BaseScene nextScene = null;
		switch(sceneData.sceneId){
		case SV.SCENE_LOADING:
			nextScene = new LoadingScene();
			break;
		case SV.SCENE_LOGIN:			
			nextScene = new LoginScene();
			break;
		case SV.SCENE_MENU:
			nextScene = new MenuScene();
			break;
		case SV.SCENE_GAME:
			nextScene = new GameScene((GameSceneData)sceneData);
			break;
		}
		

		//�л�����
		if(nextScene != null){
			BaseScene curScreen = (BaseScene) g_game.getScreen();	
			g_game.setScreen(nextScene);
			
			if(curScreen != null){
				curScreen.dispose();
			}
			lastSceneData = currentSceneData;
			currentSceneData = sceneData;
		}
		
	}
	
	//���ص���һ������
	public void backToLastScene(){
		changeScene(lastSceneData);
	}
	
	
	//���ؼ�����,·�ɵ���ǰ������
	public void backKeyDown(){
		if(g_game != null){
			BaseScene baseScene = (BaseScene)g_game.getScreen();
			baseScene.onBack();
		}
		
	}
	
	//====================================================================================
}
