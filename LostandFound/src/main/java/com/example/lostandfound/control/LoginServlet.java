package com.example.lostandfound.Control;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.lostandfound.Method.DataBankAccess;
import com.example.lostandfound.Method.Encryption;
import com.example.lostandfound.Method.ManipulateItems;
import com.example.lostandfound.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "LoginServlet",value = "/Login-Servlet")
public class LoginServlet extends HttpServlet{
    public void init() throws ServletException {
        super.init();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res){
        User newuser = new User();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String encPassword = new Encryption().encrypt(password);
        try {
            List<Map> list = new DataBankAccess().query("user",true,"password","=",encPassword,"username");
            for(Map unit : list){
                if(unit.containsValue(username)){
                    req.getSession().setAttribute("username",username);
                    req.setAttribute("itemList",new ManipulateItems().showAllItems());
                    req.getRequestDispatcher("main.jsp").forward(req,res);
                    break;
                }
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}


