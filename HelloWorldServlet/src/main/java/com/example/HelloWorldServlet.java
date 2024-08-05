package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM users");
            resultSet = statement.executeQuery();

            StringBuilder usersList = new StringBuilder();
            while (resultSet.next()) {
                usersList.append("Name: ").append(resultSet.getString("name"))
                         .append(", Age: ").append(resultSet.getInt("age"))
                         .append("<br>");
            }

            request.setAttribute("usersList", usersList.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        Integer age = Integer.parseInt(ageStr);

        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("age", age);

        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello, " + name + "!</h1>");
        response.getWriter().println("<h2>Age: " + age + "</h2>");
        response.getWriter().println("<a href='hello'>Go to Welcome Page</a><br>");
        response.getWriter().println("<a href='logout'>Logout</a>");
    }

}
