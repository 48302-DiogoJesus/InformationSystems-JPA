package model.Entities;

import jakarta.persistence.*;
import DataScopes.JPAEntity;

import java.io.Serializable;

@Entity
@Table(
    name = "registo_proc"
)
public class RegistoProc implements Serializable, JPAEntity<Integer> {
  @Id
  @JoinColumn(name = "id_registo", nullable = false)
  @OneToOne(fetch = FetchType.LAZY)
  private Registo id_registo;

  @Version
  @Column(name="vers")
  private Integer vers;

  public RegistoProc() {
  }

  public Registo getId_registo() {
    return id_registo;
  }

  public void setId_registo(Registo id_registo) {
    this.id_registo = id_registo;
  }

  @Override
  public Integer getPK() {
    return getId_registo().getPK();
  }
}
