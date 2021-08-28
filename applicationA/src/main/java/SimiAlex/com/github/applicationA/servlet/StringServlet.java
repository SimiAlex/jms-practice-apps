package SimiAlex.com.github.applicationA.servlet;

import SimiAlex.com.github.applicationA.ejb.StringMessageProducerEjb;
import javax.ejb.EJB;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //create car object with form parameters
        String message = req.getParameter("message");

        //send string on queue
        stringMessageProducerEjb.sendMessage(message);

        //print messages from queue
//        String messages = null;
//        try {
//            messages = stringMessageProducerEjb.readMessagesFromQueue();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//
//        resp.getWriter().write(messages);
    }
}
