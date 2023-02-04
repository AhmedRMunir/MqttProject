package com.iointel.mqtt.mqtt_app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Cache {
	private Cache() {

	}

	public static final MqttAppCallback callback = new MqttAppCallback();
	public static final ScheduledExecutorService executorService = Executors
			.newScheduledThreadPool(Constants.Init.NUM_THREADS);
}
