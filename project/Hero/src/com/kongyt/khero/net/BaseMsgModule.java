package com.kongyt.khero.net;

public abstract class BaseMsgModule {
	public int moduleId = 0;
	public void onReceiveMsg(MsgPacket msgPacket){
		System.out.printf("收到数据包,消息id=0x%08x", msgPacket.msgId);
		System.out.println(" 消息长度len=" + msgPacket.msgLen + "（包含消息头8个字节）");
	}
}
