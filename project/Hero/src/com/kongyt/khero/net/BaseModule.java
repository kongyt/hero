package com.kongyt.khero.net;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kongyt.khero.messages.Message.*;


public class BaseModule extends BaseMsgModule {

	public BaseModule(){
		this.moduleId = 0x00010000;
	}

	
	// �ַ�ģ����Ϣ���������Ϣ��Ӧ����
	@Override
	public void onReceiveMsg(MsgPacket msgPacket) {
		// TODO Auto-generated method stub
		super.onReceiveMsg(msgPacket);	
		switch(msgPacket.msgId){
		case Msg.Register_Request_VALUE:
			onRegisterRespnse(msgPacket);
			break;
		case Msg.Login_Response_VALUE:
			onLoginRespnse(msgPacket);
			break;
		case Msg.Welcom_Notification_VALUE:
			onWelcomeNitification(msgPacket);
			break;
			
		}
	}
	
	
	// ����ע������
	public void sendRegisterRequest(String username, String password) {
		System.out.println("���͵�½��Ϣ���û���:" + username + "   ����:" + password);
		Request req = Request
				.newBuilder()
				.setRegister(
						RegisterRequest.newBuilder().setUsername(username)
								.setPassword(password).build()).build();

		byte[] reqBytePacket = req.toByteArray();

		MsgPacket msgPacket = new MsgPacket();
		msgPacket.msgId = Msg.Register_Request_VALUE;
		msgPacket.msgLen = reqBytePacket.length + 8;
		msgPacket.msgData = reqBytePacket;

		Net.instance().sendMsg(msgPacket);

	}

	// ��Ӧע��ظ���Ϣ
	public void onRegisterRespnse(MsgPacket msgPacket) {
		System.out.println("onRegisterResponse()");

		try {
			Response res = Response.parseFrom(msgPacket.msgData);
			if (res.getResult() == true) {
				System.out.println("ע��ɹ�");
			} else {
				System.out.println("ע��ʧ��");
				System.out.println("������Ϣ:" + res.getErrorDescribe());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	// ���͵�½����
	public void sendLoginRequest(String username, String password){
		System.out.println("���͵�½��Ϣ���û���:" + username + "   ����:" + password);
		Request req = Request
				.newBuilder()
				.setLogin(
						LoginRequest
						.newBuilder()
						.setUsername(username)
						.setPassword(password)
						.build())
				.build();
		
		byte[] reqBytePacket = req.toByteArray();
		
		MsgPacket msgPacket = new MsgPacket();
		msgPacket.msgId = Msg.Login_Request_VALUE;
		msgPacket.msgLen = reqBytePacket.length + 8;
		msgPacket.msgData = reqBytePacket;
		
		Net.instance().sendMsg(msgPacket);
		
	}
	
	// ��Ӧ��¼�ظ���Ϣ
	public void onLoginRespnse(MsgPacket msgPacket){
		System.out.println("onLoginResponse()");
		
		try {
			Response res = Response.parseFrom(msgPacket.msgData);
			System.out.println("=====================");
			if( res.getResult() == true){
				
				int token = res.getLogin().getToken();
				System.out.println("��½�ɹ�");
				System.out.println("Token=" + token);
			}else{
				System.out.println("��½ʧ��");
				System.out.println("������Ϣ:"+ res.getErrorDescribe());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Debug ����
	public void sendDebugCommand(String cmd){
		System.out.println("���͵�������:" + cmd);
		Command command = Command
				.newBuilder()
				.setDebug(DebugCommand
						.newBuilder()
						.setCommand(cmd)
						.build())
				.build();
		
		byte[] cmdBytePacket = command.toByteArray();
		
		MsgPacket msgPacket = new MsgPacket();
		msgPacket.msgId = Msg.Debug_Command_VALUE;
		msgPacket.msgLen = cmdBytePacket.length + 8;
		msgPacket.msgData = cmdBytePacket;
		
		Net.instance().sendMsg(msgPacket);
		
	}
	
	public void onWelcomeNitification(MsgPacket msgPacket){
		
	}

}
