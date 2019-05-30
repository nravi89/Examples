package com.studykafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Properties;

public class KafkaExample {

    private final static String TOPIC = "cptest";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
            //"localhost:9092,localhost:9093,localhost:9094";

    public static void main(String... args) throws Exception {
        runProducer(5);
       // runConsumer();
    }

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    private static Consumer<Long, String> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        Consumer<Long, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }
    
    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();

        try {
        	String FILENAME = "/home/ravi/test/dining_remove.txt";
        	//br = new BufferedReader(new FileReader(FILENAME));
        	FileReader fr = new FileReader(FILENAME);
        	BufferedReader br = new BufferedReader(fr);

        	StringBuilder msg = new StringBuilder();
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				msg.append(sCurrentLine);
			}

			System.out.println(msg.toString());
           // for (long index = time; index < time + sendMessageCount; index++) {
                final ProducerRecord<Long, String> record =  new ProducerRecord<>(TOPIC, msg.toString());
                        //new ProducerRecord<>(TOPIC, index, "Hello Mom " + index);

                RecordMetadata metadata = producer.send(record).get();

                long elapsedTime = System.currentTimeMillis() - time;
                System.out.printf("sent record(key=%s value=%s) " +
                                "meta(partition=%d, offset=%d) time=%d\n",
                                record.key(), record.value(), metadata.partition(),
                        metadata.offset(), elapsedTime);
                
            

           // }
        }finally {
            producer.flush();
            producer.close();
        }
    }


    static void runConsumer() throws InterruptedException {
        Consumer<Long, String> consumer = createConsumer();

        try{
        	while (true) {
                final ConsumerRecords<Long, String> consumerRecords = consumer.poll(100);

                /*if (consumerRecords.count()==0) {
                	System.out.println("count 0");
                    break;
                }*/
                //System.out.println("sdd");
                consumerRecords.forEach(record -> {
                    System.out.println("Got Record: (" + record.key() + ", " + record.value()
                            + ") at offset " + record.offset());
                });
                consumer.commitAsync();
            }
        }finally{
        	 consumer.close();
        	 System.out.println("DONE");
        }
        
       
        
    }

}
