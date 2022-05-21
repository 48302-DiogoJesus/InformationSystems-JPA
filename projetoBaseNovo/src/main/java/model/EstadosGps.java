package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "estados_gps"
)
public class EstadosGps implements Serializable {
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
}
