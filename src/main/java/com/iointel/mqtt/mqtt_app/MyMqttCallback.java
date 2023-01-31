package com.iointel.mqtt.mqtt_app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqttCallback implements MqttCallback{
	
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void connectionLost(Throwable cause) {
		logger.fatal("Connection Lost");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		if (message == null || message.getPayload() == null) {
			logger.info("Null Message Received");
			return;
		}
		byte[] payload = message.getPayload();
    	String text = new String(payload, "UTF-8");
    	logger.info("Subscribed Message: " + text);
    	
    	// Save each message as a separate file in temp/MqttData
    	String dirName = System.getProperty("java.io.tmpdir") + File.separator + "MqttData" + File.separator;
    	
    	File topicDir = new File(dirName + topic);
    	
    	if (!topicDir.exists()) {
    		topicDir.mkdir();
    	}
    	
    	File file = new File(topicDir + File.separator + message.getId() + ".txt");

    	file.createNewFile();
    	logger.debug("Message File Created");
    	
    	OutputStream os = null;
    	try {
    		os = new FileOutputStream(file);
    		os.write(payload, 0, payload.length);
    	} catch (IOException e) {
    		logger.trace(e.getStackTrace());
    	} finally {
    		try {
    			os.close();
    		} catch (IOException e) {
    			logger.trace(e.getStackTrace());
    		}
    	}
    	
    	logger.debug("Message Bytes dumped into file: " + file.getName());
    	    	    	
//    	Files.write(file.toPath(), "hello".getBytes());
//    	file.setWritable(true, false);
//    	if (Files.isWritable(file.toPath())) {
//    		System.out.println("yes");
//    	}    	
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token){
		logger.debug("Messaged Delivered");
		MqttMessage message;
		try {
			message = token.getMessage();
			byte[] payload = message.getPayload();
	    	String text = new String(payload, "UTF-8");
	    	logger.info("Messaged Published: " + text);
		} catch (MqttException e) {
			logger.trace(e.getStackTrace());
		} catch (UnsupportedEncodingException e) {
			logger.trace(e.getStackTrace());
		}
	}

}
