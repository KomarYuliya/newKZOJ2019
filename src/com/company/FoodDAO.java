package com.company;

import com.model.Food;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class FoodDAO implements DAO<Food, String> {
    private SessionFactory factory;
    public FoodDAO(SessionFactory factory){
        this.factory=factory;
    }
    public boolean create(Food food){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.save(food);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
        return result;
    }

    public Food read(String foodName) {
        Food result=new Food();

        try(Session session=factory.openSession()){
            result=session.get(Food.class, foodName);
        }
        catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
        finally {
            if(result==null){
                result=new Food();
                result.setFoodName("???");
                return result;
            }
            return result;
        }
    }
    public boolean update(Food food){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.update(food);
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

    @Override
    public String getTableView(String data) {
        String sql="From "+Food.class.getSimpleName();
        String result="";
        if(!data.equals("") && !data.equals("_")){
            sql+=" where foodName='"+data+"'";
        }
        try(Session session=factory.openSession()){
            Query query=session.createQuery(sql);
            List<Food> foods=query.list();
            for(Food f: foods){
                result+="\n<tr>\n<td>"+f.getFoodName()+"</td>\n";
                result+="<td>"+f.getCalories()+"</td>\n";
                result+="<td>"+f.getFats()+"</td>\n";
                result+="<td>"+f.getProtein()+"</td>\n";
                result+="<td>"+f.getCarbohydrates()+"</td>\n";
                if(data.equals("")) {
                    result += "<td><input type='checkbox' value='" + f.getFoodName() + "' name='toDelete'></td>\n";
                }
                result+="</tr>\n";
            }
        }
        return result;
    }

    public boolean delete(Food food){
        boolean result=false;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            session.delete(food);
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
