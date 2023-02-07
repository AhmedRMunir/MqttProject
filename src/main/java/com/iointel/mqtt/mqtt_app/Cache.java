package com.iointel.mqtt.mqtt_app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.eclipse.paho.client.mqttv3.MqttClient;

import com.iointel.mqtt.mqtt_app.utilities.ShutdownUtility;

public class Cache {

	private static final Logger logger = LogManager.getLogger(Cache.class);

	private Cache() {
	}

	public static final MqttAppCallback callback = new MqttAppCallback();
	public static final ScheduledExecutorService executorService = Executors
			.newScheduledThreadPool(Constants.Init.NUM_THREADS);

	public static Thread shutdownHook(MqttClient client) {
		return new Thread(() -> {
			logger.info("Shutting Down");
			ShutdownUtility.mqttClientShutdownHook(client);
			ShutdownUtility.executorServiceShutdownHook(Cache.executorService);
			if (LogManager.getContext() instanceof LoggerContext loggerContext) {
				logger.info("Shutting down log4j2");
				Configurator.shutdown(loggerContext);
			} else
				logger.warn("Unable to shutdown log4j2");
		});
	}
}
