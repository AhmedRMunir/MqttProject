package com.iointel.mqtt.mqtt_app.utilities;

import java.util.UUID;

public class RandomUtility {

	public static String generateUUID() {
		return UUID.randomUUID().toString() + Thread.currentThread().getName();
	}
}
