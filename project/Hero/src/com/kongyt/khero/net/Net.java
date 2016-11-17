package com.kongyt.khero.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.kongyt.khero.managers.LM;
import com.kongyt.khero.utils.SV;

public class Net{
	
	private static Net s_instance = null;
	public static Net instance(){
		if( s_instance == null){
			s_instance = new Net();
		}
		return s_instance;
	}
	
	private Net(){
		
	}
	
	
    private boolean isConnected = false;
    private Socket socket = null;
	private OutputStream outputStream;
	private InputStream inputStream;
	private boolean isRunning = true;
	
	public void destroy(){
		if(isRunning){
			stop();
			isConnected = false;
		}
		
		if(inputStream != null){
			try {
				inputStream.close();
				inputStream = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(outputStream != null){
			try {
				outputStream.close();
				outputStream = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			socket = null;
		}
	}
	
	public void stop(){
		isRunning = false;
	}
	
	public void conn(final String ip, final int port){
		new Thread(){
			
			public void handleMsg(int msgId, int msgLen, byte[] msgData){
				Bundle bundle = new Bundle();
				Message message = new Message();
				message.what = SV.RECV_SERVER_DATA;
				bundle.putInt("msgId", msgId);
				bundle.putInt("msgLen", msgLen);
				bundle.putByteArray("msgData", msgData);
				message.setData(bundle);
				
				msgHandler.handleMessage(message);
			}
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();				
				
				byte[] recvBuffer = new byte[1024 * 16];
				int recvDataLen = 0;
				try {
					System.out.println("连接服务器。。。");
					socket = new Socket(ip, port);
					outputStream = socket.getOutputStream();
					inputStream = socket.getInputStream();
					
					isConnected = true;
					
					System.out.println("成功连接服务器");
					
					byte [] recv = new byte[1024];
					
					while(isRunning){						
						int len = inputStream.read(recv);
						if(len > 0){							
							if((recvBuffer.length - recvDataLen) < len) 
								throw new RuntimeException("消息缓冲区不够大，消息包装不下");
							System.arraycopy(recv, 0, recvBuffer, recvDataLen, len);
						
							recvDataLen += len;
							if( recvDataLen >= 8){	
								int dataLen = 0;
								int msgId = 0;
								
								byte[] msgIdByte = new byte[4];
								byte[] dataLenByte = new byte[4];
								System.arraycopy(recvBuffer, 0, msgIdByte, 0, 4);
								msgId = byteArray2Int(msgIdByte);
								System.arraycopy(recvBuffer, 4, dataLenByte, 0, 4);								
								dataLen = byteArray2Int(dataLenByte);								
								if(dataLen <= recvDataLen){
									byte[] packetData = new byte[dataLen-8];
									System.arraycopy(recvBuffer, 8, packetData, 0, dataLen-8);
									if(recvDataLen > dataLen){
										System.arraycopy(recvBuffer, dataLen, recvBuffer, 0, recvDataLen-dataLen);
									}
									recvDataLen -= dataLen;									
									
									// 通过handler发送消息
									handleMsg(msgId, dataLen, packetData);
								}
							}
						}
					}
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}.start();
		
	}
	
	// 发送数据包
	public void sendMsg(MsgPacket msgPacket){
		try {
			outputStream.write(int2ByteArray(msgPacket.msgId));			
			outputStream.write(int2ByteArray(msgPacket.msgLen));
			outputStream.write(msgPacket.msgData);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void distributeMsg(MsgPacket msgPacket){
		int moduleId = msgPacket.msgId & 0xFFFF0000;
		if(modules.containsKey(moduleId)){
			modules.get(moduleId).onReceiveMsg(msgPacket);
		}else{
			LM.instance().logD("消息模块没有注册");
		}
	}
	
	public void registerModule(BaseMsgModule module){
		System.out.printf("注册消息模块,模块moduleId=0x%08x\n", module.moduleId);
		this.modules.put(module.moduleId, module);
	}
	
	private Map<Integer, BaseMsgModule> modules = new HashMap<Integer, BaseMsgModule>();
	
	public Handler msgHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == SV.RECV_SERVER_DATA) {  
                Bundle bundle = msg.getData();  
                MsgPacket msgPacket = new MsgPacket();
                msgPacket.msgId = bundle.getInt("msgId");
                msgPacket.msgLen = bundle.getInt("msgLen");
                msgPacket.msgData = bundle.getByteArray("msgData");
                distributeMsg(msgPacket);
            }  
        }   
    };  
  
	
	public static int byteArray2Int(byte[] bts){
		int rs = 0;
		if(bts.length >= 4){
			int n1 = bts[0];
			int n2 = bts[1];
			int n3 = bts[2];
			int n4 = bts[3];
			n1 = n1 << 24;
			n2 = 0x00FF0000 & n2 << 16;
			n3 = 0x0000FF00 & n3 << 8;
			n4 = 0x000000FF & n4;
			rs = n1|n2|n3|n4;
		}		
		return rs;		
	}
	
	public static byte[] int2ByteArray(int n){
		byte[] rs = new byte[4];
		rs[0] = (byte)(n >> 24);
		rs[1] = (byte)((n >> 16)&0xFF);
		rs[2] = (byte)((n >> 8)&0xFF);
		rs[3] = (byte)(n&0xFF);		
		return rs;
		
	}

}
