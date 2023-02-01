package com.iointel.mqtt.mqtt_app.utilities;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppException;

public final class MqttUtility {

	public static MqttClient createClient(String broker, String clientId) throws MqttAppException {
		MqttClient client;
		try {
			client = new MqttClient(broker, clientId);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exception.Mqtt.ClientCreate);
		}
		return client;
	}

	public static void connectClient(MqttClient client, MqttCallback callback) throws MqttAppException {
		try {
			client.setCallback(callback);
			MqttConnectOptions options = createOptions();
			client.connect(options);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exception.Mqtt.ClientConnect);
		}
	}

	public static void closeClient(MqttClient client) throws MqttAppException {
		try {
			client.close();
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exception.Mqtt.ClientClose);
		}
	}

	public static void disconnectClient(MqttClient client) throws MqttAppException {
		if (client == null || !client.isConnected()) {
			return;
		}
		try {
			client.disconnect();
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exception.Mqtt.ClientDisconnect);
		}
	}

	public static void subscribeTopic(MqttClient client, String topic) throws MqttAppException {
		try {
			client.subscribe(topic);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exception.Mqtt.ClientSubscribe);
		}
	}

	public static void publishMessage(MqttClient client, String topic, MqttMessage message) throws MqttAppException {
		try {
			client.publish(topic, message);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exception.Mqtt.ClientPublish);
		}
	}

	public static MqttMessage createMessage(String content, int qos) {
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(qos);
		return message;
	}

	private static MqttConnectOptions createOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		options.setAutomaticReconnect(true);
		return options;
	}
}
