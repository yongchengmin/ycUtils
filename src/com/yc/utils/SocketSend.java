package com.yc.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketSend {

	public static void send(String ip,int port,String mesg,String charsetName) 
			throws UnknownHostException, IOException{
		Socket socket = new Socket(ip,port);
		OutputStream outputStream = socket.getOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,charsetName);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		bufferedWriter.write(mesg);
		bufferedWriter.flush();
		bufferedWriter.close();
		socket.close();
	}

}
