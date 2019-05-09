package com;

import com.company.*;
import com.model.Diary;
import com.model.Statistic;
import com.model.User;
import com.model.User_data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Main {
    public static void main(String[] args) throws ParseException {
        String string = "2019-07-09";
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date date = format.parse(string);
        System.out.println(date);
    }
}
