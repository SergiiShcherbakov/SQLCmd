package ua.com.juja.sergiishcherbakov.sqlcmd.controller.web;

import ua.com.juja.sergiishcherbakov.sqlcmd.controller.service.Service;
import ua.com.juja.sergiishcherbakov.sqlcmd.controller.service.ServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sergii Shcherbakov on 05.09.2017.
 */
public class MainServlet extends HttpServlet {
    Service service =  ServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ( service.isConnected() ) {
            req.setAttribute("commands", service.getCommandList());
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("start.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
