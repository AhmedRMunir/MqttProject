package com.iointel.mqtt.mqtt_app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class App {
	
	// Log4j Logger instantiation
	private static final Logger logger = LogManager.getLogger();
	static MqttClient client;
	static MyMqttCallback cb;
	static String broker;
	static String clientId;
	static MemoryPersistence persistence;
	
	
    public static void main(String[] args) {     
    	String topic        = "MQTT_Examples";
        String content      = "Sample Message";
        int qos             = 0;
        broker       = "tcp://localhost:1883";
        clientId     = "MainClient";
        persistence = new MemoryPersistence();
        cb = new MyMqttCallback();
        
        logger.info("hello from App.java");

        try {
        	// Create Client
            client = new MqttClient(broker, clientId, persistence);
            
            // Set Client Callback functions to custom callback
            client.setCallback(cb);
            
            // PUT THIS IN A METHOD AND THEN PUT IT IN CONNCETIONLOST
            // Define Connection options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);
            
            // Connect Client to Local Broker
            client.connect(options);
            
            // Subscribe to defined topic
            client.subscribe(topic);
            
            // Create a message and publish with defined topic
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(topic, message);
            
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
}
