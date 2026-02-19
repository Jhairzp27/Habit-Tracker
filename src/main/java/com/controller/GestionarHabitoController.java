package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Servlet implementation class GestionarHabitoController */
@WebServlet("/GestionarHabitoController")
public class GestionarHabitoController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.ruteador(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.ruteador(request, response);
  }

  private void ruteador(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String ruta = (request.getParameter("ruta") != null) ? request.getParameter("ruta") : "iniciar";

    switch (ruta) {
      case "iniciar":
        this.iniciar(request, response);
        break;

      case "crear":
        this.crearHabito(request, response);
        break;

      case "planificar":
        this.planificarHabito(request, response);
        break;

      case "modificar":
        this.modificarHabito(request, response);
        break;

      case "eliminar":
        this.eliminarHabito(request, response);
        break;
    }
  }

  private void crearHabito(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //		Obtener parametros
    //		Ingresar al modelo
    //		Llamar vista
    // response.sendRedirect("view/NuevoHabito.jsp");
  }

  private void planificarHabito(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //		Obtener parametros
    //		Ingresar al modelo
    //		Llamar vista
  }

  private void modificarHabito(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //		Obtener parametros
    //		Ingresar al modelo
    //		Llamar vista
  }

  private void eliminarHabito(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //		Obtener parametros
    //		Ingresar al modelo
    //		Llamar vista
  }

  private void iniciar(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //		Llamar vista
    response.sendRedirect("view/GestionarHabitos.jsp");
  }
}
