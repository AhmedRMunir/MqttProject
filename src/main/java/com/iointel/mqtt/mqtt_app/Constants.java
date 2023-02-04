package com.iointel.mqtt.mqtt_app;

import com.iointel.mqtt.mqtt_app.utilities.RandomUtility;

public final class Constants {

	private Constants() {

	}

	public static final class Init {
		private Init() {

		}

		public static final String BROKER = "tcp://localhost:1883";
		public static final String CLIENT_ID = RandomUtility.generateUUID();
		public static final String TOPIC = "MQTT_Examples";
		public static final int QOS = 0;
		public static final String TMPDIR_PATH = System.getProperty("java.io.tmpdir");
		public static final String MESSAGE_DIR_NAME = "MqttData";
		public static final int NUM_THREADS = 2;
	}

	public static final class Exceptions {
		private Exceptions() {

		}

		public static final class Mqtt {
			private Mqtt() {

			}

			public static final String CLIENT_CREATE = "Client creation failed!";
			public static final String CLIENT_CONNECT = "Client connect failed!";
			public static final String CLIENT_SUBSCRIBE = "Client Subscribe failed!";
			public static final String CLIENT_DISCONNECT = "Client disconnect failed!";
			public static final String CLIENT_CLOSE = "Client close failed!";
			public static final String CLIENT_PUBLISH = "Client publish failed!";
		}

		public static final class Util {
			private Util() {

			}

			public static final String FILE_CREATE = "File creation failed!";
			public static final String OUTPUTSTREAM = "File Outputstream Error!";
			public static final String UNSUPPORTED_ENCODING = "Encoding Unsupported Error!";
			public static final String DIR_CREATE = "Directory Creation Error!";
			public static final String EXECUTORSERVICE_SHUTDOWN = "ExecutorService did not terminate!";
			public static final String THREAD_INTERRUPTED = "Thread Interrupted!";
		}
	}

}
