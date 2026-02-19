package com.controller;

import com.model.DAO.CategoriaDAO;
import com.model.DAO.HabitoDAO;
import com.model.entities.Categoria;
import com.model.entities.Habito;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CrearHabitoController")
public class EstablecerHabitoController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String nombre = req.getParameter("nombre");
    String categoria = req.getParameter("categoria");
    String descripcion = req.getParameter("descripcion");

    this.ruteador(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.ruteador(req, resp);
  }

  private void ruteador(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String ruta = (request.getParameter("ruta") != null) ? request.getParameter("ruta") : "crear";

    switch (ruta) {
      case "crear":
        this.crear(request, response);
        break;

      case "listar":
        this.obtenerTodas(request, response);
        break;

      case "guardar":
        this.guardar(request, response);
        break;

      case "aceptar":
        this.aceptar(request, response);
        break;
    }
  }

  private void obtenerTodas(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Categoria> categoria = CategoriaDAO.obtenerTodas();
    req.setAttribute("categorias", categoria);
    req.getRequestDispatcher("view/NuevoHabito.jsp").forward(req, resp);
  }

  public void crear(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    obtenerTodas(req, resp);
  }

  public void guardar(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String nombre = req.getParameter("nombre");
    String idCategoriaStr = req.getParameter("categoria");
    String descripcion = req.getParameter("descripcion");

    if (nombre == null
        || nombre.trim().isEmpty()
        || idCategoriaStr == null
        || idCategoriaStr.isEmpty()) {

      // Configuramos el mensaje de error
      req.setAttribute(
          "mensajeError",
          "Por favor, completa todos los campos obligatorios (Nombre y Categoría).");

      req.setAttribute("urlDestino", req.getContextPath() + "/CrearHabitoController?ruta=crear");

      req.getRequestDispatcher("/view/MensajeError.jsp").forward(req, resp);
      return;
    }

    try {
      int idCategoria = Integer.parseInt(idCategoriaStr);

      String mensaje = "¡Hábito creado correctamente!";
      String destino = req.getContextPath() + "/CrearHabitoController?ruta=crear";
      String mensajeEnc =
          java.net.URLEncoder.encode(mensaje, java.nio.charset.StandardCharsets.UTF_8);
      String destinoEnc =
          java.net.URLEncoder.encode(destino, java.nio.charset.StandardCharsets.UTF_8);

      Habito nuevoHabito = new Habito();
      nuevoHabito.setNombre(nombre);
      nuevoHabito.setDescripcion(descripcion);
      nuevoHabito.setFechaInicio(new java.util.Date());
      nuevoHabito.setFrecuencia(0);

      HabitoDAO.guardar(nuevoHabito, idCategoria);

      resp.sendRedirect(
          req.getContextPath()
              + "/view/MensajeGuardado.jsp?mensaje="
              + mensajeEnc
              + "&urlDestino="
              + destinoEnc);
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("view/MensajeError.jsp");
    }
  }

  private void aceptar(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.sendRedirect(req.getContextPath() + "/CrearHabitoController?ruta=crear");
  }
}
