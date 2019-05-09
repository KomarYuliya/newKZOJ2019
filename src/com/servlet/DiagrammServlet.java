package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/diadramm")
public class DiagrammServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = new PrintWriter(response.getWriter());
        try {
            String param = request.getParameter("chart");
            response.setContentType("text/html");

            out.println("<!DOCTYPE html PUBLIC " +
                    "\"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
                    "\"http://www.w3.org/TR/html4/loose.dtd\"> ");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Статистика</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<H2>Статистика</H2>");
            out.println("<P>");
            out.println("<IMG SRC=\"generate\" BORDER=1 WIDTH=600 HEIGHT=400/>");
            out.println("</body>");
            out.println("</html>");

            out.flush();
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
        finally {
            out.close();
        }
    }
}
