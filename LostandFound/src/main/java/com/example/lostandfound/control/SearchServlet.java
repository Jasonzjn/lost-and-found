package com.example.lostandfound.Control;

import com.example.lostandfound.Entity.Item;
import com.example.lostandfound.Method.ManipulateItems;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SearchServlet",value = "/Search-Servlet")
public class SearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        Item newItem = new Item();
        newItem.setType(req.getParameter("type"));
        newItem.setName(req.getParameter("name"));
        try {
            req.setAttribute("itemList",new ManipulateItems().showSelectedItems(newItem));
            req.getRequestDispatcher("main.jsp").forward(req,res);
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 ServletException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
