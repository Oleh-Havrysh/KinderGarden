package ua.nure.havrysh.kindergarten;

import com.google.gson.Gson;
import ua.nure.havrysh.kindergarten.dao.KindergartenDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleg on 15.11.2016.
 */
public class MyServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(KindergartenDAO.getInstance().getChildren());
        resp.getWriter().print(json);

    }
}
