package com.tatesttask.controller;

import com.tatesttask.util.PasswordStrengths;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class PasswordStrenghtServletTest {

    private PasswordStrenghtServlet passwordStrenghtServlet = new PasswordStrenghtServlet();

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private PrintWriter printWriter = mock(PrintWriter.class);

    @Before
    public void setup() throws IOException {
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void testPasswordNull() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn(null);

        passwordStrenghtServlet.doGet(request, response);

        verify(printWriter, times(1)).write(PasswordStrengths.NONE.getValue());
    }

    @Test
    public void testPasswordEmpty() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn("");

        passwordStrenghtServlet.doGet(request, response);

        verify(printWriter, times(1)).write(PasswordStrengths.WEAK.getValue());
    }

    @Test
    public void testPasswordBigBlank() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn("             ");

        passwordStrenghtServlet.doGet(request, response);

        verify(printWriter, times(1)).write(PasswordStrengths.WEAK.getValue());
    }

    @Test
    public void testPasswordWeak() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn("1");

        passwordStrenghtServlet.doGet(request, response);

        verify(printWriter, times(1)).write(PasswordStrengths.WEAK.getValue());
    }

    @Test
    public void testPasswordMedium() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn("11111");

        passwordStrenghtServlet.doGet(request, response);

        verify(printWriter, times(1)).write(PasswordStrengths.MEDIUM.getValue());
    }

    @Test
    public void testPasswordStrong() throws IOException, ServletException {
        when(request.getParameter("password")).thenReturn("11111111111");

        passwordStrenghtServlet.doGet(request, response);

        verify(printWriter, times(1)).write(PasswordStrengths.STRONG.getValue());
    }

}
