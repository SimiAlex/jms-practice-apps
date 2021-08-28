package SimiAlex.com.github.applicationA.servlet;

import SimiAlex.com.github.applicationA.ejb.StringMessageProducerEjb;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/stringServlet")
public class StringServlet extends HttpServlet {

    @EJB
    private StringMessageProducerEjb stringMessageProducerEjb;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        // recover the String message from the request
        String message = req.getParameter("message");

        // send string message on queue
        // print messages from queue
        try {
            stringMessageProducerEjb.sendMessage(message);
            String messages = stringMessageProducerEjb.readMessagesFromQueue();
            resp.getWriter().write(messages);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }
}
