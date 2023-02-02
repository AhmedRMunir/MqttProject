package com.iointel.mqtt.mqtt_app.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.iointel.mqtt.mqtt_app.Constants;
import com.iointel.mqtt.mqtt_app.MqttAppException;

public final class FileUtility {

	public static File createMqttMessageFile(String topic, String fileName) throws MqttAppException {
		return createFileAndDirsInPath(Constants.Init.tempDirPath + File.separator + Constants.Init.messageDirName
				+ File.separator + topic + File.separator + fileName);
	}

	public static File createFileAndDirsInTempDir(String path) throws MqttAppException {
		return createFileAndDirsInPath(Constants.Init.tempDirPath + File.separator + path);
	}

	public static File createFileAndDirsInPath(String filePath) throws MqttAppException {
		File file = new File(filePath);
		createDirsInPath(file);
		return createFile(filePath);
	}

	public static void createDirsInPath(File targetFile) throws MqttAppException {
		File parent = targetFile.getParentFile();
		if (parent != null && !parent.exists() && !parent.mkdirs()) {
			throw new MqttAppException(Constants.Exception.Util.DirCreate);
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
			file.createNewFile();
		} catch (IOException e) {
			throw new MqttAppException(Constants.Exception.Util.FileCreate);
		}
		return file;
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
