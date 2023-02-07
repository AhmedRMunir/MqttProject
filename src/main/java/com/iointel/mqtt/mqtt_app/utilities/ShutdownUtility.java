package com.iointel.mqtt.mqtt_app.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;

import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppException;

public class ShutdownUtility {

	private static final Logger logger = LogManager.getLogger(ShutdownUtility.class);

	private ShutdownUtility() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static void mqttClientShutdownHook(MqttClient client) {
		try {
			MqttUtility.disconnectClient(client);
		} catch (MqttAppException e) {
			logger.error(Constants.Exceptions.Mqtt.CLIENT_DISCONNECT, e);
		}
		CloseUtility.close(client);
	}

	public static void executorServiceShutdownHook(ExecutorService pool) {
		pool.shutdown();
		try {
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
					logger.error(Constants.Exceptions.Util.EXECUTORSERVICE_SHUTDOWN);
				}
			}
		} catch (InterruptedException e) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	public static void joinThread(Thread thread) throws MqttAppException {
		try {
			thread.join();
		} catch (InterruptedException e) {
			logger.error(Constants.Exceptions.Util.THREAD_INTERRUPTED, e);
			thread.interrupt();
			throw new MqttAppException(Constants.Exceptions.Util.THREAD_INTERRUPTED);
		}
	}

}
