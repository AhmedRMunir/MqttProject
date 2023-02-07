package com.iointel.mqtt.mqtt_app;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

public final class Constants {

	public static final class Init {
		public static final String BROKER = "tcp://localhost:1883";
		public static final String CLIENT_ID = MqttAsyncClient.generateClientId();
		public static final String TOPIC = "MQTT_Examples";
		public static final int QOS = 0;
		public static final boolean AUTOMATIC_RECONNECT = true;
		public static final boolean CLEAN_SESSION = true;
		public static final String TMPDIR_PATH = System.getProperty("java.io.tmpdir");
		public static final String MESSAGE_DIR_NAME = "MqttData";
		public static final String FILE_EXTENSION = ".json";
		public static final int NUM_THREADS = 1;
	}

	public static final class Exceptions {
		public static final class Mqtt {
			public static final String CLIENT_CREATE = "Client creation failed!";
			public static final String CLIENT_CONNECT = "Client connect failed!";
			public static final String CLIENT_SUBSCRIBE = "Client Subscribe failed!";
			public static final String CLIENT_DISCONNECT = "Client disconnect failed!";
			public static final String CLIENT_CLOSE = "Client close failed!";
			public static final String CLIENT_PUBLISH = "Client publish failed!";
		}

		public static final class Util {
			public static final String FILE_CREATE = "File creation failed!";
			public static final String OUTPUTSTREAM = "File Outputstream Error!";
			public static final String UNSUPPORTED_ENCODING = "Encoding Unsupported Error!";
			public static final String DIR_CREATE = "Directory Creation Error!";
			public static final String EXECUTORSERVICE_SHUTDOWN = "ExecutorService did not terminate!";
			public static final String THREAD_INTERRUPTED = "Thread Interrupted!";
		}
	}
}
