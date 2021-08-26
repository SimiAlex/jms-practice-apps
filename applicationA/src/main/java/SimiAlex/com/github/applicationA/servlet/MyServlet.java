package SimiAlex.com.github.applicationA.servlet;

import SimiAlex.com.github.applicationA.ejb.MessageProducerEjb;

import javax.ejb.EJB;
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
        messageProducerEjb.sendMessage("Exersam JMS");
        resp.getWriter().write("Totul este in regula");
    }
}
