package com.iointel.mqtt.mqtt_app.utilities;

import java.io.Closeable;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;

import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppException;

public final class CloseUtility {
	private static final Logger logger = LogManager.getLogger(CloseUtility.class);

	public static void close(Object object) {
		if (object == null) {
			return;
		}
		try {
			if (object instanceof Connection) {
				((Connection) object).close();
			} else if (object instanceof ResultSet) {
				((ResultSet) object).close();
			} else if (object instanceof PreparedStatement) {
				((PreparedStatement) object).close();
			} else if (object instanceof Statement) {
				((Statement) object).close();
			} else if (object instanceof CallableStatement) {
				((CallableStatement) object).close();
			} else if (object instanceof Closeable) {
				((Closeable) object).close();
			} else if (object instanceof OutputStream) {
				((OutputStream) object).close();
			} else if (object instanceof MqttClient) {
				((MqttClient) object).close();
			}
		} catch (Exception e) {
			logger.error("Exception occured during object close", e);
		}
	}

	public static void mqttClientShutdownHook(MqttClient client) {
		try {
			MqttUtility.disconnectClient(client);
		} catch (MqttAppException e) {
			logger.error(Constants.Exception.Mqtt.ClientDisconnect, e);
		}
		CloseUtility.close(client);
	}

	public static void executorServiceShutdownHook(ExecutorService pool) {
		pool.shutdown();
		try {
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
					logger.error(Constants.Exception.Util.ExecutorServiceShutdown);
				}
			}
		} catch (InterruptedException e) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	public static void joinThread(Thread thread) {
		try {
			thread.join();
		} catch (InterruptedException e) {
			logger.error(Constants.Exception.Util.ThreadInterrupted, e);
		}
	}
}