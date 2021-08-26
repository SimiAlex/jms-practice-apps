package SimiAlex.com.github.applicationA.ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
@LocalBean
public class MessageProducerEjb {

    @Resource(mappedName = "java:/queue/myQueue")
    Queue myQueue;

    @Inject
    JMSContext context;

    public void sendMessage(String message){
        context.createProducer().send(myQueue, message);
    }

}