package SimiAlex.com.github.applicationB.ejb;

import SimiAlex.com.github.applicationB.dao.MessageRepository;
import SimiAlex.com.github.applicationB.dto.MessageDTO;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
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

    @Inject
    private MessageRepository mr;

    @Override
    public void onMessage(Message message) {
        try {
            String text =((TextMessage)message).getText();
            String id = message.getJMSMessageID();
            MessageDTO mdto = new MessageDTO();
            mdto.setMessage(text);
            mdto.setId(id);
            mr.addMessage(mdto);

            LOGGER.info(text + " " + id);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
