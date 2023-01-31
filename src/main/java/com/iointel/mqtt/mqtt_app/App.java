package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class App {
	
	// Log4j Logger instantiation
	private static final Logger logger = LogManager.getLogger();
	private static MqttClient client;
	private static MyMqttCallback cb;
	private static String broker;
	private static String clientId;
	private static MemoryPersistence persistence;
	
	
    public static void main(String[] args) {     
    	String topic        = "MQTT_Examples";
        String content      = "Sample Message";
        int qos             = 0;
        broker       = "tcp://localhost:1883";
        clientId     = "MainClient";
        persistence = new MemoryPersistence();
        cb = new MyMqttCallback();
        
        logger.info("App start");

        try {
        	connectClient();        	
            
            // Subscribe to defined topic
            client.subscribe(topic);
            
            publishMessage(topic, content, qos);
                        
        	// Disconnect Client
            client.disconnect();
            
            // Close Client
            client.close();

            // exit
            System.exit(0);
        } catch(MqttException e) {
        	logger.info("Reason " + e.getReasonCode());
        	logger.info("Localized Message: " + e.getLocalizedMessage());
        	logger.info("Cause: " + e.getCause());
        	logger.info("Exception: " + e);
        	logger.error("Reason " + e.getReasonCode());
        	logger.error(e);
        }
    }
    
    // Connects client to defined broker, clientId and persistence in class
    // Sets cleanSession and automatic reconnect to true
    public static boolean connectClient() {
    	// Create Client
        try {
			client = new MqttClient(broker, clientId, persistence);
			// Set Client Callback functions to custom callback
	        client.setCallback(cb);

	        // Define Connection options
	        MqttConnectOptions options = new MqttConnectOptions();
	        options.setCleanSession(true);
	        options.setAutomaticReconnect(true);
	        
	        // Connect Client to Local Broker
	        client.connect(options);
	        
	        return true;
		} catch (MqttException e) {
			logger.error(e);
			return false;
		}
    }
    
    // Publishes a message with a given topic, content and QoS
    private static boolean publishMessage(String topic, String content, int qos) {
    	// Create a message and publish with defined topic
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        try {
			client.publish(topic, message);
			return true;
		} catch (MqttPersistenceException e) {
			logger.error(e);
		} catch (MqttException e) {
			logger.error(e);
		}
        return false;
    }
}
