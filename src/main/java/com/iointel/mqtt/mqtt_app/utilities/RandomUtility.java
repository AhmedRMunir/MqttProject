package com.iointel.mqtt.mqtt_app.utilities;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtility {
	private static final SecureRandom rand = new SecureRandom();
//	private static final ThreadLocalRandom threadsafeRand = new ThreadLocalRandom();
//	private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	private static final Base64.Encoder encoder = Base64.getEncoder().withoutPadding();

	public static String generateRandomString() {
		byte[] buffer = new byte[20];
		rand.nextBytes(buffer);
		return encoder.encodeToString(buffer);
	}

	public static String generateRandomStringThreadSafe() {
		byte[] buffer = new byte[20];
		ThreadLocalRandom.current().nextBytes(buffer);
		return encoder.encodeToString(buffer);
	}
}
