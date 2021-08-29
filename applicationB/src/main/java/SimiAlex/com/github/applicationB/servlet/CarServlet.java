package SimiAlex.com.github.applicationB.servlet;

import SimiAlex.com.github.applicationB.dao.CrudRepository;
import SimiAlex.com.github.applicationB.dao.MessageRepository;
import SimiAlex.com.github.applicationB.dto.MessageDTO;
import SimiAlex.com.github.backend.model.Car;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/carServlet"})
public class CarServlet extends HttpServlet
{
    @Inject
    private CrudRepository<Car> carCrudRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Car> cars = carCrudRepository.findAll();
        req.getSession().setAttribute("cars", cars);
        // redirect to cars.jsp
        resp.sendRedirect("cars.jsp");
    }
}
