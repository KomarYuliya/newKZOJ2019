package com.servlet;

import com.SingleTone;
import com.company.DAO;
import com.company.DiaryDAO;
import com.company.FoodDAO;
import com.company.StatisticDAO;
import com.model.Diary;
import com.model.Food;
import com.model.Statistic;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redact")
public class AdminFoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SingleTone singleTone=SingleTone.getInstance("???");
        if(singleTone.getLogin().equals("???") || singleTone.getAccessLevel()==0) req.getRequestDispatcher("AutorizWindow.jsp").forward(req, resp);
        else {
            Configuration configuration=new Configuration();
            SessionFactory factory=configuration.configure().buildSessionFactory();
            DAO<Food, String> foodStringDAO=new FoodDAO(factory);
            req.setAttribute("tableFood", foodStringDAO.getTableView(""));
            req.getRequestDispatcher("AdminFoodControlWindow.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Configuration conf = new Configuration();
        SessionFactory factory = conf.configure().buildSessionFactory();
        Diary diary=new Diary();
        DAO<Diary, String> diaryDAO=new DiaryDAO(factory);
        DAO<Food, String> foodDAO=new FoodDAO(factory);
        DAO<Statistic, String> statisticDAO=new StatisticDAO(factory);
        String foodName=req.getParameter("foodName");

        if(!foodName.equals("")){
            Food food=foodDAO.read(foodName);
            if(food.getFoodName().equals("???")){
                food.setFoodName(req.getParameter("foodName"));
                food.setCalories(Integer.parseInt(req.getParameter("kk")));
                food.setCalories(Integer.parseInt(req.getParameter("fats")));
                food.setCalories(Integer.parseInt(req.getParameter("prot")));
                food.setCalories(Integer.parseInt(req.getParameter("carboh")));
                foodDAO.create(food);
            }
            else{
                int[] diff=new int[6];
                diff[5]=1;
                if(food.getCalories()!=Integer.parseInt(req.getParameter("kk")))
                    diff[0]=food.getCalories()-Integer.parseInt(req.getParameter("kk"));
                if(food.getFats()!=Integer.parseInt(req.getParameter("fats")))
                    diff[1]=food.getFats()-Integer.parseInt(req.getParameter("fats"));
                if(food.getProtein()!=Integer.parseInt(req.getParameter("prot")))
                    diff[2]=food.getProtein()-Integer.parseInt(req.getParameter("prot"));
                if(food.getCarbohydrates()!=Integer.parseInt(req.getParameter("carboh")))
                    diff[3]=food.getCarbohydrates()-Integer.parseInt(req.getParameter("carboh"));
                ((StatisticDAO) statisticDAO).editFood(diff, req.getParameter("foodName"));
            }
        }
        String[] checkes=req.getParameterValues("toDelete");
        if(checkes!=null){
            for(int i=0;i<checkes.length;i++){
                Food food=foodDAO.read(checkes[i]);
                int diff[]=new int[6];
                diff[5]=-1;
                diff[0]=food.getCalories();
                diff[1]=food.getFats();
                diff[2]=food.getProtein();
                diff[3]=food.getCarbohydrates();
                ((StatisticDAO) statisticDAO).editFood(diff, req.getParameter("toDelete"));
                foodDAO.delete(food);
            }
        }
        doGet(req, resp);
    }
}
