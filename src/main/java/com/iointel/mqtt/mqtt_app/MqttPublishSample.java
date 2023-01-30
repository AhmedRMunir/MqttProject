package com.iointel.mqtt.mqtt_app;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublishSample {

public static void main(String[] args) {

    String topic        = "MQTT Examples";
    String content      = "Message from MqttPublishSample";
    int qos             = 2;
    String broker       = "tcp://localhost:1883";
    String clientId     = "JavaSample";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
    	
    	// Create Sample Client and connect
        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        System.out.println("Connecting to broker: "+broker);
        sampleClient.connect(connOpts);
        System.out.println("Connected");
        
        // Subscribe to specified topic
        // On message received, parse payload and display on console
        sampleClient.subscribe(topic, (t, msg) -> {
        	byte[] payload = msg.getPayload();
        	String text = new String(payload, "UTF-8");
        	System.out.println("Subscribed Message: " + text);
        });
        
        // Publish a message with specific topic and QoS
        System.out.println("Publishing message: "+content);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        sampleClient.publish(topic, message);
        System.out.println("Message published");
        
        
        
        // Disconnect Client
        sampleClient.disconnect();
        System.out.println("Disconnected");
        
        // Close Client
        sampleClient.close();
        System.out.println("Closed");
        System.exit(0);
    } catch(MqttException me) {
        System.out.println("reason "+me.getReasonCode());
        System.out.println("msg "+me.getMessage());
        System.out.println("loc "+me.getLocalizedMessage());
        System.out.println("cause "+me.getCause());
        System.out.println("excep "+me);
        me.printStackTrace();
    }
}
}
