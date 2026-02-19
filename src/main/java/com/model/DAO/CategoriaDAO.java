package com.model.DAO;

import com.model.entities.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CategoriaDAO {

  private static EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("HabitTrackerPU");
  private static EntityManager em = emf.createEntityManager();

  static {
    inicializarCategorias();
  }

  public CategoriaDAO() {}

  private static void inicializarCategorias() {
    try {
      TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Categoria c", Long.class);
      Long count = query.getSingleResult();

      if (count == 0) {
        em.getTransaction().begin();

        Categoria deporte = new Categoria();
        deporte.setNombre("Deporte");
        em.persist(deporte);

        Categoria salud = new Categoria();
        salud.setNombre("Salud");
        em.persist(salud);

        Categoria estiloVida = new Categoria();
        estiloVida.setNombre("Estilo de vida");
        em.persist(estiloVida);

        Categoria otros = new Categoria();
        otros.setNombre("Otros");
        em.persist(otros);

        em.getTransaction().commit();
      }
    } catch (Exception e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      e.printStackTrace();
    }
  }

  public static List<Categoria> obtenerTodas() {
    TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
    return query.getResultList();
  }

  public static Categoria obtenerPorId(int id) {
    return em.find(Categoria.class, id);
  }
}
