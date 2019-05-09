package com.servlet;

import com.SingleTone;
import com.company.DAO;
import com.company.StatisticDAO;
import com.company.Users_dataDAO;
import com.model.Statistic;
import com.model.User_data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DiaryViewer {
    public void main(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Configuration conf = new Configuration();
        SessionFactory factory = conf.configure().buildSessionFactory();
        DAO<User_data, String> users_dataDAO=new Users_dataDAO(factory);
        DAO<Statistic, String> statisticDAO=new StatisticDAO(factory);
        SingleTone singleTone=SingleTone.getInstance("login");
        User_data user_data=users_dataDAO.read(singleTone.getLogin());
        req.setAttribute("norms", "\n" +
                "            <td>Калории: "+user_data.getNormCal()+"</td>\n" +
                "            <td>Жиры: "+user_data.getNormFats()+"</td>\n" +
                "            <td>Белки: "+user_data.getNormProteins()+"</td>\n" +
                "            <td>Углеводы: "+user_data.getNormCarbohydrates()+"</td>");
        req.setAttribute("data", statisticDAO.getTableView(singleTone.getLogin()));
        req.getRequestDispatcher("DiaryWindow.jsp").forward(req, resp);
    }
}
