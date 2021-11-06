package rabbit.recibe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 *
 * @author Comunidad del anillo
 */
public class Receptor {
    
    private final static String QUEUE_NAME = "cola";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        ConnectionFactory factory = new ConnectionFactory();
        
        factory.setHost("localhost");
        
        Connection connection = factory.newConnection();
        
        Channel channel = connection.createChannel();
        
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("[*] esperando mensajes para salir presione Ctrl+C");
        
        DeliverCallback deliverCallback = (consumerTag, delivery) ->{
            String mensaje = new String(delivery.getBody(), "UTF-8");
            System.out.println("[X] recibido: "+mensaje+" ");            
        };
        
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag->{ });
    }
    
}
