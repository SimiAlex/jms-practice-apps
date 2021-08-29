package SimiAlex.com.github.applicationA.ejb;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import java.io.Serializable;

@ApplicationScoped
public class ObjectMessageProducer {

    @Inject
    private JMSContext jmsContext;

    public <T extends Serializable> void sendObject(T object, Destination destination){
        ObjectMessage objectMessage = jmsContext.createObjectMessage(object);
        jmsContext.createProducer().send(destination, objectMessage);
    }
}
