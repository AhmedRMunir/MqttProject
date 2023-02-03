package com.iointel.mqtt.mqtt_app;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cache {
	public static MqttAppCallback callback = new MqttAppCallback();
	public static Queue<SaveMessageTask> taskQueue = new LinkedList<>();
	public static ExecutorService executorService = Executors.newFixedThreadPool(Constants.Init.NumThreads);
}
