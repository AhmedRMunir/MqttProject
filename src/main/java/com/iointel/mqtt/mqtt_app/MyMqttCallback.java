package com.iointel.mqtt.mqtt_app;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.utilities.FileUtility;
import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class MyMqttCallback implements MqttCallback {

	private static final Logger logger = Constants.Init.logger;

	@Override
	public void connectionLost(Throwable cause) {
		logger.error(cause);
		MqttClient client;
		try {
			client = MqttUtility.createClient(Constants.Init.broker, Constants.Init.clientId, Cache.persistence);
			MqttUtility.connectClient(client, Cache.callback);
		} catch (MqttAppException e) {
			// Deal with this
			logger.error(e);
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws MqttAppException {
		if (message == null || message.getPayload() == null) {
			logger.info("Null Message Received");
			return;
		}
		try {
			logger.debug("Subscribed Message: " + new String(message.getPayload(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new MqttAppException(Constants.Exception.Util.UnsupportedEncoding);
		}
		File file = FileUtility.createFileAndDir(topic, message.getId());
		FileUtility.writePayloadToFile(message.getPayload(), file);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		logger.debug("Messaged Delivered");
		MqttMessage message;
		try {
			message = token.getMessage();
			byte[] payload = message.getPayload();
			String text = new String(payload, "UTF-8");
			logger.info("Messaged Published: " + text);
		} catch (MqttException e) {
			// What to do here?
			logger.trace(e);
		} catch (UnsupportedEncodingException e) {
			logger.trace(e);
		}
	}
}
