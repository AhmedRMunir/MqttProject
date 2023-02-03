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
			Thread mainThread = Thread.currentThread();
			Thread shutdownHook = new Thread(() -> {
				CloseUtility.joinThread(mainThread);
				CloseUtility.mqttClientShutdownHook(client);
				CloseUtility.executorServiceShutdownHook(Cache.executorService);
			});
			Runtime.getRuntime().addShutdownHook(shutdownHook);
//			send100Messages(client);
		} catch (MqttAppException e) {
			logger.error(e);
		}
	}

//	private static void send100Messages(MqttClient client) throws MqttAppException {
//		for (int i = 0; i < 100; i++) {
//			MqttMessage m = MqttUtility.createMessage("hello from" + i, 0);
//			MqttUtility.publishMessage(client, Constants.Init.topic, m);
//		}
//	}
}
