package com.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

  /** */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idCategoria;

  @Column(name = "nombre")
  private String nombre;

  // 2. Constructor vacío (obligatorio para JPA)
  public Categoria() {}

  public Categoria(int idCategoria, String nombre) {
    super();
    this.idCategoria = idCategoria;
    this.nombre = nombre;
  }

  // 3. Getters y Setters
  public int getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(int idCategoria) {
    this.idCategoria = idCategoria;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
