package com.tatesttask.controller;

import com.tatesttask.model.Credential;
import com.tatesttask.service.RegisterService;
import com.tatesttask.service.impl.RegisterServiceImpl;
import com.tatesttask.util.ApplicationContextProvider;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private RegisterService registerService = (RegisterService)ApplicationContextProvider
            .getApplicationContext().getBean("registerService");

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String output = "";
        try {
            output = tryToRegister(request.getParameter("login"), request.getParameter("password"));
        } catch (Exception e) {
            output = buildHTMLResponse(false, e.getMessage());
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registration.html");
        PrintWriter out = response.getWriter();
        out.println(output);
        requestDispatcher.include(request, response);
    }

    private String tryToRegister(String login, String password) throws Exception {
        if (StringUtils.isBlank(login)) {
            throw new Exception("Empty login");
        }
        if (StringUtils.isBlank(password)) {
            throw new Exception("Empty password");
        }
        Credential credential = new Credential(login, password);
        boolean result = registerService.register(credential);
        return buildHTMLResponse(result);
    }

    private String buildHTMLResponse(boolean result) {
        return buildHTMLResponse(result, "");
    }

    private String buildHTMLResponse(boolean result, String reason ) {
        if (result) {
            return String.format("<font color=%s>Registered %s.%s</font>\n", "green","successfully", reason);
        } else {
            return String.format("<font color=%s>Registered %s.%s</font>\n", "red","unsuccessfully", reason);
        }
    }
}
