package com.iointel.mqtt.mqtt_app;

public class MqttAppException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MqttAppException() {
		super();
	}

	public MqttAppException(final String message) {
		super(message);
	}

	public MqttAppException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public MqttAppException(final String message, final String detail, final Throwable cause) {
		super("Message: " + message + "\nDetails: " + detail + "\nCause: " + cause + "\n");
	}

	public MqttAppException(final Throwable cause) {
		super(cause);
	}
}
