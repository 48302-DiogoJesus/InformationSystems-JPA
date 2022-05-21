package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(
    name = "registo_n_proc"
)
public class RegistoNProc implements Serializable {
  @Id
  @Column(
      nullable = false
  )
  private int id_registo;

  public RegistoNProc() {
  }

  public int getId_registo() {
    return this.id_registo;
  }

  public void setId_registo(int id_registo) {
    this.id_registo = id_registo;
  }
}
