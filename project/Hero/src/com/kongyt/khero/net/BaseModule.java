package com.kongyt.khero.net;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kongyt.khero.messages.Message.*;


public class BaseModule extends BaseMsgModule {

	public BaseModule(){
		this.moduleId = 0x00010000;
	}

	
	// 分发模块消息到具体的消息响应函数
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
	
	
	// 发送注册请求
	public void sendRegisterRequest(String username, String password) {
		System.out.println("发送登陆消息，用户名:" + username + "   密码:" + password);
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

	// 响应注册回复消息
	public void onRegisterRespnse(MsgPacket msgPacket) {
		System.out.println("onRegisterResponse()");

		try {
			Response res = Response.parseFrom(msgPacket.msgData);
			if (res.getResult() == true) {
				System.out.println("注册成功");
			} else {
				System.out.println("注册失败");
				System.out.println("错误信息:" + res.getErrorDescribe());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	// 发送登陆请求
	public void sendLoginRequest(String username, String password){
		System.out.println("发送登陆消息，用户名:" + username + "   密码:" + password);
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
	
	// 响应登录回复消息
	public void onLoginRespnse(MsgPacket msgPacket){
		System.out.println("onLoginResponse()");
		
		try {
			Response res = Response.parseFrom(msgPacket.msgData);
			System.out.println("=====================");
			if( res.getResult() == true){
				
				int token = res.getLogin().getToken();
				System.out.println("登陆成功");
				System.out.println("Token=" + token);
			}else{
				System.out.println("登陆失败");
				System.out.println("错误信息:"+ res.getErrorDescribe());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Debug 命令
	public void sendDebugCommand(String cmd){
		System.out.println("发送调试命令:" + cmd);
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
