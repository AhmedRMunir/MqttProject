package com.iointel.mqtt.mqtt_app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Cache {

//	private static final Logger logger = LogManager.getLogger(Cache.class);

	private Cache() {
		
	}

	// TODO: remove callback? and instead create a new object whenever we need it?
	// Only time it is used is when we create a client
	public static final MqttAppCallback callback = new MqttAppCallback();
	public static final ScheduledExecutorService executorService = Executors
			.newScheduledThreadPool(Constants.Init.NUM_THREADS);

//	public static Thread shutdownHook(MqttAsyncClient client) {
//		return new Thread(() -> {
//			logger.info("Shutting Down");
//			ShutdownUtility.mqttClientShutdownHook(client);
//			ShutdownUtility.executorServiceShutdownHook(Cache.executorService);
//			if (LogManager.getContext() instanceof LoggerContext loggerContext) {
//				logger.info("Shutting down log4j2");
//				Configurator.shutdown(loggerContext);
//			} else
//				logger.warn("Unable to shutdown log4j2");
//		});
//	}
//
//	public static void addRuntimeShutdownHook(MqttAsyncClient client) {
//		Runtime.getRuntime().addShutdownHook(shutdownHook(client));
//	}
}
