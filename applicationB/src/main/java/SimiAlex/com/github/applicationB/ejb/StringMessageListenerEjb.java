package SimiAlex.com.github.applicationB.ejb;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven
public class StringMessageListenerEjb implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            String text =((TextMessage)message).getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
