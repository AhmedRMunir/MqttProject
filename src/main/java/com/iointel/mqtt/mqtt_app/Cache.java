package com.iointel.mqtt.mqtt_app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Cache {

	private Cache() {
		
	}

	// TODO: remove callback? and instead create a new object whenever we need it?
	// Only time it is used is when we create a client
//	public static final MqttAppCallback callback = new MqttAppCallback();
	public static final ScheduledExecutorService executorService = Executors
			.newScheduledThreadPool(Constants.Init.NUM_THREADS);
}
