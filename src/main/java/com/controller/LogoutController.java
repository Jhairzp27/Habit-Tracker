package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LogoutController() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Invalidar la sesión actual
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    // Redirigir al login
    response.sendRedirect(request.getContextPath() + "/LoginController");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
