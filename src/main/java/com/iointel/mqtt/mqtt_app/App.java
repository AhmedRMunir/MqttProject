package com.iointel.mqtt.mqtt_app;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class App {
    public static void main(String[] args) {     
    	String topic        = "MQTT_Examples";
        String content      = "Message from MqttPublishSample";
        int qos             = 0;
        String broker       = "tcp://localhost:1883";
        String clientId     = "MainClient";
        MemoryPersistence persistence = new MemoryPersistence();
        MyMqttCallback cb = new MyMqttCallback();

        try {
        	
        	// Create Sample Client and connect
            MqttClient client = new MqttClient(broker, clientId, persistence);
            client.setCallback(cb);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
//            System.out.println("Connecting to broker: "+broker);
            client.connect(connOpts);
//            System.out.println("Connected");
            
            // Subscribe to specified topic
            // On message received, parse payload and display on console
            client.subscribe(topic);
            
            // Publish a message with specific topic and QoS
//            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(topic, message);
//            System.out.println("Message published");
            
            // Disconnect Client
            client.disconnect();
//            System.out.println("Disconnected");
            
            // Close Client
            client.close();
//            System.out.println("Closed");
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
