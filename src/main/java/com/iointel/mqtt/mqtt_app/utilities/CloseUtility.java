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

	private CloseUtility() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static void close(Object object) {
		if (object == null) {
			return;
		}
		try {
			if (object instanceof Connection connection) {
				connection.close();
			} else if (object instanceof ResultSet resultSet) {
				resultSet.close();
			} else if (object instanceof PreparedStatement preparedStatement) {
				preparedStatement.close();
			} else if (object instanceof Statement statement) {
				statement.close();
			} else if (object instanceof CallableStatement callableStatement) {
				callableStatement.close();
			} else if (object instanceof Closeable closeable) {
				closeable.close();
			} else if (object instanceof OutputStream outputStream) {
				outputStream.close();
			} else if (object instanceof MqttClient mqttClient) {
				mqttClient.close();
			}
		} catch (Exception e) {
			logger.error("Exception occured during object close", e);
		}
	}

	public static void mqttClientShutdownHook(MqttClient client) {
		try {
			MqttUtility.disconnectClient(client);
		} catch (MqttAppException e) {
			logger.error(Constants.Exceptions.Mqtt.CLIENT_DISCONNECT, e);
		}
		CloseUtility.close(client);
	}

	public static void executorServiceShutdownHook(ExecutorService pool) {
		pool.shutdown();
		try {
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
					logger.error(Constants.Exceptions.Util.EXECUTORSERVICE_SHUTDOWN);
				}
			}
		} catch (InterruptedException e) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	public static void joinThread(Thread thread) throws MqttAppException {
		try {
			thread.join();
		} catch (InterruptedException e) {
			logger.error(Constants.Exceptions.Util.THREAD_INTERRUPTED, e);
			thread.interrupt();
			throw new MqttAppException(Constants.Exceptions.Util.THREAD_INTERRUPTED);
		}
	}
}