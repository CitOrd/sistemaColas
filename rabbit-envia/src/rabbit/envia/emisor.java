package rabbit.envia;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Comunidad del anillo
 */
public class emisor {
    private final static String QUEUE_NAME = "cola";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {        
        
        ConnectionFactory conexion = new ConnectionFactory();
        conexion.setHost("localhost");
        
        try(Connection connection = conexion.newConnection();
            Channel channel = connection.createChannel()){
            
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            String mensaje = "hello world";
            
            channel.basicPublish("", QUEUE_NAME, null, mensaje.getBytes(StandardCharsets.UTF_8));
            
            System.out.println("[x] sent: "+ mensaje + "");
        }catch(Exception e){
            
        }
    }
    
}
