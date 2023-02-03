package com.iointel.mqtt.mqtt_app.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class GeneralUtility {
	public static String currDateAndTime() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd_hh-mm-ss.SSS");
		String timeString = ldt.format(formatter);

		return timeString;
	}
}
