package com.tatesttask.controller;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class LoginServletTest {

    private final static String MOCK_LOGIN_PASSWORD = "mockLoginPassword";

    private LoginServlet loginServlet = new LoginServlet();

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    private HttpSession session = mock(HttpSession.class);
    private PrintWriter printWriter = mock(PrintWriter.class);

    @Before
    public void setup() throws IOException {
        when(request.getParameter("login")).thenReturn(MOCK_LOGIN_PASSWORD);
        when(request.getParameter("password")).thenReturn(MOCK_LOGIN_PASSWORD);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/login.html")).thenReturn(requestDispatcher);
    }

    @Test
    public void testLoginNull() throws IOException, ServletException {
        when(request.getParameter("login")).thenReturn(null);

        loginServlet.doPost(request, response);

        testIfNotCredentialsAccepted();
    }

    @Test
    public void testLoginEmpty() throws IOException, ServletException {
        when(request.getParameter("login")).thenReturn("");

        loginServlet.doPost(request, response);

        testIfNotCredentialsAccepted();
    }

    @Test
    public void testLoginBlank() throws IOException, ServletException {
        when(request.getParameter("login")).thenReturn(" ");

        loginServlet.doPost(request, response);

        testIfNotCredentialsAccepted();
    }

    @Test
    public void testPasswordNull() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn(null);

        loginServlet.doPost(request, response);

        testIfNotCredentialsAccepted();
    }

    @Test
    public void testPasswordEmpty() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn("");

        loginServlet.doPost(request, response);

        testIfNotCredentialsAccepted();
    }

    @Test
    public void testPasswordBlank() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn(" ");

        loginServlet.doPost(request, response);

        testIfNotCredentialsAccepted();
    }

    private void testIfNotCredentialsAccepted() throws IOException, ServletException {
        verify(request, never()).getSession();
        verify(session, never()).setAttribute(anyString(), anyString());
        verify(response, never()).sendRedirect(anyString());
        verify(request, times(1)).getRequestDispatcher("/login.html");
        verify(requestDispatcher, times(1)).include(request, response);
    }

    private void testIfCredentialsAccepted(String login) throws IOException, ServletException {
        verify(request, times(1)).getSession();
        verify(session, times(1)).setAttribute("user", login);
        verify(response, times(1)).sendRedirect("/content/LoginSuccess.jsp");
        verify(request, never()).getRequestDispatcher("/login.html");
        verify(requestDispatcher, never()).include(request, response);
    }


}
