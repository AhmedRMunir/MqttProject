package com.iointel.mqtt.mqtt_app;

import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Cache {
	public static MyMqttCallback callback = new MyMqttCallback();
	public static MqttClientPersistence persistence = new MemoryPersistence();
}
