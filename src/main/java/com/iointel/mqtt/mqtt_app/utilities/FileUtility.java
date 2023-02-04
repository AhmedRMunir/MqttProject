package com.iointel.mqtt.mqtt_app.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppException;

public final class FileUtility {
	private FileUtility() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static File saveMessageToFile(String topic, MqttMessage message) throws MqttAppException {
		File file = FileUtility.createMqttMessageFile(topic,
				GeneralUtility.currDateAndTime() + RandomUtility.generateUUID() + ".txt");
		FileUtility.writePayloadToFile(message.getPayload(), file);
		return file;
	}

	public static File createMqttMessageFile(String topic, String fileName) throws MqttAppException {
		return createFileAndDirsInPath(Constants.Init.TMPDIR_PATH + File.separator + Constants.Init.MESSAGE_DIR_NAME
				+ File.separator + topic + File.separator + fileName);
	}

	public static File createFileAndDirsInTempDir(String path) throws MqttAppException {
		return createFileAndDirsInPath(Constants.Init.TMPDIR_PATH + File.separator + path);
	}

	public static File createFileAndDirsInPath(String filePath) throws MqttAppException {
		File file = new File(filePath);
		createDirsInPath(file);
		return createFile(filePath);
	}

	public static void createDirsInPath(File targetFile) throws MqttAppException {
		File parent = targetFile.getParentFile();
		if (parent != null && !parent.exists() && !parent.mkdirs()) {
			throw new MqttAppException(Constants.Exceptions.Util.DIR_CREATE);
		}
	}

	public static File createDir(String dirName) {
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

	public static File createFile(String filePath) throws MqttAppException {
		File file = new File(filePath);
		try {
			if (!file.createNewFile()) {
				throw new MqttAppException(Constants.Exceptions.Util.FILE_CREATE);
			}
		} catch (IOException e) {
			throw new MqttAppException(Constants.Exceptions.Util.FILE_CREATE);
		}
		return file;
	}

	public static void writePayloadToFile(byte[] payload, File file) throws MqttAppException {
		try (FileOutputStream os = new FileOutputStream(file)) {
			os.write(payload, 0, payload.length);
			CloseUtility.close(os);
		} catch (IOException e) {
			throw new MqttAppException(Constants.Exceptions.Util.OUTPUTSTREAM);
		}
	}
}
