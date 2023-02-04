package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.iointel.mqtt.mqtt_app.utilities.CloseUtility;
import com.iointel.mqtt.mqtt_app.utilities.MqttUtility;

public class Subscriber {

	private static final Logger logger = LogManager.getLogger(Subscriber.class);

	public static void main(String[] args) {
		try (MqttClient client = MqttUtility.createClient(Constants.Init.BROKER, Constants.Init.CLIENT_ID)) {
			MqttUtility.connectClient(client, Cache.callback);
			MqttUtility.subscribeTopic(client, Constants.Init.TOPIC);
			Thread shutdownHook = shutdownHook(client);
			Runtime.getRuntime().addShutdownHook(shutdownHook);
		} catch (MqttAppException | MqttException e) {
			logger.error(e);
		}
	}

	private static Thread shutdownHook(MqttClient client) {
		return new Thread(() -> {
			logger.info("Shutting Down");
			CloseUtility.mqttClientShutdownHook(client);
			CloseUtility.executorServiceShutdownHook(Cache.executorService);
			if (LogManager.getContext() instanceof LoggerContext loggerContext) {
				logger.info("Shutting down log4j2");
				Configurator.shutdown(loggerContext);
			} else
				logger.warn("Unable to shutdown log4j2");
		});
	}
}
