package com.iointel.mqtt.mqtt_app;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class App {

	public static void main(String[] args) throws MqttAppException {
		Constants.Init.logger.info("App Start");
		MqttClient client = null;
		try {
			client = MqttUtility.createClient(Constants.Init.broker, Constants.Init.clientId,
					Constants.Init.persistence);
			MqttUtility.connectClient(client, Constants.Init.callback);
			MqttUtility.subscribeTopic(client, Constants.Init.topic);
			MqttMessage message = MqttUtility.createMessage("Sample Message", Constants.Init.qos);
			MqttUtility.publishMessage(client, Constants.Init.topic, message);
		} catch (MqttAppException e) {
			Constants.Init.logger.error(e);
		} finally {
			MqttUtility.disconnectClient(client);
			MqttUtility.closeClient(client);
		}
	}
}
