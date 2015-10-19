package com.tatesttask.controller;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    private final static String MOCK_LOGIN_PASSWORD = "mockLoginPassword";
    private final static String FAIL_MESSAGE = "<font color=red>Registered unsuccessfully.%s</font>\n";
    private final static String SUCCESS_MESSAGE = "<font color=green>Registered successfully.%s</font>\n";

    private RegistrationServlet registrationServlet = new RegistrationServlet();

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    private PrintWriter printWriter = mock(PrintWriter.class);

    @Before
    public void setup() throws IOException {
        when(request.getParameter("login")).thenReturn(MOCK_LOGIN_PASSWORD);
        when(request.getParameter("password")).thenReturn(MOCK_LOGIN_PASSWORD);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getRequestDispatcher("/registration.html")).thenReturn(requestDispatcher);
    }

    @Test
    public void testLoginNull() throws IOException, ServletException {
        when(request.getParameter("login")).thenReturn(null);

        registrationServlet.doPost(request, response);

        verify(printWriter,times(1)).println(String.format(FAIL_MESSAGE,"Empty login"));
    }

    @Test
    public void testPasswordNull() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn(null);

        registrationServlet.doPost(request, response);

        verify(printWriter,times(1)).println(String.format(FAIL_MESSAGE,"Empty password"));
    }

}
