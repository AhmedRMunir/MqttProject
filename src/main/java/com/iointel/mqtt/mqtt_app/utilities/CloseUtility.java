package com.iointel.mqtt.mqtt_app.utilities;

import java.io.Closeable;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;

import com.iointel.mqtt.mqtt_app.Constants;

public class CloseUtility {
	private static final Logger logger = Constants.Init.logger;

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

			}
		} catch (Exception e) {
			logger.error("Exception occured during object close", e);
		}
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			logger.error("Exception occured during thread sleep", e);
		}
	}

}