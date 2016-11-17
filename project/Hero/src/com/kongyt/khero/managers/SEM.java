package com.kongyt.khero.managers;



//音效管理类Sound Effect Manager
public class SEM {
	
	private static SEM s_instance;

	private SEM() {

	}

	public static SEM instance() {
		if (s_instance == null) {
			s_instance = new SEM();
			s_instance.init();
		}
		return s_instance;
	}

	// 释放单例对象
	public static void relInstance() {
		if (s_instance != null) {
			s_instance.destroy();
			s_instance = null;
		}
	}

	// 初始化函数
	private void init() {

	}

	// 销毁函数
	private void destroy() {

	}

	// ========================================游戏相关=====================================================
	
	//根据名字播放音效
	public long playSound(String soundEffectName, float volume){
		return playSound(soundEffectName, volume, false);
	}
	
	
	
	public long playSound(String soundEffectName, float volume, boolean isLoop){
		return 0;
	}
	
	
	
	//停止播放音效
	public void stopSound(long soundId){
		
	}
	
	//播放音乐
	public long playMusic(String musicName, float volume, boolean isLoop){
		return 0;
	}
	
	
	//停止播放音乐
	public void stopMusic(long musicId){
		
	}
	
	// ==================================================================================================

}
