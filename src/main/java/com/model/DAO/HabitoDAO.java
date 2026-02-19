package com.model.DAO;

import com.model.entities.Categoria;
import com.model.entities.Habito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class HabitoDAO {

  private static EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("HabitTrackerPU");
  private static EntityManager em;

  public HabitoDAO() {}

  private void inicializarHabitos() {
    try {
      TypedQuery<Long> query = em.createQuery("SELECT COUNT(h) FROM Habito h", Long.class);
      Long count = query.getSingleResult();

      if (count == 0) {
        System.out.println("No hay habitos, se iniciaran datos por defecto...");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Guarda un hábito deseado
   *
   * @param habito
   */
  public static void guardar(Habito habito, int idCategoria) {
    // Creamos un EntityManager nuevo para cada operación (Más seguro para web)
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      // Buscamos la categoría
      Categoria cat = em.find(Categoria.class, idCategoria);

      if (cat != null) {
        habito.setCategoria(cat);
        em.persist(habito);
        em.getTransaction().commit();
        System.out.println("Hábito guardado con éxito.");
      } else {
        System.err.println("ERROR: No existe la categoría con ID " + idCategoria);
      }

    } catch (Exception e) {
      System.err.println("ERROR AL GUARDAR EN BD:");
      e.printStackTrace();
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
    } finally {
      // Cerramos el gestor
      em.close();
    }
  }

  /**
   * Obtiene habitos que no han sido planificados
   *
   * @param planificado
   */
  public static void obtenerSinPlanificacion() {
    List<Habito> habitos = listarHabitos();

    // Filtrar hábitos NO planificados
    for (Habito habito : habitos) {
      if (habito.getFrecuencia() <= 0 || habito.getDia() == null || habito.getHorario() == null) {
        // Hábito NO está planificado
        System.out.println("Hábito NO planificado: " + habito.getNombre());
      }
    }
  }

  /**
   * Lista todos los hábitos
   *
   * @return una lista de hábitos creados
   */
  public static List<Habito> listarHabitos() {
    return em.createQuery("SELECT h FROM Habito h", Habito.class).getResultList();
  }

  /**
   * Lista todos los habitos
   *
   * @return habitos ordenados por su ID
   */
  public static List<Habito> listarTodos() {
    EntityManager em = emf.createEntityManager();
    try {
      return em.createQuery("SELECT h FROM Habito h ORDER BY h.idHabito DESC", Habito.class)
          .getResultList();
    } finally {
      em.close();
    }
  }

  public static void actualizarHabito(Habito habito) {
    try {
      em.getTransaction().begin();
      em.merge(habito);
      em.getTransaction().commit();
    } catch (Exception e) {
      em.getTransaction().rollback();
    }
  }

  /**
   * Eliminar habitos por su id
   *
   * @param id del hábito
   * @return true si se eliminó, false si no se encontró
   */
  public static boolean eliminarHabito(int id) {
    // 1. Crear un EntityManager nuevo y fresco
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();

      // 2. Buscar el hábito
      Habito h = em.find(Habito.class, id);

      if (h != null) {
        // 3. Eliminar (Si tienes Cascade en BD o JPA, se borrarán las tareas solas)
        em.remove(h);
        em.getTransaction().commit();
        return true;
      }
      return false; // No existía

    } catch (Exception e) {
      e.printStackTrace(); // Mira la consola de Eclipse si falla
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      return false;
    } finally {
      em.close(); // Siempre cerrar
    }
  }

  public static Habito buscarPorId(int id) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(Habito.class, id);
    } finally {
      em.close();
    }
  }

  /**
   * @return
   */
  public static List<Habito> obtenerNoPlanificados() {
    EntityManager em = emf.createEntityManager();
    try {
      // Asumimos que frecuencia 0 significa "No planificado"
      return em.createQuery("SELECT h FROM Habito h WHERE h.frecuencia = 0", Habito.class)
          .getResultList();
    } finally {
      em.close();
    }
  }

  /**
   * @param habito
   */
  public static void actualizar(Habito habito) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(habito);
      em.getTransaction().commit();
    } catch (Exception e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      em.close();
    }
  }

  /**
   * Verifica si ya existe un hábito con esa hora, excluyendo el hábito actual. Flow 4.2: Hora
   * Duplicada.
   *
   * @param horario
   * @param idHabitoActual
   * @return true si ya existe ese horario
   */
  public static boolean existeHoraRegistrada(Date horario, int idHabitoActual) {
    EntityManager em = emf.createEntityManager();
    try {
      // Consultamos si hay algún hábito con el mismo horario pero diferente ID
      String jpql =
          "SELECT COUNT(h) FROM Habito h WHERE h.horario = :horario AND h.idHabito != :id";
      TypedQuery<Long> query = em.createQuery(jpql, Long.class);
      query.setParameter("horario", horario);
      query.setParameter("id", idHabitoActual);

      Long count = query.getSingleResult();
      return count > 0; // Retorna true si ya existe (es duplicada)
    } finally {
      em.close();
    }
  }
}
