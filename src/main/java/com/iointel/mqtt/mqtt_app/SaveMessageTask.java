package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.utilities.FileUtility;

public class SaveMessageTask implements Runnable {

	private String topic;
	private MqttMessage message;
	private static final Logger logger = LogManager.getLogger(SaveMessageTask.class);

	public SaveMessageTask(String topic, MqttMessage message) {
		this.topic = topic;
		this.message = message;
	}

	@Override
	public void run() {
		logger.info("logging message");
		try {
			FileUtility.saveMessageToFile(topic, message);
		} catch (MqttAppException e) {
			logger.error(e);
		}
	}

}
