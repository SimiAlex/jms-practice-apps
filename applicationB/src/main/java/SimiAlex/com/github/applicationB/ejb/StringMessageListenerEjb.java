package SimiAlex.com.github.applicationB.ejb;

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
        @ActivationConfigProperty(propertyName = "acknowledgment", propertyValue = "Auto-acknowledgment")
})
public class StringMessageListenerEjb implements MessageListener {

    private  final Logger LOGGER = Logger.getLogger(StringMessageListenerEjb.class.toString());

    @Override
    public void onMessage(Message message) {
        try {
            String text =((TextMessage)message).getText();
            LOGGER.info(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
