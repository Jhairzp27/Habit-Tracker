package com.controller;

import com.model.DAO.HabitoDAO;
import com.model.entities.Habito;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/PlanificarHabitoController")
public class PlanificarHabitoController extends HttpServlet {

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
    String ruta =
        (request.getParameter("ruta") != null)
            ? request.getParameter("ruta")
            : "seleccionarPlanificarHabito";

    switch (ruta) {
      case "seleccionarPlanificarHabito":
        this.seleccionarPlanificarHabito(request, response);
        break;

      case "listar":
        this.obtenerSinPlanificacion(request, response);
        break;

      case "seleccionarHabito":
        this.seleccionarHabito(request, response);
        break;

      case "guardar":
        this.guardar(request, response);
        break;

      case "aceptar":
        this.aceptar(request, response);
        break;

      default:
        this.seleccionarPlanificarHabito(request, response);
        break;
    }
  }

  private void aceptar(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    obtenerSinPlanificacion(request, response);
  }

  private void seleccionarPlanificarHabito(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    obtenerSinPlanificacion(req, resp);
  }

  public void obtenerSinPlanificacion(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      // DAO: Dame los que tienen frecuencia = 0
      List<Habito> lista = HabitoDAO.obtenerNoPlanificados();
      req.setAttribute("habitosPendientes", lista);
      req.getRequestDispatcher("/view/PlanificarHabito.jsp").forward(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("view/MensajeError.jsp");
    }
  }

  public void seleccionarHabito(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      int id = Integer.parseInt(req.getParameter("id"));
      Habito habito = HabitoDAO.buscarPorId(id);
      req.setAttribute("habito", habito);
      req.getRequestDispatcher("/view/FormularioPlanificacion.jsp").forward(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("view/MensajeError.jsp");
    }
  }

  public void guardar(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      if (!validarDatos(req, resp)) {
        return;
      }

      int id = Integer.parseInt(req.getParameter("id"));
      String[] diasArray = req.getParameterValues("dias");
      String horaStr = req.getParameter("hora");

      int frecuencia = (diasArray != null) ? diasArray.length : 0;
      String diasStr = (diasArray != null) ? String.join(",", diasArray) : "";

      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
      Date horario = sdf.parse(horaStr);

      Habito habito = HabitoDAO.buscarPorId(id);
      habito.setFrecuencia(frecuencia);
      habito.setDia(diasStr);
      habito.setHorario(horario);

      HabitoDAO.actualizar(habito);

      // 4. GUARDAR LAS TAREAS (Lógica Nueva)
      String[] tareasNombres = req.getParameterValues("tareas");

      if (tareasNombres != null) {
        for (String nombreTarea : tareasNombres) {
          if (nombreTarea != null && !nombreTarea.trim().isEmpty()) {
            com.model.entities.Tarea nuevaTarea = new com.model.entities.Tarea();
            nuevaTarea.setNombreTarea(nombreTarea);
            nuevaTarea.setHabito(habito);

            // Guardamos usando el DAO de Tarea
            com.model.DAO.TareaDAO.guardar(nuevaTarea);
          }
        }
      }

      String mensaje = "Planificación Guardada Exitosamente";

      String destino = req.getContextPath() + "/PlanificarHabitoController?ruta=listar";

      String mensajeEncoded =
          java.net.URLEncoder.encode(mensaje, java.nio.charset.StandardCharsets.UTF_8);
      String destinoEncoded =
          java.net.URLEncoder.encode(destino, java.nio.charset.StandardCharsets.UTF_8);

      resp.sendRedirect(
          req.getContextPath()
              + "/view/MensajeGuardado.jsp?mensaje="
              + mensajeEncoded
              + "&urlDestino="
              + destinoEncoded);

    } catch (Exception e) {
      e.printStackTrace();
      // En caso de error técnico grave
      resp.sendRedirect(req.getContextPath() + "/view/MensajeError.jsp");
    }
  }

  /**
   * Valida los datos del formulario (Flujos Alternos 4.1 y 4.2). Retorna TRUE si es válido, FALSE
   * si hay error.
   */
  private boolean validarDatos(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String[] diasArray = request.getParameterValues("dias");
      String horaStr = request.getParameter("hora");
      String idStr = request.getParameter("id");

      // --- FLUJO ALTERNO 4.1: Datos Incompletos ---
      if (diasArray == null || diasArray.length == 0 || horaStr == null || horaStr.isEmpty()) {
        request.setAttribute("mensajeError", "Hay campos obligatorios vacíos (Días u Hora).");
        // Ojo: Para volver al formulario necesitamos el ID, así que intentamos recuperarlo
        String destino =
            (idStr != null)
                ? request.getContextPath()
                    + "/PlanificarHabitoController?ruta=seleccionarHabito&id="
                    + idStr
                : request.getContextPath() + "/PlanificarHabitoController?ruta=listar";

        request.setAttribute("urlDestino", destino);
        request.getRequestDispatcher("/view/MensajeError.jsp").forward(request, response);
        return false; // Detener
      }

      // --- FLUJO ALTERNO 4.2: Hora Duplicada ---
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
      Date horario = sdf.parse(horaStr);
      int id = Integer.parseInt(idStr);

      if (HabitoDAO.existeHoraRegistrada(horario, id)) {
        request.setAttribute(
            "mensajeError", "La hora seleccionada ya se encuentra registrada para otro hábito.");
        // Volver al formulario para que corrija la hora
        request.setAttribute(
            "urlDestino",
            request.getContextPath()
                + "/PlanificarHabitoController?ruta=seleccionarHabito&id="
                + id);
        request.getRequestDispatcher("/view/MensajeError.jsp").forward(request, response);
        return false; // Detener
      }

      return true; // Todo OK

    } catch (Exception e) {
      e.printStackTrace();
      // Error de conversión (fechas, números)
      request.setAttribute("mensajeError", "Formato de datos inválido.");
      request.getRequestDispatcher("/view/MensajeError.jsp").forward(request, response);
      return false;
    }
  }
}
