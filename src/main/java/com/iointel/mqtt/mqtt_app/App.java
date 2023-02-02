package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.utilities.CloseUtility;
import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class App {

	private static String content = "Sample Message";
	private static final Logger logger = LogManager.getLogger(CloseUtility.class);

	public static void main(String[] args) throws MqttAppException {
		
		// Instead of sending 1 message and ending, send a message every two seconds
		MqttClient client;
		try {
			client = MqttUtility.createClient(Constants.Init.broker, Constants.Init.clientId);
			MqttUtility.connectClient(client, Cache.callback);
			MqttUtility.subscribeTopic(client, Constants.Init.topic);

			MqttMessage message = MqttUtility.createMessage(content, Constants.Init.qos);
			MqttUtility.publishMessage(client, Constants.Init.topic, message);
			MqttUtility.disconnectClient(client);
			CloseUtility.close(client);
		} catch (MqttAppException e) {
			logger.error(e);
		} finally {
		}
	}
}
