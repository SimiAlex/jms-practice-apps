package SimiAlex.com.github.applicationA.ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Enumeration;

@Stateless
@LocalBean
public class StringMessageProducerEjb {

    @Resource(mappedName = "java:/queue/stringQueue")
    Queue queue;

    @Inject
    JMSContext context;

    public void sendMessage(String message) {
        context.createProducer().send(queue, message);
    }

    public String readMessagesFromQueue() throws JMSException {
        StringBuilder returnString = new StringBuilder();
        QueueBrowser queueBrowser = context.createBrowser(queue);
        Enumeration enumeration = queueBrowser.getEnumeration();

        while(enumeration.hasMoreElements()) {
            TextMessage textMessage = (TextMessage) enumeration.nextElement();
            returnString.append(textMessage.getText()).append("\n");
        }
        return returnString.toString();
    }
}
