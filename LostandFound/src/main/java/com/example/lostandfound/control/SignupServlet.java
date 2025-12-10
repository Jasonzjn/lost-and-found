package com.example.lostandfound.Control;

import com.example.lostandfound.Method.DataBankAccess;
import com.example.lostandfound.Method.Encryption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(name = "SignupServlet",value = "/Signup-Servlet")
public class SignupServlet extends HttpServlet{
    public void init() throws ServletException {
        super.init();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re-password");
        if(!password.equals(rePassword)){
            req.setAttribute("error","Password not identical");
            req.getRequestDispatcher("signup.jsp").forward(req,res);
        }
        String encPassword = new Encryption().encrypt(password);
        try {
            //lack user validation
            new DataBankAccess().input("user",username,encPassword);
            req.getRequestDispatcher("login.jsp").forward(req,res);
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
