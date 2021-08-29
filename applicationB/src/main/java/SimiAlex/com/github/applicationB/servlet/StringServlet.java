package SimiAlex.com.github.applicationB.servlet;

import SimiAlex.com.github.applicationB.dao.MessageRepository;
import SimiAlex.com.github.applicationB.dto.MessageDTO;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/"})
public class StringServlet extends HttpServlet
{
    @Inject
    private MessageRepository mr;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<MessageDTO> messages = mr.findAllMessages();
        req.getSession().setAttribute("messages", messages);
        // redirect to messages.jsp
        resp.sendRedirect("messages.jsp");
    }
}
