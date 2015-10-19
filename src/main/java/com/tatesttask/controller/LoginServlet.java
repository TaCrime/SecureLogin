package com.tatesttask.controller;

import com.tatesttask.service.CredentialsVerifier;
import com.tatesttask.service.impl.CredentialsVerifierImpl;
import com.tatesttask.util.ApplicationContextProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
//    @Autowired CredentialsVerifier credentialsVerifier;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        CredentialsVerifier credentialsVerifier = (CredentialsVerifier)ApplicationContextProvider
                .getApplicationContext().getBean("credentialsVerifier");
        String login = StringUtils.trim(request.getParameter("login"));
        String password = StringUtils.trim(request.getParameter("password"));
        if (StringUtils.isNoneBlank(login) && StringUtils.isNoneBlank(password) &&
                credentialsVerifier.isValidCreadential(login, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            response.sendRedirect("/content/LoginSuccess.jsp");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Please make sure you enter proper Login/Pass.</font>\n");
            requestDispatcher.include(request, response);
        }
    }
}

