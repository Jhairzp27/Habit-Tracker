package com.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Tarea")
public class Tarea implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idTarea;

  @Column(name = "nombreTarea")
  private String nombreTarea; // Cambio de int a String para poder escribir el nombre

  @ManyToOne
  @JoinColumn(name = "idHabito")
  private Habito habito; // Cambio de int a la clase Habito para crear la relación

  public Tarea() {}

  public int getIdTarea() {
    return idTarea;
  }

  public void setIdTarea(int idTarea) {
    this.idTarea = idTarea;
  }

  public String getNombreTarea() {
    return nombreTarea;
  }

  public void setNombreTarea(String nombreTarea) {
    this.nombreTarea = nombreTarea;
  }

  public Habito getHabito() {
    return habito;
  }

  public void setHabito(Habito habito) {
    this.habito = habito;
  }
}
