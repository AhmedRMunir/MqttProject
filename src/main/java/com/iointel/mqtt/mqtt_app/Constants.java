package com.iointel.mqtt.mqtt_app;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Constants {

	public static final class Exception {
		public static final class Application {
			public static final String Initialization = "Application initialization failed!";
			public static final String ScheduledExecutor = "Scheduled Executor Service failed!";
		}

		public static final class Mqtt {
			public static final String ClientCreate = "Client creation failed!";
			public static final String ClientConnect = "Client connect failed!";
			public static final String ClientSubscribe = "Client Subscribe failed!";
			public static final String ClientDisconnect = "Client disconnect failed!";
			public static final String ClientClose = "Client close failed!";
			public static final String ClientPublish = "Client publish failed!";
		}
	}

	public static final class Init {
		public static final Logger logger = LogManager.getLogger();
		public static MyMqttCallback callback = new MyMqttCallback();
		public static MqttClientPersistence persistence = new MemoryPersistence();
		public static String broker = "tcp://localhost:1883";
		public static String clientId = UUID.randomUUID().toString();
		public static String topic = "MQTT_Examples";
		public static int qos = 0;

	}
}
