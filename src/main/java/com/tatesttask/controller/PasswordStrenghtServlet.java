package com.tatesttask.controller;

import com.tatesttask.util.PasswordStrengths;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PasswordStrenghtServlet")
public class PasswordStrenghtServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        String password = request.getParameter("password");
        response.setContentType("text/html");
        response.getWriter().write(checkPasswordStrehgth(StringUtils.trim(password)));
    }

    private String checkPasswordStrehgth(String password) {
        if (password == null) {
            return PasswordStrengths.NONE.getValue();
        } else if (password.length() <= 4) {
            return PasswordStrengths.WEAK.getValue();
        } else if (password.length() < 8) {
            return PasswordStrengths.MEDIUM.getValue();
        } else {
            return PasswordStrengths.STRONG.getValue();
        }
    }
}
