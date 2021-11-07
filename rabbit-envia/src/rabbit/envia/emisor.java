package rabbit.envia;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;
import ObjetoNegocio.Usuario;
import java.util.Random;
import javax.json.Json;


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
        
        Usuario usr = new Usuario(new Random().nextInt(10)+1+"","Jose",new Random().nextInt(60)+1);
        String usrString = Json.createObjectBuilder()
                .add("id", usr.getId())
                .add("nombre", usr.getNombre())
                .add("edad", usr.getEdad()).build().toString();
                
        ConnectionFactory conexion = new ConnectionFactory();
        conexion.setHost("localhost");
        
        try(Connection connection = conexion.newConnection();
            Channel channel = connection.createChannel()){
            
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);            
            
            channel.basicPublish("", QUEUE_NAME, null, usrString.getBytes(StandardCharsets.UTF_8));
            
            System.out.println("[x] sent: "+ usrString + "");
        }catch(Exception e){
            
        }
    }
    
}
