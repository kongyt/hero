package com.kongyt.khero.net;

public abstract class BaseMsgModule {
	public int moduleId = 0;
	public void onReceiveMsg(MsgPacket msgPacket){
		System.out.printf("�յ����ݰ�,��Ϣid=0x%08x", msgPacket.msgId);
		System.out.println(" ��Ϣ����len=" + msgPacket.msgLen + "��������Ϣͷ8���ֽڣ�");
	}
}
