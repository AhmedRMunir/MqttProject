package com.iointel.mqtt.mqtt_app.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppException;

public class FileUtility {

	public static File createFileAndDir(String topic, int id) throws MqttAppException {
		return createFileAndDir(topic, id, "");
	}

	// Make a recurring createDir method
	public static File createFileAndDir(String topic, int id, String extension) throws MqttAppException {
		String dirName = System.getProperty("java.io.tmpdir") + File.separator + "MqttData";
		createDir(dirName);
		dirName += File.separator + topic;
		createDir(dirName);
		File file = new File(dirName + File.separator + id + extension);
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new MqttAppException(Constants.Exception.Util.FileCreate);
		}
		return file;
	}

	public static File createDir(String dirName) {
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

	public static void writePayloadToFile(byte[] payload, File file) throws MqttAppException {
		try (FileOutputStream os = new FileOutputStream(file)) {
			os.write(payload, 0, payload.length);
			CloseUtility.close(os);
		} catch (IOException e) {
			throw new MqttAppException(Constants.Exception.Util.OutputStream);
		}
	}
}
