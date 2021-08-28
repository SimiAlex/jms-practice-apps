package SimiAlex.com.github.applicationB.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/stringQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class MdbStringReader implements MessageListener {

    private static Logger LOGGER = Logger.getLogger(MdbStringReader.class.toString());

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                String text = ((TextMessage)message).getText();
                LOGGER.info(text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
