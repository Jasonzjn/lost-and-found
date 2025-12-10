package com.example.lostandfound.Control;

import com.example.lostandfound.Method.ManipulateItems;
import com.example.lostandfound.Entity.Item;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NewItemServlet",value = "/NewItem-Servlet")
public class NewItemServlet extends HttpServlet {
     public void doPost(HttpServletRequest req, HttpServletResponse res){
         Item newItem = new Item();
         newItem.setType(req.getParameter("type"));
         newItem.setName(req.getParameter("name"));
         newItem.setContent(req.getParameter("content"));
         newItem.setContact(req.getParameter("contact"));
         new ManipulateItems().addItem(newItem);
     }
}
