package com.company;

import com.model.User;
import com.model.User_data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class UsersDAO implements DAO<User, String> {
    private SessionFactory factory;

    @Override
    public String getTableView(String data) {
        String sql="From "+User.class.getSimpleName();
        String result="";
        if(!data.equals("")){
            sql+=" where login='"+data+"'";
        }
        DAO<User_data, String> user_dataStringDAO=new Users_dataDAO(factory);
        try(Session session=factory.openSession()){
            Query query=session.createQuery(sql);
            List<User> list=query.list();
            for(User l:list){
                result+="\n<tr>\n<td>"+l.getLogin()+"</td>\n";
                result+="<td>"+l.getPassword()+"</td>\n";
                result+="<td>"+l.getUsername()+"</td>\n";
                result+=user_dataStringDAO.getTableView(l.getLogin())+"\n";
                result+="<td>"+l.accessLevel()+"</td>\n";
                result+="<td><input type='checkbox' value='"+l.getLogin()+"' name='toDelete'></td>\n</tr>\n";
            }
        }
        return result;
    }

    public UsersDAO(SessionFactory factory){
        this.factory=factory;
    }
    public boolean create(User user){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
        return result;
    }

    public User read(String login) {
        User result=new User();

        try(Session session=factory.openSession()){
            result=session.get(User.class, login);
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
        finally {
            if(result==null){
                result=new User();
                result.setLogin("???");
                return result;
            }
            return result;
        }
    }
    public boolean update(User user){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.update(user);
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
    public boolean delete(User user){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.delete(user);
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
