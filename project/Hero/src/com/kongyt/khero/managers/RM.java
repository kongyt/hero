package com.kongyt.khero.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


//资源管理类ResourceManager
public class RM extends AssetManager{
	
private static RM s_instance;
	
	private RM(){
		
	}
	
	public static RM instance(){
		if( s_instance == null){
			s_instance = new RM();
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
		this.LoadTexAtlas(preLoadTexAtlsNames);
	}
	
	//销毁函数
	private void destroy(){
		this.unLoadTexAtlas(preLoadTexAtlsNames);		
	}

	//========================================游戏相关=====================================================
	
	private static final String PREFIX_OF_TEX_ATLS = "images/";
	
	//预加载纹理集名字
	public static final String[] preLoadTexAtlsNames = {
		
	};
	
	//根据字符串数组加载纹理集
	public void LoadTexAtlas(String [] texAtlsNames){
		for(int i = 0; i < texAtlsNames.length; i++){
			this.load(PREFIX_OF_TEX_ATLS + texAtlsNames[i], TextureAtlas.class);
		}
	}
	
	
	//根据字符串数组卸载纹理集
	public void unLoadTexAtlas(String [] texAtlsNames){
		for(int i = 0; i < texAtlsNames.length; i++){
			this.unload(PREFIX_OF_TEX_ATLS + texAtlsNames[i]);
		}
	}
	
	
	//资源是否加载完毕
	public boolean isPreLoadOk(){
		return this.update();
	}
	
	//获取纹理集
	public TextureAtlas getTexAtls(String texAtlsName){		
		if(!this.isLoaded(PREFIX_OF_TEX_ATLS + texAtlsName, TextureAtlas.class)){
			this.load(PREFIX_OF_TEX_ATLS + texAtlsName, TextureAtlas.class);
			this.finishLoading();
		}
		return this.get(PREFIX_OF_TEX_ATLS + texAtlsName, TextureAtlas.class);
	}
	
	
	//==================================================================================================
	
	
}
