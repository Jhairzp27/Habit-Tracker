package com.controller;

import com.model.DAO.UsuarioDAO;
import com.model.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

public class LoginController extends HttpServlet {
  @Serial private static final long serialVersionUID = 1L;
  private final UsuarioDAO usuarioDAO;

  public LoginController() {
    super();
    this.usuarioDAO = new UsuarioDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Redirigir a la página de login
    request.getRequestDispatcher("/view/Login.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String correoElectronico = request.getParameter("correoElectronico");
    String password = request.getParameter("password");

    // Validar que los campos no estén vacíos
    if (correoElectronico == null
        || correoElectronico.trim().isEmpty()
        || password == null
        || password.trim().isEmpty()) {
      request.setAttribute("error", "Por favor, complete todos los campos");
      request.getRequestDispatcher("/view/Login.jsp").forward(request, response);
      return;
    }

    // Autenticar usuario
    Usuario usuario = usuarioDAO.autenticar(correoElectronico.trim(), password);

    if (usuario != null) {
      // Crear sesión
      HttpSession session = request.getSession(true);
      session.setAttribute("usuarioId", usuario.getIdUsuario());
      session.setAttribute("usuarioNombre", usuario.getNombre());
      session.setAttribute("usuarioApellido", usuario.getApellido());
      session.setAttribute("usuarioCorreo", usuario.getCorreoElectronico());
      session.setAttribute("usuarioRol", usuario.getRol());

      // Redirigir según el rol
      if ("admin".equalsIgnoreCase(usuario.getRol())) {
        response.sendRedirect(request.getContextPath() + "/view/DashboardAdmin.jsp");
      } else {
        response.sendRedirect(request.getContextPath() + "/view/GestionarHabitos.jsp");
      }
    } else {
      // Credenciales incorrectas
      request.setAttribute("error", "Correo electrónico o contraseña incorrectos");
      request.getRequestDispatcher("/view/Login.jsp").forward(request, response);
    }
  }
}
