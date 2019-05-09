package com.servlet;

import com.SingleTone;
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

@WebServlet("/control")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SingleTone singleTone=SingleTone.getInstance("???");
        if(singleTone.getLogin().equals("???")) req.getRequestDispatcher("AutorizWindow.jsp").forward(req, resp);
        req.getRequestDispatcher("ControlWindow.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Food newFood=new Food();
        if(!req.getParameter("name").equals("")) {
            Configuration conf = new Configuration();
            SessionFactory factory = conf.configure().buildSessionFactory();
            DAO<Food, String> foodDAO=new FoodDAO(factory);
            newFood.setFoodName(req.getParameter("name"));
            newFood.setCalories(Integer.parseInt(req.getParameter("kkl")));
            newFood.setFats(Integer.parseInt(req.getParameter("fats")));
            newFood.setProtein(Integer.parseInt(req.getParameter("protein")));
            newFood.setCarbohydrates(Integer.parseInt(req.getParameter("carbs")));
            if(!foodDAO.read(newFood.getFoodName()).equals("???")) {
                foodDAO.create(newFood);
                req.setAttribute("previousOperation", "Новое блюдо успешно добавлено!");
                SingleTone singleTone=SingleTone.getInstance("username");
                req.setAttribute("userName", singleTone.getLogin());
                req.getRequestDispatcher("MainWindow.jsp").forward(req, resp);
            }
        }
        else{
            req.setAttribute("errorEmptyField", "Заполните все поля");
            req.getRequestDispatcher("ControlWindow.jsp").forward(req, resp);
        }
    }
}
