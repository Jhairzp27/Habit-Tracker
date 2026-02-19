package com.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Habito")
public class Habito implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idHabito;

  @Column(name = "nombre")
  private String nombre;

  @ManyToOne // Un hábito tiene una categoría
  @JoinColumn(name = "id_categoria") // Nombre de la columna en la BD
  private Categoria categoria;

  @Temporal(TemporalType.DATE) // Indica que en la BD solo guarde la fecha
  private Date fechaInicio;

  @Column(name = "frecuencia")
  private int frecuencia;

  @Column(name = "dia")
  private String dia;

  @Temporal(TemporalType.TIME) // Indica que en la BD solo guarde la hora
  private Date horario;

  @Column(name = "descripcion")
  private String descripcion;

  public Habito() {}

  public int getIdHabito() {
    return idHabito;
  }

  public void setIdHabito(int idHabito) {
    this.idHabito = idHabito;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Date getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public int getFrecuencia() {
    return frecuencia;
  }

  public void setFrecuencia(int frecuencia) {
    this.frecuencia = frecuencia;
  }

  public String getDia() {
    return dia;
  }

  public void setDia(String dia) {
    this.dia = dia;
  }

  public Date getHorario() {
    return horario;
  }

  public void setHorario(Date horario) {
    this.horario = horario;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Habito(
      int idHabito,
      String nombre,
      Categoria categoria,
      Date fechaInicio,
      int frecuencia,
      String dia,
      Date horario,
      String descripcion) {
    super();
    this.idHabito = idHabito;
    this.nombre = nombre;
    this.categoria = categoria;
    this.fechaInicio = fechaInicio;
    this.frecuencia = frecuencia;
    this.dia = dia;
    this.horario = horario;
    this.descripcion = descripcion;
  }
}
