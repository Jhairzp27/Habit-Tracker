package com.model.DAO;

import com.model.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO {

  private static final String PERSISTENCE_UNIT_NAME = "HabitTrackerPU";
  private EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

  // Método para autenticar usuario
  public Usuario autenticar(String correoElectronico, String password) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Usuario> query =
          em.createQuery(
              "SELECT u FROM Usuario u WHERE u.correoElectronico = :correo AND u.password = :password",
              Usuario.class);
      query.setParameter("correo", correoElectronico);
      query.setParameter("password", password);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    } finally {
      em.close();
    }
  }

  // Método para buscar usuario por correo
  public Usuario buscarPorCorreo(String correoElectronico) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Usuario> query =
          em.createQuery(
              "SELECT u FROM Usuario u WHERE u.correoElectronico = :correo", Usuario.class);
      query.setParameter("correo", correoElectronico);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    } finally {
      em.close();
    }
  }

  // Método para crear un nuevo usuario
  public boolean crear(Usuario usuario) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(usuario);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  // Método para buscar usuario por ID
  public Usuario buscarPorId(int idUsuario) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(Usuario.class, idUsuario);
    } finally {
      em.close();
    }
  }

  // Método para actualizar usuario
  public boolean actualizar(Usuario usuario) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(usuario);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }

  // Método para eliminar usuario
  public boolean eliminar(int idUsuario) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      Usuario usuario = em.find(Usuario.class, idUsuario);
      if (usuario != null) {
        em.remove(usuario);
        em.getTransaction().commit();
        return true;
      }
      return false;
    } catch (Exception e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      em.close();
    }
  }
}
