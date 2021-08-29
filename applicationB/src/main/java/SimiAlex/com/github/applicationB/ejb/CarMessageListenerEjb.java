package SimiAlex.com.github.applicationB.ejb;

import SimiAlex.com.github.backend.model.Car;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/carQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgment", propertyValue = "Auto-acknowledgment")
})
public class CarMessageListenerEjb implements MessageListener {

    private final Logger LOGGER = Logger.getLogger(CarMessageListenerEjb.class.toString());
//    @Inject
//    private MessageRepository mr;

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Car receivedCar = objectMessage.getBody(Car.class);
            LOGGER.info("--------------------------------------................................");
            LOGGER.info(receivedCar.toString());
            LOGGER.info("--------------------------------------................................");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
