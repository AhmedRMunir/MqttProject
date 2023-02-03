package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class MqttAppCallback implements MqttCallback {

	private static final Logger logger = LogManager.getLogger(MqttAppCallback.class);

	@Override
	public void connectionLost(Throwable cause) {
		logger.error(cause);
		MqttClient client;
		try {
			client = MqttUtility.createClient(Constants.Init.broker, Constants.Init.clientId);
			MqttUtility.connectClient(client, Cache.callback);
		} catch (MqttAppException e) {
			logger.error(e);
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws MqttAppException {
		if (message == null || message.getPayload() == null) {
			logger.info("Null Message Received");
		} else {
			Cache.executorService.execute(new SaveMessageTask(topic, message));
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		logger.debug("Messaged Delivered");
	}
}
