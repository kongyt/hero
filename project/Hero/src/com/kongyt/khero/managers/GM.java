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


//游戏管理类GameManager
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
	
	
	//释放单例对象
	public static void relInstance(){
		if( s_instance != null){
			s_instance.destroy();
			s_instance = null;
		}
	}
	
	//初始化函数
	private void init(){
		
	}
	
	//销毁函数
	private void destroy(){
		if(g_game != null){
			g_game = null;
		}
	}
	
	//====================================游戏相关===========================================
	
	//Activity引用
	private Activity g_activity;
	
	//注册Activity引用
	public void registerActivity(Activity activity){
		this.g_activity = activity;
	}
	
	
	//Game引用
	private Game g_game;
	
	//注册Game引用
	public void registerGame(Game game){
		this.g_game = game;
	}	
	
	
	private SceneData lastSceneData;		//保存上次的场景构建参数
	private SceneData currentSceneData;		//保存本次的场景构建参数
	
	//根据场景id切换场景
	public void changeScene(SceneData sceneData){
		if( sceneData == null)
			return;
		
		if( this.g_game == null)
			return;
		
		//构建场景
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
		

		//切换场景
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
	
	//返回到上一个场景
	public void backToLastScene(){
		changeScene(lastSceneData);
	}
	
	
	//返回键按下,路由到当前场景中
	public void backKeyDown(){
		if(g_game != null){
			BaseScene baseScene = (BaseScene)g_game.getScreen();
			baseScene.onBack();
		}
		
	}
	
	//====================================================================================
}
