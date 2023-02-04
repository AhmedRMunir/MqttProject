package com.iointel.mqtt.mqtt_app.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class GeneralUtility {
	private GeneralUtility() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static String currDateAndTime() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd_hh-mm-ss.SSS");
		return ldt.format(formatter);
	}
}
