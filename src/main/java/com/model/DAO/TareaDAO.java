package com.model.DAO;

import com.model.entities.Tarea;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TareaDAO {

  // Asegúrate de que "persistence" coincida con el nombre en tu persistence.xml
  private static EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("HabitTrackerPU");

  /**
   * Guarda una nueva tarea en la base de datos.
   *
   * @param tarea Objeto tarea con el nombre y el hábito asignado.
   */
  public static void guardar(Tarea tarea) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();

      // Guardamos la tarea
      em.persist(tarea);

      em.getTransaction().commit();
      System.out.println("Tarea guardada: " + tarea.getNombreTarea());

    } catch (Exception e) {
      e.printStackTrace();
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
    } finally {
      em.close();
    }
  }

  /**
   * Obtiene todas las tareas asociadas a un hábito específico. Útil para cuando quieras mostrar o
   * editar el hábito.
   *
   * @param idHabito ID del hábito padre.
   * @return Lista de tareas.
   */
  public static List<Tarea> listarPorHabito(int idHabito) {
    EntityManager em = emf.createEntityManager();
    try {
      // Consulta JPQL: Selecciona tareas donde el id del hábito coincida
      String jpql = "SELECT t FROM Tarea t WHERE t.habito.idHabito = :idHabito";
      TypedQuery<Tarea> query = em.createQuery(jpql, Tarea.class);
      query.setParameter("idHabito", idHabito);

      return query.getResultList();
    } finally {
      em.close();
    }
  }

  /**
   * Elimina una tarea por su ID.
   *
   * @param idTarea
   */
  public static void eliminar(int idTarea) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      Tarea tarea = em.find(Tarea.class, idTarea);
      if (tarea != null) {
        em.remove(tarea);
      }
      em.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
    } finally {
      em.close();
    }
  }
}
