package com.iointel.mqtt.mqtt_app.clients;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import com.iointel.mqtt.mqtt_app.Cache;
import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppCallback;
import com.iointel.mqtt.mqtt_app.MqttAppException;
import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;
import com.iointel.mqtt.mqtt_app.utilities.ShutdownUtility;

public class Subscriber {

	private static final Logger logger = LogManager.getLogger(Subscriber.class);

	public static void main(String[] args) {
		try {
			MqttAsyncClient client = MqttUtility.createClient(Constants.Init.BROKER,
					MqttAsyncClient.generateClientId());
			MqttUtility.setCallbackClient(client, new MqttAppCallback());
			MqttConnectOptions options = MqttUtility.createOptions(Constants.Init.AUTOMATIC_RECONNECT, Constants.Init.CLEAN_SESSION);
			MqttUtility.connectClient(client, options);
			MqttUtility.subscribeTopic(client, Constants.Init.TOPIC, Constants.Init.QOS);
			ShutdownUtility.addMqttClientShutdownHook(client);
			ShutdownUtility.addExecutorServiceShutdownHook(Cache.executorService);
		} catch (MqttAppException e) {
			logger.error(e);
		}
	}
}
