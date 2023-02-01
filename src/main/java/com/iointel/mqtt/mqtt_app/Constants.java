package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iointel.mqtt.mqtt_app.utilities.RandomUtility;

public class Constants {

	public static final class Init {
		public static final Logger logger = LogManager.getLogger();
		public static final String broker = "tcp://localhost:1883";
//		public static final String clientId = UUID.randomUUID().toString();
		public static final String clientId = RandomUtility.generateRandomStringThreadSafe();
		public static final String topic = "MQTT_Examples";
		public static final int qos = 0;
	}

	public static final class Exception {

		public static final class Mqtt {
			public static final String ClientCreate = "Client creation failed!";
			public static final String ClientConnect = "Client connect failed!";
			public static final String ClientSubscribe = "Client Subscribe failed!";
			public static final String ClientDisconnect = "Client disconnect failed!";
			public static final String ClientClose = "Client close failed!";
			public static final String ClientPublish = "Client publish failed!";
		}

		public static final class Util {
			public static final String FileCreate = "File creation failed!";
			public static final String OutputStream = "File Outputstream Error!";
			public static final String UnsupportedEncoding = "Encoding Unsupported Error!";
		}
	}

}
