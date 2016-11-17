package com.kongyt.khero.utils;

import com.badlogic.gdx.Application;

//StaticValue�࣬����һЩ��ֵ
public class SV {
	
	//�ֱ�����Ϣ
	public static final float WINDOW_WIDTH = 800;			//���ڿ��
	public static final float WINDOW_HEIGHT = 480;			//���ڸ߶�
	public static final boolean WINDOW_SCALE_MODE = false;	//����ģʽ
	
	
	//����ID
	public static final int SCENE_LOADING = 0;				//��Ϸ���س���
	public static final int SCENE_LOGIN = 1;				//��Ϸ��½����
	public static final int SCENE_MENU = 2;					//��Ϸ���˵�
	public static final int SCENE_GAME = 3;					//��Ϸ����
	
	
	//��Ϸ��Ϣ
	public static final String GameName = "KHero";			//��Ϸ����
	
	
	//DEBUG��Ϣ
	public static boolean DEBUG = true;						//�Ƿ���DEBUGģʽ
	
	//��־����ȼ�
	public static final int LOG_NONE = Application.LOG_NONE;
	public static final int LOG_DEBUG = Application.LOG_DEBUG;
	public static final int LOG_INFO = Application.LOG_INFO;
	public static final int LOG_ERROR = Application.LOG_ERROR;
	
	
	//���õ�BaseActor��TAG��int����
	public static final int TAG_NONE = 0;
	public static final int TAG_HERO = 1;
	
	
	//�������
	public static final float PHYSICS_TIME_SPAN = 1/50f;	//����֡ʱ��
	public static final int VEL_ITRT = 6;					//�ٶȼ����������
	public static final int POS_ITRT = 4;					//λ�ü����������
	public static final float SPEED_OF_DECAY = 100; 		// ��λ������/ÿƽ����
	
	
	//��Ϣ�궨��
	public static final int RECV_SERVER_DATA = 1;			//�յ����������ݰ�

}
