package com.controller;

import com.model.DAO.HabitoDAO;
import com.model.entities.Habito;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EliminarHabitoController")
public class EliminarHabitoController extends HttpServlet {

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
    // Por defecto listamos
    String accion =
        (request.getParameter("accion") != null) ? request.getParameter("accion") : "listar";

    System.out.println("--- DEBUG: Accion recibida en EliminarController: " + accion);

    switch (accion) {
      case "listar":
        this.mostrarListaParaEliminar(request, response);
        break;
      case "seleccionar": // Ojo: tu JSP debe mandar accion=seleccionar al hacer clic en borrar
        this.eliminarElHabitoSeleccionado(request, response);
        break;
      default:
        this.mostrarListaParaEliminar(request, response);
        break;
    }
  }

  /** Muestra la lista de hábitos con el botón de eliminar. */
  public void mostrarListaParaEliminar(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      // CORRECCIÓN: Usamos listarTodos() que crea su propia conexión segura
      List<Habito> habitos = HabitoDAO.listarTodos();

      req.setAttribute("habitos", habitos);
      requestDispatcherForward(req, resp, "/view/EliminarHabito.jsp");

    } catch (Exception e) {
      e.printStackTrace();
      req.setAttribute("error", "Error al cargar la lista: " + e.getMessage());
      requestDispatcherForward(req, resp, "/view/MensajeError.jsp");
    }
  }

  /** Recibe el ID y ejecuta el borrado. */
  public void eliminarElHabitoSeleccionado(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String idHabitoStr = req.getParameter("idHabito");

    System.out.println("--- DEBUG: Intentando eliminar hábito ID: " + idHabitoStr);

    if (idHabitoStr == null || idHabitoStr.isEmpty()) {
      req.setAttribute("error", "No se recibió el ID del hábito a eliminar.");
      requestDispatcherForward(req, resp, "/view/MensajeError.jsp");
      return;
    }

    try {
      int idHabito = Integer.parseInt(idHabitoStr);

      // Llamamos al método robusto del DAO
      boolean eliminado = HabitoDAO.eliminarHabito(idHabito);

      if (eliminado) {
        // Éxito: Vamos al mensaje de eliminado
        req.setAttribute(
            "mensaje", "El hábito y sus tareas asociadas fueron eliminados correctamente.");

        // Redirección inteligente al Dashboard al dar Aceptar
        String destino = req.getContextPath() + "/EliminarHabitoController?accion=listar";
        req.setAttribute("urlDestino", destino);

        requestDispatcherForward(req, resp, "/view/MensajeEliminado.jsp");
      } else {
        req.setAttribute("error", "No se pudo eliminar el hábito. Verifique que exista.");
        requestDispatcherForward(req, resp, "/view/MensajeError.jsp");
      }

    } catch (NumberFormatException nfe) {
      req.setAttribute("error", "El ID del hábito no es un número válido.");
      requestDispatcherForward(req, resp, "/view/MensajeError.jsp");
    } catch (Exception e) {
      e.printStackTrace(); // ¡MIRA LA CONSOLA SI FALLA!
      req.setAttribute("error", "Error técnico al eliminar: " + e.getMessage());
      requestDispatcherForward(req, resp, "/view/MensajeError.jsp");
    }
  }

  private void requestDispatcherForward(
      HttpServletRequest req, HttpServletResponse resp, String vista)
      throws ServletException, IOException {
    req.getRequestDispatcher(vista).forward(req, resp);
  }
}
