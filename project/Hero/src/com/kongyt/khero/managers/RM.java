package com.kongyt.khero.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


//��Դ������ResourceManager
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
	
	
	//�ͷŵ�������
	public static void relInstance(){
		if( s_instance != null){
			s_instance.destroy();
			s_instance = null;
		}
	}
	
	//��ʼ������
	private void init(){
		this.LoadTexAtlas(preLoadTexAtlsNames);
	}
	
	//���ٺ���
	private void destroy(){
		this.unLoadTexAtlas(preLoadTexAtlsNames);		
	}

	//========================================��Ϸ���=====================================================
	
	private static final String PREFIX_OF_TEX_ATLS = "images/";
	
	//Ԥ������������
	public static final String[] preLoadTexAtlsNames = {
		
	};
	
	//�����ַ��������������
	public void LoadTexAtlas(String [] texAtlsNames){
		for(int i = 0; i < texAtlsNames.length; i++){
			this.load(PREFIX_OF_TEX_ATLS + texAtlsNames[i], TextureAtlas.class);
		}
	}
	
	
	//�����ַ�������ж������
	public void unLoadTexAtlas(String [] texAtlsNames){
		for(int i = 0; i < texAtlsNames.length; i++){
			this.unload(PREFIX_OF_TEX_ATLS + texAtlsNames[i]);
		}
	}
	
	
	//��Դ�Ƿ�������
	public boolean isPreLoadOk(){
		return this.update();
	}
	
	//��ȡ����
	public TextureAtlas getTexAtls(String texAtlsName){		
		if(!this.isLoaded(PREFIX_OF_TEX_ATLS + texAtlsName, TextureAtlas.class)){
			this.load(PREFIX_OF_TEX_ATLS + texAtlsName, TextureAtlas.class);
			this.finishLoading();
		}
		return this.get(PREFIX_OF_TEX_ATLS + texAtlsName, TextureAtlas.class);
	}
	
	
	//==================================================================================================
	
	
}
