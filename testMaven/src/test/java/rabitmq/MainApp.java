package rabitmq;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class MainApp {
	
	public static List<EventData> getAllMessagesFromRabbitMQ( String dataExtractsHostIP, 
			String dataExtractsUsername, String dataExtractsPassword,
		   String dataExtracts_callbackURL, String dataExtracts_Queue, Boolean isDataExtracts) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(dataExtractsHostIP);
		factory.setUsername(dataExtractsUsername);
		factory.setPassword(dataExtractsPassword);
		Connection connection = null;
		Channel channel = null;
		List<EventData> listEvent = new ArrayList<EventData>();
		List<String> messages = new ArrayList<String>();
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.basicQos(1);
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("x-message-ttl", 60000 * 60 * 8);
			channel.queueDeclare(dataExtracts_Queue, true, false, false, args);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			System.out.println(channel.toString());
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(dataExtracts_Queue, consumer);
			QueueingConsumer.Delivery delivery;
			boolean queueHasData = true;
			int count = channel.queueDeclarePassive(dataExtracts_Queue).getMessageCount();
			System.out.println(" number of messages in the queue ::" + count);
			while (count != 0) {
				delivery = consumer.nextDelivery();
				if (delivery == null)
					queueHasData = false;

				String message = new String(delivery.getBody(), "UTF-8");
				messages.add(message);
				count--;
				if (count == 0) {
					count = channel.queueDeclarePassive(dataExtracts_Queue).getMessageCount();
				}
				System.out.println("message:::" + message);
				EventData eventData = new EventData();
				List<UserData> userDataList = new LinkedList<>();
				String[] details = message.split("Data::");

				String timestamp = details[0];

				System.out.println("timestamp:::" + timestamp.trim());
				JSONParser jsonParser = new JSONParser();
				try {

					if (details.length > 1) {
						if (details[1] != null && !details[1].equals("null")) {
							JSONObject jsonObject = (JSONObject) jsonParser.parse(details[1]);
							JSONObject eventObject = (JSONObject) jsonObject.get("event");

							String loginNameFromRabbitMq = null;
							String providerAcctIdFromRabbitMq = null;
							String method = null;
							String methodType = null;
							String url = null;
							String fromDate = null;
							String toDate = null;
							long userCount = 0;

							String eventInfo = eventObject.get("info").toString();
							if (isDataExtracts) {

								if (null == ((JSONObject) eventObject.get("data")).get("userData")) {
									channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
									System.out.println("Skipping");
									continue;
								}
								fromDate = ((JSONObject) eventObject.get("data")).get("fromDate").toString();
								toDate = ((JSONObject) eventObject.get("data")).get("fromDate").toString();
								userCount = (long) ((JSONObject) eventObject.get("data")).get("userCount");
								org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) ((JSONObject) eventObject
										.get("data")).get("userData");

								for (int i = 0; i < jsonArray.size(); i++) {
									UserData userData = new UserData();
									loginNameFromRabbitMq = ((org.json.simple.JSONObject) ((org.json.simple.JSONObject) jsonArray
											.get(i)).get("user")).get("loginName").toString();

									method = ((org.json.simple.JSONObject) ((org.json.simple.JSONArray) ((org.json.simple.JSONObject) jsonArray
											.get(i)).get("links")).get(0)).get("rel").toString();

									methodType = ((org.json.simple.JSONObject) ((org.json.simple.JSONArray) ((org.json.simple.JSONObject) jsonArray
											.get(i)).get("links")).get(0)).get("methodType").toString();

									url = ((org.json.simple.JSONObject) ((org.json.simple.JSONArray) ((org.json.simple.JSONObject) jsonArray
											.get(i)).get("links")).get(0)).get("href").toString();

									userData.setLoginName(loginNameFromRabbitMq);
									userData.setMethod(method);
									userData.setMethodType(methodType);
									userData.setUrl(url);
									userDataList.add(userData);
								}
							} else {
								System.out.println("WEBhook called");
								if (null == eventObject.get("loginName")) {
									channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
									System.out.println("Skipping");
									continue;
								}
							
								loginNameFromRabbitMq = eventObject.get("loginName").toString();
								if(loginNameFromRabbitMq.startsWith("SITAUT")) {
									JSONObject dataObj = (JSONObject) eventObject.get("data");
									org.json.simple.JSONArray providerAccountArr = (org.json.simple.JSONArray) dataObj
											.get("providerAccount");
									providerAcctIdFromRabbitMq = ((org.json.simple.JSONObject) providerAccountArr.get(0))
											.get("id").toString();
								}

							}
							eventData.setEventLoginName(loginNameFromRabbitMq);
							eventData.setProviderAcctId(providerAcctIdFromRabbitMq);
							eventData.setEventInfo(eventInfo);
							eventData.setNotificationDate(timestamp);

							eventData.setUserData(userDataList);
							eventData.setUserCount(userCount);
							eventData.setFromDate(fromDate);
							eventData.setToDate(toDate);
							listEvent.add(eventData);
						}
					}
				} catch (ParseException ex) {
					System.out.println("Exception while parsing::" + ex.getMessage());
				}

				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}

			System.out.println("==============All the messages in RabitMQ==========");
			System.out.println(messages);
			System.out.println("===================================================");

			channel.queuePurge(dataExtracts_Queue);
			int count1 = channel.queueDeclarePassive(dataExtracts_Queue).getMessageCount();
			System.out.println(" number of messages in the queue ::" + count1);
		} catch (TimeoutException e) {
			throw new Exception("Exception occured while retriving/parsing events from RabbitMQ: " + e.getMessage());
		} finally {
			try {
				channel.close();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
			connection.close();
		}

		if(isDataExtracts) {
			System.out.println("DataExtracts Messages:"+listEvent);
			}else {
				System.out.println("Webhooks Messages::"+listEvent);
			}
		
		return listEvent;
	}
	
	public static void main(String[] args) {
		callWebhook();
	}
	
	private static void callExtractData() {
		
		String dataExtractsHostIP = "192.168.112.183";
		String dataExtractsUsername = "test"; 
		String dataExtractsPassword = "test";
		String dataExtracts_callbackURL = "http://192.168.112.183:8084/DEProj/SIT_DE";
		String dataExtracts_Queue = "YSLDataExtractsAutomationNew8"; 
		boolean isDataExtracts = true;
		
		try {
			List<EventData> data = getAllMessagesFromRabbitMQ(
					dataExtractsHostIP, 
					dataExtractsUsername, 
					dataExtractsPassword, 
					dataExtracts_callbackURL, 
					dataExtracts_Queue, 
					isDataExtracts);
			
			System.setOut(new PrintStream(new FileOutputStream("D:/test/eventData.txt")));
			for(EventData d:data)
			System.out.println(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void callWebhook(){
		    String Webhooks_callbackURL="http://192.168.112.183:8084/DEProj/SIT_WH_New";
		    String Webhooks_Queue="YSLWebHooks_AutoNPR";
		    String Webhooks_HostIP="192.168.112.183";
		    String Webhooks_Username="test";
		    String Webhooks_Password="test";
		    
		    try {
				List<EventData> data = getAllMessagesFromRabbitMQ(
						Webhooks_HostIP, 
						Webhooks_Username, 
						Webhooks_Password, 
						Webhooks_callbackURL, 
						Webhooks_Queue, 
						false);
				
				System.setOut(new PrintStream(new FileOutputStream("D:/test/eventData.txt")));
				for(EventData d:data)
				System.out.println(d);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
