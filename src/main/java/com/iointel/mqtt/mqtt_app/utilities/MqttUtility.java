package com.iointel.mqtt.mqtt_app.utilities;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppException;

public final class MqttUtility {

	private MqttUtility() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static MqttAsyncClient createClient(String broker, String clientId) throws MqttAppException {
		MqttAsyncClient client;
		try {
			client = new MqttAsyncClient(broker, clientId);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_CREATE);
		}
		return client;
	}

	public static void connectClient(MqttAsyncClient client, MqttConnectOptions options) throws MqttAppException {
		try {
			client.connect(options).waitForCompletion();
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_CONNECT);
		}
	}

	public static void setCallbackClient(MqttAsyncClient client, MqttCallback callback) {
		client.setCallback(callback);
	}

	public static void closeClient(MqttAsyncClient client) throws MqttAppException {
		try {
			client.close();
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_CLOSE);
		}
	}

	public static void disconnectClient(MqttAsyncClient client) throws MqttAppException {
		if (client == null || !client.isConnected()) {
			return;
		}
		try {
			client.disconnect();
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_DISCONNECT);
		}
	}

	public static void subscribeTopicArray(MqttAsyncClient client, String[] topics, int[] qos) throws MqttAppException {
		try {
			client.subscribe(topics, qos);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_SUBSCRIBE);
		}
	}

	public static void subscribeTopic(MqttAsyncClient client, String topic, int qos) throws MqttAppException {
		try {
			client.subscribe(topic, qos);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_SUBSCRIBE);
		}
	}

	public static void publishMessage(MqttAsyncClient client, String topic, MqttMessage message)
			throws MqttAppException {
		try {
			client.publish(topic, message);
		} catch (MqttException e) {
			throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_PUBLISH);
		}
	}

	public static void publish100Messages(MqttAsyncClient client) throws MqttAppException {
		for (int i = 0; i < 100; i++) {
			MqttMessage msg = MqttUtility.createMessage("hello from " + i, Constants.Init.QOS);
			try {
				client.publish(Constants.Init.TOPIC, msg);
			} catch (MqttException e) {
				throw new MqttAppException(Constants.Exceptions.Mqtt.CLIENT_PUBLISH);
			}
		}
	}

	public static MqttMessage createMessage(String content, int qos) {
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(qos);
		return message;
	}

	public static MqttConnectOptions createOptions(boolean automaticReconnect, boolean cleanSession) {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(cleanSession);
		options.setAutomaticReconnect(automaticReconnect);
		return options;
	}
}
