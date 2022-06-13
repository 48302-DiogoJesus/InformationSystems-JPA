package model.Entities;

import jakarta.persistence.*;
import DataScopes.JPAEntity;

import java.io.Serializable;

@Entity
@Table(
    name = "alarme"
)
public class Alarme implements Serializable, JPAEntity<Integer> {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  @Column(
      nullable = false
  )
  private int id;

  @JoinColumn(name = "id_registo", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Registo id_registo;

  @JoinColumn(name = "id_veiculo",  nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Veiculo id_veiculo;

  public Alarme() {
  }

  public int getId() {
    return this.id;
  }

  @Override
  public Integer getPK() {
    return getId();
  }

  public void setId(int id) {
    this.id = id;
  }

  public Registo getId_registo() {
    return id_registo;
  }

  public void setId_registo(Registo id_registo) {
    this.id_registo = id_registo;
  }

  public Veiculo getId_veiculo() {
    return id_veiculo;
  }

  public void setId_veiculo(Veiculo id_veiculo) {
    this.id_veiculo = id_veiculo;
  }
}
