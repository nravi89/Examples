package test;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.rabbitmq.client.*;

public class RabbitMQConsumer {

	public static void main(String []args) throws Exception {
				
		 
			 
		     ConnectionFactory factory = new ConnectionFactory();
			 factory.setUsername("guest");
			 factory.setPassword("guest");
			 factory.setVirtualHost("/");
			 factory.setHost("localhost");
			 factory.setPort(5672);
			 
			 Connection conn = factory.newConnection();
			 Channel channel = conn.createChannel();
			 channel.basicQos(1);
			 String exchangeName = "myExchange";
			 String queueName = "TAAS";
			 String routingKey = "testRoute";
			 boolean durable = true;
			 //channel.exchangeDeclare(exchangeName, "direct", durable);
			 //channel.queueDeclare(queueName, durable,false,false,null);
			 //channel.queueBind(queueName, exchangeName, routingKey);
			 
			 
			 final Consumer consumer = new DefaultConsumer(channel) {
				  @Override
				  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				    String message = new String(body, "UTF-8");

				    System.out.println(" [x] Received '" + message + "'");
				    try {
				     // doWork(message);
				    	//ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
				    	//int a = ois.readInt();
				    	int a = new Integer(message);
				    	System.out.println(a);
				    }catch(Exception e){
				        System.err.println(e.getMessage());
				    }finally {
				    }
				      System.out.println(" [x] Done");
				    }
				 
				};
				
				boolean autoAck = true; // acknowledgment is covered below
				channel.basicConsume(queueName, autoAck, consumer);
				
			    //channel.close();
			    // conn.close();
			  }
}