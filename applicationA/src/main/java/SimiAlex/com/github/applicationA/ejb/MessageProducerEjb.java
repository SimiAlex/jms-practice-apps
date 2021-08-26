package SimiAlex.com.github.applicationA.ejb;

import SimiAlex.com.github.applicationA.model.Car;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;

@Stateless
@LocalBean
public class MessageProducerEjb {

    @Resource(mappedName = "java:/queue/myQueue")
    Queue myQueue;

    @Inject
    JMSContext context;

    public void sendMessage(Car car) throws JAXBException, JMSException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Car.class);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Marshaller marshaller = jaxbContext.createMarshaller();
        //transformare in xml
        marshaller.marshal(car, baos);

        BytesMessage msg = context.createBytesMessage();
        msg.writeBytes(baos.toByteArray());

        JMSProducer producer = context.createProducer();
        producer.send(myQueue, msg);
    }

    public String readMessagesFromQueue() throws JMSException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Car.class);

        QueueBrowser myQueueBrowser = context.createBrowser(myQueue);
        Enumeration messageEnumeration = myQueueBrowser.getEnumeration();
        StringBuilder sb = new StringBuilder();
        while (messageEnumeration.hasMoreElements()) {
            Message msg = (Message) messageEnumeration.nextElement();
            if(msg instanceof TextMessage)
            {
                TextMessage textMessage = (TextMessage) msg;
                sb.append(textMessage.getText()).append("\n");
            }
            else if(msg instanceof BytesMessage){
                BytesMessage bytesMessage = (BytesMessage) msg;
                byte[] content = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(content);

                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Car msgDoc = (Car) unmarshaller.unmarshal(new ByteArrayInputStream(content));
                sb.append(msgDoc.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}