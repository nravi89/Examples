package test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQProducer {

	public static void main(String[] args) throws IOException, TimeoutException {

		
		 
		 
	     ConnectionFactory factory = new ConnectionFactory();
		 factory.setUsername("guest");
		 factory.setPassword("guest");
		 factory.setVirtualHost("/");
		 factory.setHost("localhost");
		 factory.setPort(5672);
		 
		 Connection conn = factory.newConnection();
		 Channel channel = conn.createChannel();
		 
		 String exchangeName = "myExchange";
		 String queueName = "TAASa";
		 String routingKey = "testRoute";
		 boolean durable = true;
		 //channel.exchangeDeclare(exchangeName, "direct", durable);
		 //channel.queueDeclare(queueName, durable,false,false,null);
		 //channel.queueBind(queueName, exchangeName, routingKey);
		 channel.basicQos(1);
		 
		 String message = "5";
		 channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		 System.out.println(" [x] Sent '" + message + "'");
		 channel.close();
		 //connection.close();
		 
		  
	}
}
