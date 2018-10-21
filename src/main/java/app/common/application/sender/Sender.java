package app.common.application.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {
        private final String QUEUE_NAME = "eventsAdministrado";
        
        @Value("${rabbit.url}")
        private String urlRabbit;
    
        public boolean sendMessage(String message) throws Exception {
            //String url = System.getenv().get("RABBITMQ_URL");
            
            //System.err.println("url Rabbit: " + urlRabbit);
            
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(urlRabbit);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
            
            return true;
	}
	
}
