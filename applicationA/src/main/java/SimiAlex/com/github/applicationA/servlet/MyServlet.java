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

@WebServlet(urlPatterns = "/")
public class MyServlet extends HttpServlet {

    @EJB
    private MessageProducerEjb messageProducerEjb;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //send message
        try {
            messageProducerEjb.sendMessage(new Car("dacia", "sandero", "2021"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //read messages
        String result = null;
        try {
            result = messageProducerEjb.readMessagesFromQueue();
            resp.getWriter().write(result);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
