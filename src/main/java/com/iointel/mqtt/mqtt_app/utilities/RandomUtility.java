package com.iointel.mqtt.mqtt_app.utilities;

import java.util.UUID;

public final class RandomUtility {
	private RandomUtility() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString() + Thread.currentThread().getName();
	}
}
