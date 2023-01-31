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
		logger.fatal(cause);
		App.connectClient();
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		if (message == null || message.getPayload() == null) {
			logger.info("Null Message Received");
			return;
		}
		byte[] payload = message.getPayload();
    	logger.debug("Subscribed Message: " + new String(payload, "UTF-8"));
    	
//    	File file = createMessageFile(topic, message.getId(), ".txt");
    	File file = createMessageFile(topic, message.getId());
    	writePayloadToFile(payload, file);
	}
	
	// Creates a binary file with id as name under tmpdir/MqttData/{topic}
	private File createMessageFile(String topic, int id) {
		return createMessageFile(topic, id, "");
	}
	
	// Saves each message in a file with the given extension
	// Saves the messages under a dir with the provided topic name
	// Creates each dir under another dir called "MqttData" in tmpdir
	// Returns a File object of the newly created file
	private File createMessageFile(String topic, int id, String extension) {
    	String dirName = System.getProperty("java.io.tmpdir") + File.separator + "MqttData";
    	
    	File mqttDataDir = new File(dirName);
    	
    	if (!mqttDataDir.exists()) {
    		mqttDataDir.mkdir();
    	}
    	
    	File topicDir = new File(dirName + File.separator + topic);
    	
    	if (!topicDir.exists()) {
    		topicDir.mkdir();
    	}
    	
    	File file = new File(topicDir + File.separator + id + extension);

    	try {
			file.createNewFile();
		} catch (IOException e) {
			logger.error(e);
		}
    	logger.debug("Message File Created");
    	return file;
	}
	
	// writes the payload into the provided file
	private void writePayloadToFile(byte[] payload, File file) {
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
