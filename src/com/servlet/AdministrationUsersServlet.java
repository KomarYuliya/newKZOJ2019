package com.servlet;

import com.SingleTone;
import com.company.*;
import com.model.Diary;
import com.model.User;
import com.model.User_data;
import com.model.Statistic;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administration")
public class AdministrationUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SingleTone singleTone=SingleTone.getInstance("???");
        if(singleTone.getLogin().equals("???")) req.getRequestDispatcher("AutorizWindow.jsp").forward(req, resp);
        else {
            Configuration configuration=new Configuration();
            SessionFactory factory=configuration.configure().buildSessionFactory();
            DAO<User, String> userStringDAO=new UsersDAO(factory);
            req.setAttribute("tableUsers", userStringDAO.getTableView(""));
            req.getRequestDispatcher("AdminUsersControlWindow.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory=null;
        SingleTone singleTone=SingleTone.getInstance("???");
        try {
            Configuration conf = new Configuration();
            factory = conf.configure().buildSessionFactory();

            DAO<User, String> userDAO = new UsersDAO(factory);
            DAO<User_data, String> user_dataDAO = new Users_dataDAO(factory);
            DAO<Diary, String> diaryStringDAO=new DiaryDAO(factory);
            DAO<Statistic, String> statisticStringDAO=new StatisticDAO(factory);
            String[] checked=req.getParameterValues("toDelete");
            if(checked!=null) {
                for (int i = 0; i < checked.length; i++) {
                    User user = userDAO.read(checked[i]);
                    User_data user_data=user_dataDAO.read(checked[i]);
                    if(user.getAccessLevel()!=0 && singleTone.getAccessLevel()!=2){
                        req.setAttribute("errorWrongRights", "Вы не можете удалять пользователей своего уровня и выше");
                    }
                    else {
                        ((DiaryDAO) diaryStringDAO).deleteMany(user.getLogin());
                        ((StatisticDAO) statisticStringDAO).deleteMany(user.getLogin());
                        user_dataDAO.delete(user_data);
                        userDAO.delete(user);
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            doGet(req, resp);
        }
    }
}
