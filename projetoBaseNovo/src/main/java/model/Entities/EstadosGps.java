package model.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import DataScopes.JPAEntity;

import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "estados_gps"
)
public class EstadosGps implements Serializable, JPAEntity<String> {
  @Id
  @Column(
      nullable = false
  )
  private String estado;

  public EstadosGps() {
  }

  public String getEstado() {
    return this.estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  @Override
  public String getPK() {
    return getEstado();
  }
}
