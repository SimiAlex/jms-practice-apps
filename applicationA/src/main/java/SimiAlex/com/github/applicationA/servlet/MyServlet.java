package SimiAlex.com.github.applicationA.servlet;

import SimiAlex.com.github.applicationA.ejb.MessageProducerEjb;
import SimiAlex.com.github.applicationA.model.Car;
import jakarta.xml.bind.JAXBException;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/car-form/process")
public class MyServlet extends HttpServlet {

    @EJB
    private MessageProducerEjb messageProducerEjb;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //send message
        try {
            messageProducerEjb.sendMessage(new Car("dacia", "sandero", "2021"));
        } catch (JAXBException | JMSException e) {
            e.printStackTrace();
        }

        //read messages
        readMessagesFromQueue(resp);

    }

    private void readMessagesFromQueue(HttpServletResponse resp) throws IOException {
        String result = null;
        try {
            result = messageProducerEjb.readMessagesFromQueue();
            resp.getWriter().write(result);
        } catch (JMSException | JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //create car object with form parameters
        String make = req.getParameter("make");
        String model = req.getParameter("model");
        String year = req.getParameter("year");
        Car car = new Car(make, model, year);

        //send Car object
        try {
            messageProducerEjb.sendMessage(car);
        } catch (JAXBException | JMSException e) {
            e.printStackTrace();
        }

        readMessagesFromQueue(resp);
    }
}
