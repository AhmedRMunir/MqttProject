package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class Subscriber {

	private static final Logger logger = LogManager.getLogger(Subscriber.class);

	public static void main(String[] args) {
		try (MqttClient client = MqttUtility.createClient(Constants.Init.BROKER, Constants.Init.CLIENT_ID)) {
			MqttUtility.connectClient(client, Cache.callback);
			MqttUtility.subscribeTopic(client, Constants.Init.TOPIC);
			Thread shutdownHook = Cache.shutdownHook(client);
			Runtime.getRuntime().addShutdownHook(shutdownHook);
		} catch (MqttAppException | MqttException e) {
			logger.error(e);
		}
	}
}
