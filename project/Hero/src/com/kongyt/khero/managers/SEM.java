package com.kongyt.khero.managers;



//��Ч������Sound Effect Manager
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

	// �ͷŵ�������
	public static void relInstance() {
		if (s_instance != null) {
			s_instance.destroy();
			s_instance = null;
		}
	}

	// ��ʼ������
	private void init() {

	}

	// ���ٺ���
	private void destroy() {

	}

	// ========================================��Ϸ���=====================================================
	
	//�������ֲ�����Ч
	public long playSound(String soundEffectName, float volume){
		return playSound(soundEffectName, volume, false);
	}
	
	
	
	public long playSound(String soundEffectName, float volume, boolean isLoop){
		return 0;
	}
	
	
	
	//ֹͣ������Ч
	public void stopSound(long soundId){
		
	}
	
	//��������
	public long playMusic(String musicName, float volume, boolean isLoop){
		return 0;
	}
	
	
	//ֹͣ��������
	public void stopMusic(long musicId){
		
	}
	
	// ==================================================================================================

}
