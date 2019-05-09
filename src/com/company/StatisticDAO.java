package com.company;

import com.model.Diary;
import com.model.Food;
import com.model.Statistic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class StatisticDAO implements DAO<Statistic, String> {
    private SessionFactory factory;
    public StatisticDAO(SessionFactory factory){
        this.factory=factory;
    }

    public Map<String, Statistic> getData(String login){
        String sql="From "+Statistic.class.getSimpleName();
        sql+=" where login='"+login+"'";
        Map<String, Statistic> map=new TreeMap<>();
        try(Session session=factory.openSession()){
            Query query=session.createQuery(sql);
            List<Statistic> statistics=query.list();
            for(Statistic s: statistics){
                map.put(s.getDate(), s);
            }
        }
        return map;
    }
    public void deleteMany(String login){
        String sql="From "+Statistic.class.getSimpleName();
        sql+=" where login='"+login+"'";
        try(Session session=factory.openSession()){
            Query query=session.createQuery(sql);
            List<Statistic> statistics=query.list();
            for(Statistic s: statistics){
                delete(s);
            }
        }
    }
    public boolean create(Statistic statistic){
        boolean result=false;
        Date date=new Date();
        statistic.setId(date.getTime());
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.save(statistic);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
        return result;
    }

    public void editFood(int[] data, String foodName){
        DAO<Diary, String> diaryStringDAO=new DiaryDAO(factory);
        String sql = "From " + Diary.class.getSimpleName();
        sql+=" where foodName= :paramName";
        try(Session session=factory.openSession()){
            Query query=session.createQuery(sql);
            query.setParameter("paramName", foodName);
            List<Diary> diaries = query.list();
            for(Diary d: diaries){
                sql = "From " + Statistic.class.getSimpleName();
                sql+=" where login= :paramLogin and date= :paramDate";
                query = session.createQuery(sql);
                query.setParameter("paramLogin", d.getLogin());
                query.setParameter("paramDate", d.getDate());
                List<Statistic> statistics=query.list();
                for(Statistic s: statistics){
                    double koeff=d.getSize()/100.0;
                    s.setCurrCal((int)(s.getCurrCal()-data[0]*koeff));
                    s.setCurrFats((int)(s.getCurrFats()-data[1]*koeff));
                    s.setCurrProteins((int)(s.getCurrProteins()-data[2]*koeff));
                    s.setCurrCarbohydrates((int)(s.getCurrCarbohydrates()-data[3]*koeff));
                    update(s);
                }
                if(data[5]==-1) diaryStringDAO.delete(d);
            }
        }
    }
    public String getTableView(String login){
        String result=" ";
        String sql = "From " + Statistic.class.getSimpleName();
        sql+=" where login= :paramLogin";
        try(Session session=factory.openSession()) {
            Query query = session.createQuery(sql);
            query.setParameter("paramLogin", login);
            List<Statistic> statistics = query.list();
            for(Statistic s: statistics){
                DAO<Diary, String> diaryStringDAO=new DiaryDAO(factory);
                String buff=diaryStringDAO.getTableView(s.getLogin()+"+"+s.getDate());
                if(buff.equals(""))
                {
                    delete(s);
                    continue;
                }
                result+=diaryStringDAO.getTableView(s.getLogin()+"+"+s.getDate());
                result+="\n<tr>\n" +
                        "<td>"+s.getDate()+"</td>\n" +
                        "<td colspan='2'>Итого: </td>\n" +
                        "<td>"+s.getCurrCal()+"</td>\n" +
                        "<td>"+s.getCurrFats()+"</td>\n" +
                        "<td>"+ s.getCurrProteins()+"</td>\n" +
                        "<td>"+s.getCurrCarbohydrates()+"</td>\n" +
                        "</tr>\n";
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            return result;
        }
    }
    public Statistic read(String loginAndDate) {
        String login=loginAndDate.substring(0, loginAndDate.indexOf('+'));
        String date=loginAndDate.substring(loginAndDate.indexOf('+')+1);
        String sql = "From " + Statistic.class.getSimpleName();
        sql+=" where login= :paramLogin AND date= :paramDate";
        Statistic result=new Statistic();
        try(Session session=factory.openSession()) {
            Query query=session.createQuery(sql);
            query.setParameter("paramLogin", login);
            query.setParameter("paramDate", date);
            result = (Statistic)query.getSingleResult();
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
            result.setLogin("???");
        }
        finally {
            if(result==null){
                result=new Statistic();
                result.setLogin("???");
            }
            return result;
        }
    }
    public boolean update(Statistic statistic){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.update(statistic);
            session.getTransaction().commit();
            result = true;
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
        finally {
            return result;
        }
    }
    public boolean delete(Statistic statistic){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.delete(statistic);
            session.getTransaction().commit();
            result = true;
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
        finally {
            return result;
        }
    }
}
