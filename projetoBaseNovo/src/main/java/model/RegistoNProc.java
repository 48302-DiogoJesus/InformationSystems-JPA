package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(
    name = "registo_n_proc"
)
public class RegistoNProc implements Serializable {
  @Id
  @JoinColumn(name = "id_registo", nullable = false)
  @OneToOne
  private Registo id_registo;

  public RegistoNProc() {
  }

  public Registo getId_registo() {
    return id_registo;
  }

  public void setId_registo(Registo id_registo) {
    this.id_registo = id_registo;
  }
}
