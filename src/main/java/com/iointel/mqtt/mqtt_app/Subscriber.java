package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;

import com.iointel.mqtt.mqtt_app.utilities.CloseUtility;
import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class Subscriber {

	private static final Logger logger = LogManager.getLogger(CloseUtility.class);

	public static void main(String[] args) {
		MqttClient client;
		try {
			client = MqttUtility.createClient(Constants.Init.broker, Constants.Init.clientId);
			MqttUtility.connectClient(client, Cache.callback);
			MqttUtility.subscribeTopic(client, Constants.Init.topic);
		} catch (MqttAppException e) {
			logger.error(e);
		}
//
		while (true) {

		}

		// Add Shutdown Hook
//		MqttUtility.disconnectClient(client);
//		CloseUtility.close(client);

	}

}
