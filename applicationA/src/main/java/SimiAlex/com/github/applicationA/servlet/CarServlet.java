package SimiAlex.com.github.applicationA.servlet;

import SimiAlex.com.github.applicationA.ejb.ObjectMessageProducer;
import SimiAlex.com.github.backend.model.Car;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/carServlet")
public class CarServlet extends HttpServlet {

    @Inject
    private ObjectMessageProducer objectMessageProducer;

    @Resource(mappedName = "java:/queue/carQueue")
    private Queue queue;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Car receivedCar = new Car();
        receivedCar.setId(Long.valueOf(req.getParameter("id")));
        receivedCar.setMake(req.getParameter("make"));
        receivedCar.setModel(req.getParameter("model"));
        receivedCar.setYear(Integer.parseInt(req.getParameter("year")));

        //send Car object
        objectMessageProducer.sendObject(receivedCar, queue);

    }
}
