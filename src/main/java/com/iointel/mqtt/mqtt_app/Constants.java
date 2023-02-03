package com.iointel.mqtt.mqtt_app;

import com.iointel.mqtt.mqtt_app.utilities.RandomUtility;

public final class Constants {

	public static final class Init {
		public static final String broker = "tcp://localhost:1883";
		public static final String clientId = RandomUtility.generateUUID();
		public static final String topic = "MQTT_Examples";
		public static final int qos = 0;
		public static final String tempDirPath = System.getProperty("java.io.tmpdir");
		public static final String messageDirName = "MqttData";
		public static final int NumThreads = 2;
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
			public static final String DirCreate = "Directory Creation Error!";
			public static final String ExecutorServiceShutdown = "ExecutorService did not terminate!";
			public static final String ThreadInterrupted = "Thread Interrupted!";
		}
	}

}
