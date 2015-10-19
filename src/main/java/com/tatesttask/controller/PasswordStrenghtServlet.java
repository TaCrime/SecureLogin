package com.tatesttask.controller;

import com.tatesttask.service.CredentialsVerifier;
import com.tatesttask.service.PasswordStrengthService;
import com.tatesttask.util.ApplicationContextProvider;
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

    private PasswordStrengthService passwordStrengthService = (PasswordStrengthService) ApplicationContextProvider
            .getApplicationContext().getBean("passwordStrengthService");

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        String password = request.getParameter("password");
        response.setContentType("text/html");
        response.getWriter().write(passwordStrengthService.getPasswordStrength(StringUtils.trim(password)));
    }
}
