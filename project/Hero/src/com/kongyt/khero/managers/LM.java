package com.kongyt.khero.managers;

import com.badlogic.gdx.Gdx;
import com.kongyt.khero.utils.SV;


//日志管理类LogManager
public class LM {
	private static LM s_instance;
	
	private LM(){
		
	}
	
	public static LM instance(){
		if( s_instance == null){
			s_instance = new LM();
			s_instance.init();
		}
		
		return s_instance;
	}
	
	public static void relInstance(){
		if( s_instance != null){
			s_instance.destroy();
			s_instance = null;
		}
	}
	
	public void init(){
		
	}
	
	public void destroy(){
		
	}
	
	
	//==============================游戏相关=============================
	
	public void logE(String message){
		//Gdx.app.error(SV.GameName, message);
		System.out.println(message);
	}
	
	public void logI(String message){
		//Gdx.app.log(SV.GameName, message);
		System.out.println(message);
	}
	
	public void logD(String message){
		//Gdx.app.debug(SV.GameName, message);
		System.out.println(message);
	}


	public void setLogLevel(int logLevel){
		//Gdx.app.setLogLevel(logLevel);
	}
	
}
