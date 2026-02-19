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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/ModificarHabitoController")
public class ModificarHabitoController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.ruteador(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.ruteador(req, resp);
  }

  private void ruteador(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // CORRECCIÓN 1: Si no hay ruta, vamos a "listar" por defecto.
    String ruta = (request.getParameter("ruta") != null) ? request.getParameter("ruta") : "listar";

    switch (ruta) {
      case "listar":
        this.listar(request, response);
        break;

      case "cargar":
        this.cargar(request, response);
        break;

      case "guardar":
        this.guardar(request, response);
        break;

      default:
        this.listar(request, response);
        break;
    }
  }

  /** RUTA "listar": Muestra la tabla/tarjetas con todos los hábitos disponibles para editar. */
  private void listar(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      // 1. LLAMAMOS AL MÉTODO QUE YA TIENES EN TU DAO
      // Este método trae TODOS los hábitos creados, estén planificados o no.
      List<Habito> lista = HabitoDAO.listarTodos();

      // 2. Guardamos la lista en la "mochila" (request) para la vista
      req.setAttribute("listaHabitos", lista);

      // 3. Enviamos a la vista que muestra la TABLA/LISTA
      // IMPORTANTE: Asegúrate de que este JSP sea el que tiene el c:forEach
      req.getRequestDispatcher("/view/ModificarHabito.jsp").forward(req, resp);

    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("view/MensajeError.jsp");
    }
  }

  /**
   * RUTA "cargar": Busca un hábito por ID y sus categorías para llenar el formulario de edición.
   */
  private void cargar(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String idStr = req.getParameter("id");

      if (idStr == null || idStr.isEmpty()) {
        // Si no hay ID, volvemos a la lista
        resp.sendRedirect(req.getContextPath() + "/ModificarHabitoController?ruta=listar");
        return;
      }

      int id = Integer.parseInt(idStr);

      // 1. Buscamos el hábito
      Habito habito = HabitoDAO.buscarPorId(id);

      // 2. Buscamos las categorías (para el <select>)
      List<Categoria> categorias = CategoriaDAO.obtenerTodas();

      req.setAttribute("habito", habito);
      req.setAttribute("categorias", categorias);

      // CORRECCIÓN 3: Redirige a un JSP DISTINTO, que es el formulario con los inputs.
      // Asegúrate de crear este archivo o renombrar el que tengas.
      req.getRequestDispatcher("/view/FormularioModificar.jsp").forward(req, resp);

    } catch (Exception e) {
      e.printStackTrace();
      req.setAttribute("mensajeError", "Error al cargar los datos del hábito.");
      req.getRequestDispatcher("/view/MensajeError.jsp").forward(req, resp);
    }
  }

  /** RUTA "guardar": Recibe los datos del formulario, valida y actualiza en BD. */
  private void guardar(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      // 1. Validar datos (si falla, el método se encarga de redirigir)
      if (!validarDatos(req, resp)) {
        return;
      }

      // 2. Recoger datos
      int id = Integer.parseInt(req.getParameter("id"));
      String nombre = req.getParameter("nombre");
      int idCategoria = Integer.parseInt(req.getParameter("categoria"));
      String descripcion = req.getParameter("descripcion");

      // 3. Actualizar objeto
      Habito habito = HabitoDAO.buscarPorId(id);
      habito.setNombre(nombre);
      habito.setDescripcion(descripcion);

      // Asignar categoría (Objeto completo)
      Categoria cat = new Categoria();
      cat.setIdCategoria(idCategoria);
      habito.setCategoria(cat);

      // 4. Guardar en BD
      HabitoDAO.actualizar(habito);

      // 5. Redirigir a Éxito
      String mensaje = "Hábito modificado correctamente.";
      String mensajeEncoded = URLEncoder.encode(mensaje, StandardCharsets.UTF_8);
      resp.sendRedirect(
          req.getContextPath() + "/view/MensajeGuardado.jsp?mensaje=" + mensajeEncoded);

    } catch (Exception e) {
      e.printStackTrace();
      req.setAttribute("mensajeError", "Error al guardar los cambios en la base de datos.");
      req.getRequestDispatcher("/view/MensajeError.jsp").forward(req, resp);
    }
  }

  /** Valida campos obligatorios. Retorna false si hay error. */
  private boolean validarDatos(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String nombre = req.getParameter("nombre");
    String categoria = req.getParameter("categoria");
    String id = req.getParameter("id");

    if (nombre == null
        || nombre.trim().isEmpty()
        || categoria == null
        || categoria.trim().isEmpty()) {
      req.setAttribute("mensajeError", "El nombre y la categoría son campos obligatorios.");

      // Si hay error, el botón "Aceptar" debe devolverme al formulario de ESTE hábito
      String destino = req.getContextPath() + "/ModificarHabitoController?ruta=cargar&id=" + id;
      req.setAttribute("urlDestino", destino);

      req.getRequestDispatcher("/view/MensajeError.jsp").forward(req, resp);
      return false;
    }
    return true;
  }
}
