package SimiAlex.com.github.applicationB.ejb;

import SimiAlex.com.github.applicationB.dao.CrudRepository;
import SimiAlex.com.github.backend.model.Car;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/carQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgment", propertyValue = "Auto-acknowledgment")
})
public class CarMessageListenerEjb implements MessageListener {

    @Inject
    private CrudRepository<Car> carCrudRepository;

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Car receivedCar = objectMessage.getBody(Car.class);
            carCrudRepository.add(receivedCar);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
