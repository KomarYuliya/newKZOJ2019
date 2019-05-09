package com.servlet;

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
import java.util.Enumeration;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("RegWindow.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(registration(req)==1)
                req.getRequestDispatcher("AutorizWindow.jsp").forward(req,resp);
        else {
            req.setAttribute("usernameAlreadyExists", "There is user with this username already");
            req.getRequestDispatcher("RegWindow.jsp").forward(req, resp);
        }
    }
    private int registration(HttpServletRequest req){
        SessionFactory factory=null;
        try{
            Configuration conf=new Configuration();
            factory=conf.configure().buildSessionFactory();

            DAO<User, String> userDAO=new UsersDAO(factory);
            DAO<User_data, String> user_dataDAO=new Users_dataDAO(factory);

            User user=new User();
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            user.setUsername(req.getParameter("username"));
            user.setAccessLevel(0);
            if(userDAO.read(user.getUsername()).getLogin().equals("???")!=true){
                return 0;
            }

            else {
                userDAO.create(user);

                User_data user_data = new User_data();
                user_data.setLogin(req.getParameter("login"));
                user_data.setAge(Integer.parseInt(req.getParameter("age")));
                user_data.setHeight(Integer.parseInt(req.getParameter("height")));
                user_data.setWeight(Integer.parseInt(req.getParameter("weight")));
                user_data.setNormCal(0);
                user_data.setNormProteins(0);
                user_data.setNormFats(0);
                user_data.setNormCarbohydrates(0);
                user_data.setMail(req.getParameter("email"));
                user_dataDAO.create(user_data);
            }
            return 1;

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
