package com.servlet;

import com.SingleTone;
import com.company.DAO;
import com.company.UsersDAO;
import com.company.Users_dataDAO;
import com.model.User;
import com.model.User_data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter("login");
        String password=req.getParameter("password");
        String username=req.getParameter("username");
        String age=req.getParameter("age");
        String email=req.getParameter("email");
        String height=req.getParameter("height");
        String weight=req.getParameter("weight");
        SessionFactory factory=null;
        try {
            Configuration conf = new Configuration();
            factory = conf.configure().buildSessionFactory();
            DAO<User, String> userDAO = new UsersDAO(factory);
            DAO<User_data, String> user_dataDAO = new Users_dataDAO(factory);
            SingleTone singleTone=SingleTone.getInstance("");

            User user=userDAO.read(singleTone.getLogin());
            if(!login.equals("")) user.setLogin(login);
            if(!password.equals("")) user.setPassword(password);
            if(!username.equals("")) user.setUsername(username);

            User_data user_data=user_dataDAO.read(singleTone.getLogin());
            User_data buf=user_data;
            if(!login.equals("")) user_data.setLogin(login);
            if(!age.equals("")) user_data.setAge(Integer.parseInt(age));
            if(!email.equals("")) user_data.setMail(email);
            if(!height.equals("")) user_data.setHeight(Double.parseDouble(height));
            if(!weight.equals("")) user_data.setWeight(Double.parseDouble(weight));

            if(!user_dataDAO.update(user_data)){
                req.setAttribute("errorCannotSaveChanges", "Ошибка");
            }
            else {
                if (!userDAO.update(user)) {
                    user_dataDAO.update(buf);
                    req.setAttribute("errorCannotSaveChanges", "Ошибка");
                } else {
                    singleTone.setLogin(user.getLogin());
                    req.setAttribute("userName", singleTone.getLogin());
                    req.getRequestDispatcher("MainWindow.jsp").forward(req, resp);
                }
            }

        }
        catch (Exception e){

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SingleTone singleTone = SingleTone.getInstance("???");
        if (singleTone.getLogin().equals("???")) req.getRequestDispatcher("AutorizWindow.jsp").forward(req, resp);
        else {
            Configuration conf = new Configuration();
            SessionFactory factory = null;
            factory = conf.configure().buildSessionFactory();
            DAO<User, String> userDAO = new UsersDAO(factory);
            DAO<User_data, String> user_dataDAO = new Users_dataDAO(factory);
            User user = userDAO.read(singleTone.getLogin());
            User_data user_data = user_dataDAO.read(singleTone.getLogin());
            String result = "<p>Логин: " + user.getLogin() + "</p>" +
                    "<p>Пароль: " + user.getPassword() + "</p>\n" +
                    "<p>Имя пользователя: " + user.getUsername() + "</p>\n" +
                    "<p>Возраст: " + user_data.getAge() + "</p>\n" +
                    "<p>E-mail: " + user_data.getMail() + "</p>\n" +
                    "<p>Рост: " + user_data.getHeight() + "</p>\n" +
                    "<p>Вес: " + user_data.getWeight() + "</p>\n";
            req.setAttribute("userData", result);
            req.getRequestDispatcher("ChangeWindow.jsp").forward(req, resp);
        }
    }
}
