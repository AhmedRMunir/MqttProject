package com.iointel.mqtt.mqtt_app.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtility {
	
	public static File createMessageFile(String topic, int id) throws IOException {
		return createMessageFile(topic, id, "");
	}
	
	public static File createMessageFile(String topic, int id, String extension) throws IOException {
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
			throw e;
		}
    	return file;
	}
	
	public static void writePayloadToFile(byte[] payload, File file) throws IOException {
		OutputStream os = null;
    	try {
    		os = new FileOutputStream(file);
    		os.write(payload, 0, payload.length);
    	} catch (IOException e) {
    		throw e;
    	} finally {
    		try {
    			os.close();
    		} catch (IOException e) {
    			throw e;
    		}
    	}
	}
}
