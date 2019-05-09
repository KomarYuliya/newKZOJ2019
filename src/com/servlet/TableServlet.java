package com.servlet;

import com.company.DAO;
import com.company.FoodDAO;
import com.model.Food;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/table")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration=new Configuration();
        SessionFactory factory=configuration.configure().buildSessionFactory();
        DAO<Food, String> foodStringDAO=new FoodDAO(factory);
        req.setAttribute("data", foodStringDAO.getTableView("_"));
        req.getRequestDispatcher("ListWindow.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Configuration configuration=new Configuration();
        SessionFactory factory=configuration.configure().buildSessionFactory();
        DAO<Food, String> foodStringDAO=new FoodDAO(factory);
        req.setAttribute("data", foodStringDAO.getTableView(req.getParameter("foodName")));
        req.getRequestDispatcher("ListWindow.jsp").forward(req, resp);
    }
}
