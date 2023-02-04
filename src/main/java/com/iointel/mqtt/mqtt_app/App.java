package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.utilities.CloseUtility;
import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class App {

	private static String content = "Sample Message";
	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) throws MqttAppException {

		MqttClient client;
		try {
			client = MqttUtility.createClient(Constants.Init.BROKER, Constants.Init.CLIENT_ID);
			MqttUtility.connectClient(client, Cache.callback);
			MqttUtility.subscribeTopic(client, Constants.Init.TOPIC);
			MqttMessage message = MqttUtility.createMessage(content, Constants.Init.QOS);
			MqttUtility.publishMessage(client, Constants.Init.TOPIC, message);
			MqttUtility.disconnectClient(client);
			CloseUtility.close(client);
		} catch (MqttAppException e) {
			logger.error(e);
		}
	}
}
