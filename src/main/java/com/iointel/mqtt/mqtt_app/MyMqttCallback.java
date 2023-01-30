package com.iointel.mqtt.mqtt_app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.DosFileAttributeView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqttCallback implements MqttCallback{

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		System.out.println("Connect Lost" + cause.getMessage());
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		if (message == null) {
			System.out.println("null message received");
			return;
		}
		byte[] payload = message.getPayload();
    	String text = new String(payload, "UTF-8");
    	System.out.println("Subscribed Message: " + text);
    	
    	// Save each message as a separate file in temp/MqttData
    	String dirName = System.getProperty("java.io.tmpdir") + File.separator + "MqttData" + File.separator;
    	
    	File topicDir = new File(dirName + topic);
    	
    	if (!topicDir.exists()) {
    		topicDir.mkdir();
    	}
    	
    	File file = new File(topicDir + File.separator + message.getId() + ".txt");
//
    	file.createNewFile();
//    	file.setWritable(true);
    	
    	OutputStream os = null;
    	try {
    		os = new FileOutputStream(file);
    		os.write(payload, 0, payload.length);
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			os.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	    	    	
//    	Files.write(file.toPath(), "hello".getBytes());
//    	file.setWritable(true, false);
//    	if (Files.isWritable(file.toPath())) {
//    		System.out.println("yes");
//    	}
    	
    	
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token){
//		System.out.println("delivered");
		MqttMessage message;
		try {
			message = token.getMessage();
			byte[] payload = message.getPayload();
	    	String text = new String(payload, "UTF-8");
	    	System.out.println("Published Message: " + text);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
